package com.margarita.vk_app.mvp.presenter.open;

import com.arellomobile.mvp.InjectViewState;
import com.margarita.vk_app.VkApplication;
import com.margarita.vk_app.common.utils.VkListHelper;
import com.margarita.vk_app.models.common.CommentItem;
import com.margarita.vk_app.models.view.base.BaseViewModel;
import com.margarita.vk_app.models.view.item.footer.CommentFooter;
import com.margarita.vk_app.models.view.post.PostHeader;
import com.margarita.vk_app.mvp.presenter.BaseFeedPresenter;
import com.margarita.vk_app.mvp.view.BaseFeedView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

@InjectViewState
public class OpenedCommentPresenter extends BaseFeedPresenter<BaseFeedView, CommentItem>
        implements BaseFeedPresenter.ParsingInterface<CommentItem> {

    private int id;

    public OpenedCommentPresenter() {
        VkApplication.getApplicationComponent().inject(this);
    }

    @Override
    public Observable<BaseViewModel> onLoadDataObservable(int offset, int count) {
        return getCommentsObservable();
    }

    @Override
    public Observable<BaseViewModel> onRestoreDataObservable() {
        return getCommentsObservable();
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
        // There is no view model for an opened comment
        List<BaseViewModel> result = new ArrayList<>();
        result.add(new PostHeader(item));
        result.addAll(VkListHelper.getAttachmentVkItems(item.getAttachments()));
        result.add(new CommentFooter(item));
        return result;
    }

    public void setId(int id) {
        this.id = id;
    }

    private Observable<BaseViewModel> getCommentsObservable() {
        return Observable.fromCallable(getItemFromRealmCallable(id))
                //.retry(2)
                .flatMap(commentItem ->
                        Observable.fromIterable(parseItemToList(commentItem)));
    }
}
