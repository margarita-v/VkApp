package com.margarita.vk_app.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.margarita.vk_app.CurrentUser;
import com.margarita.vk_app.R;
import com.margarita.vk_app.VkApplication;
import com.margarita.vk_app.common.manager.NetworkManager;
import com.margarita.vk_app.common.manager.VkFragmentManager;
import com.margarita.vk_app.models.common.Profile;
import com.margarita.vk_app.mvp.view.MainView;
import com.margarita.vk_app.rest.api.UsersApi;
import com.margarita.vk_app.rest.model.request.UserGetRequestModel;
import com.margarita.vk_app.ui.fragment.BaseFragment;
import com.margarita.vk_app.ui.fragment.MyPostsFragment;
import com.margarita.vk_app.ui.fragment.NewsFeedFragment;

import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmObject;

/*
 * Annotation for binding ViewState to presenter
 */
@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    @Inject
    VkFragmentManager fragmentManager;

    @Inject
    UsersApi usersApi;

    @Inject
    NetworkManager networkManager;

    public MainPresenter() {
        VkApplication.getApplicationComponent().inject(this);
    }

    /**
     * Check if the user is authorized and show a correct view for different states
     */
    public void checkAuth() {
        if (!CurrentUser.isAuthorized()) {
            showSignInView();
        } else {
            showProfile();
            getViewState().signedIn();
        }
    }

    /**
     * Show user's profile
     */
    private void showProfile() {
        networkManager.getNetworkStateObservable()
                .flatMap(isVkAvailable -> {
                    if (!CurrentUser.isAuthorized())
                        showSignInView();
                    return isVkAvailable
                            ? getProfileFromServer()
                            : getProfileFromDatabase();
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(profile -> getViewState().showCurrentUser(profile),
                        Throwable::printStackTrace);

    }

    /**
     * Show sign in view
     */
    private void showSignInView() {
        getViewState().startSignIn();
    }

    /**
     * Get user's profile from server
     * @return Observable for user's profile
     */
    private Observable<Profile> getProfileFromServer() {
        return usersApi.get(new UserGetRequestModel(CurrentUser.getId()).toMap())
                .flatMap(listFull -> Observable.fromIterable(listFull.getResponse()))
                .doOnNext(this::saveToDb);
    }

    /**
     * Get user's profile from local database
     * @return Observable for user's profile
     */
    private Observable<Profile> getProfileFromDatabase() {
        return Observable.fromCallable(getProfileFromRealmCallable());
    }

    /**
     * Get user's profile from local database as Callable
     * @return Callable for user's profile
     */
    private Callable<Profile> getProfileFromRealmCallable() {
        return () -> {
            Realm realm = Realm.getDefaultInstance();
            Profile realmResults = realm.where(Profile.class)
                    .equalTo("id", Integer.parseInt(CurrentUser.getId()))
                    .findFirst();
            return realm.copyFromRealm(realmResults);
        };
    }

    /**
     * Save item to local database
     * @param item Item which will be saved
     */
    private void saveToDb(RealmObject item) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> realm1.copyToRealmOrUpdate(item));
    }

    /**
     * Event of drawer item click
     * @param id ID of chosen drawer item
     */
    public void onClickDrawerItem(int id) {
        BaseFragment fragment = null;

        switch (id) {
            case R.string.drawer_item_news:
                fragment = new NewsFeedFragment();
                break;
            case R.string.drawer_item_my_posts:
                fragment = new MyPostsFragment();
                break;
        }

        if (fragment != null && fragmentManager.isAlreadyContains(fragment)) {
            getViewState().showFragmentFromDrawer(fragment);
        }
    }
}
