package com.margarita.vk_app.mvp.presenter.complex;

import com.arellomobile.mvp.InjectViewState;
import com.margarita.vk_app.VkApplication;
import com.margarita.vk_app.common.utils.Utils;
import com.margarita.vk_app.common.utils.VkListHelper;
import com.margarita.vk_app.models.common.CommentItem;
import com.margarita.vk_app.models.common.Place;
import com.margarita.vk_app.models.view.base.BaseViewModel;
import com.margarita.vk_app.models.view.item.body.CommentBody;
import com.margarita.vk_app.models.view.item.footer.CommentFooter;
import com.margarita.vk_app.models.view.profile.CommentHeader;
import com.margarita.vk_app.mvp.presenter.BaseFeedPresenter;
import com.margarita.vk_app.mvp.view.BaseFeedView;
import com.margarita.vk_app.rest.api.WallApi;
import com.margarita.vk_app.rest.model.request.WallGetCommentsRequest;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.Sort;

@InjectViewState
public class CommentsPresenter extends BaseFeedPresenter<BaseFeedView, CommentItem>
        implements BaseFeedPresenter.ParsingInterface<CommentItem> {

    /**
     * Place of comments
     */
    private Place place;

    @Inject
    WallApi wallApi;

    public CommentsPresenter() {
        VkApplication.getApplicationComponent().inject(this);
    }

    @Override
    public Observable<BaseViewModel> onLoadDataObservable(int offset, int count) {
        return wallApi.getComments(
                new WallGetCommentsRequest(
                        parseStringToInt(place.getOwnerId()),
                        parseStringToInt(place.getPostId()),
                        offset)
                        .toMap())
                .flatMap(full -> Observable.fromIterable(
                        VkListHelper.getComments(full.getResponse(), false)))
                .doOnNext(commentItem -> commentItem.setPlace(place))
                .doOnNext(Utils::saveToDatabase)
                .flatMap(commentItem -> Observable.fromIterable(parseItemToList(commentItem)));
    }

    @Override
    public Observable<BaseViewModel> onRestoreDataObservable() {
        return Observable.fromCallable(getListFromRealmCallable(Sort.ASCENDING))
                .flatMap(Observable::fromIterable)
                .filter(commentItem -> commentItem.getPlace().equals(this.place) &&
                        !commentItem.isFromTopic())
                .flatMap(commentItem ->
                        Observable.fromIterable(parseItemToList(commentItem)));
    }

    @Override
    protected RealmQuery<CommentItem> performQuery(Realm realm) {
        return realm.where(CommentItem.class);
    }

    @Override
    protected List<CommentItem> getQueryResult(Realm realm, RealmResults<CommentItem> results) {
        return realm.copyFromRealm(results);
    }

    @Override
    protected CommentItem getQueryResult(Realm realm, CommentItem result) {
        return realm.copyFromRealm(result);
    }

    @Override
    public List<BaseViewModel> parseItemToList(CommentItem item) {
        List<BaseViewModel> result = new ArrayList<>();
        result.add(new CommentHeader(item));
        result.add(new CommentBody(item));
        result.add(new CommentFooter(item));
        return result;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    /**
     * Function for parsing string value to int
     * @param value String value which will be parsed
     * @return Int value which was parsed from string
     */
    private int parseStringToInt(String value) {
        return Integer.parseInt(value);
    }
}
