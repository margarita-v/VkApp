package com.margarita.vk_app.mvp.presenter;

import com.margarita.vk_app.VkApplication;
import com.margarita.vk_app.common.utils.DatabaseHelper;
import com.margarita.vk_app.common.utils.VkListHelper;
import com.margarita.vk_app.models.common.WallItem;
import com.margarita.vk_app.models.countable.Likes;
import com.margarita.vk_app.models.view.counter.LikeCounter;
import com.margarita.vk_app.models.view.item.footer.NewsItemFooter;
import com.margarita.vk_app.mvp.view.LikeFooterView;
import com.margarita.vk_app.rest.api.LikeEventOnSubscribe;
import com.margarita.vk_app.rest.api.WallApi;
import com.margarita.vk_app.rest.model.request.WallGetByIdRequest;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmQuery;

/**
 * Usual presenter for performing like actions.
 * This presenter was realized without the Moxy library,
 * because we need to use this presenter in the BaseFooterHolder successors,
 * but we can't inject presenter into the view holder classes
 * using @InjectPresenter and @InjectViewState annotations.
 */
public class LikePresenter {

    @Inject
    WallApi wallApi;

    private LikeFooterView footerView;

    private DatabaseHelper<WallItem> databaseHelper;

    /**
     * Type for a like event creation
     */
    private static final String POST = "post";

    public LikePresenter(LikeFooterView footerView) {
        this.footerView = footerView;
        VkApplication.getApplicationComponent().inject(this);
        databaseHelper = new DatabaseHelper<WallItem>() {
            @Override
            public RealmQuery<WallItem> performQuery(Realm realm) {
                return realm.where(WallItem.class);
            }
        };
    }

    /**
     * Perform like action (add or remove like for post)
     * @param item Footer of post which will be liked or disliked
     */
    public void like(NewsItemFooter item) {
        WallItem wallItem = databaseHelper.getItemFromRealm(item.getId());
        likeObservable(wallItem.getOwnerId(), wallItem.getId(), wallItem.getLikes())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(likeCounter -> footerView.like(item, likeCounter),
                        Throwable::printStackTrace);
    }

    private Observable<LikeCounter> likeObservable(int ownerId, int postId, Likes likes) {
        return Observable.create(new LikeEventOnSubscribe(POST, ownerId, postId, likes))
                .observeOn(Schedulers.io())
                .flatMap(count ->
                        wallApi.getById(new WallGetByIdRequest(ownerId, postId).toMap()))
                .flatMap(full -> Observable.fromIterable(
                        VkListHelper.getWallItemsInfo(full.getResponse())))
                .doOnNext(DatabaseHelper::saveToDatabase)
                .map(wallItem -> new LikeCounter(wallItem.getLikes()));
    }
}
