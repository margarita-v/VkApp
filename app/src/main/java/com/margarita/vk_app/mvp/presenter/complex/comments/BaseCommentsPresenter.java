package com.margarita.vk_app.mvp.presenter.complex.comments;

import com.margarita.vk_app.common.utils.DatabaseHelper;
import com.margarita.vk_app.models.common.CommentItem;
import com.margarita.vk_app.models.common.Place;
import com.margarita.vk_app.models.view.base.BaseViewModel;
import com.margarita.vk_app.models.view.item.body.CommentBody;
import com.margarita.vk_app.models.view.item.footer.CommentFooter;
import com.margarita.vk_app.models.view.profile.CommentHeader;
import com.margarita.vk_app.mvp.presenter.BaseFeedPresenter;
import com.margarita.vk_app.mvp.view.BaseFeedView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.Sort;

/**
 * Base class for comment item's presenters
 */
abstract class BaseCommentsPresenter extends BaseFeedPresenter<BaseFeedView, CommentItem>
        implements BaseFeedPresenter.ParsingInterface<CommentItem> {

    /**
     * Place of comments
     */
    protected Place place;

    BaseCommentsPresenter() {
        databaseHelper = new DatabaseHelper<CommentItem>() {
            @Override
            public RealmQuery<CommentItem> performQuery(Realm realm) {
                return realm.where(CommentItem.class);
            }
        };
    }

    Observable<BaseViewModel> loadFromDatabase(boolean isFromTopic) {
        return Observable.fromCallable(
                databaseHelper.getListFromRealmCallable(Sort.ASCENDING))
                .flatMap(Observable::fromIterable)
                .filter(commentItem -> commentItem.getPlace().equals(this.place) &&
                        commentItem.isFromTopic() == isFromTopic)
                .flatMap(commentItem ->
                        Observable.fromIterable(parseItemToList(commentItem)));
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
}
