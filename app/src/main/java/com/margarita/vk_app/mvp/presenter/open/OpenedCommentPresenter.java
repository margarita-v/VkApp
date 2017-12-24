package com.margarita.vk_app.mvp.presenter.open;

import com.arellomobile.mvp.InjectViewState;
import com.margarita.vk_app.VkApplication;
import com.margarita.vk_app.common.utils.DatabaseHelper;
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

@InjectViewState
public class OpenedCommentPresenter extends BaseFeedPresenter<BaseFeedView, CommentItem>
        implements BaseFeedPresenter.ParsingInterface<CommentItem> {

    private int id;

    /**
     * Count of retry for getting a concrete comment
     */
    private static final int RETRY_COUNT = 4;

    public OpenedCommentPresenter() {
        VkApplication.getApplicationComponent().inject(this);
        databaseHelper = new DatabaseHelper<CommentItem>() {
            @Override
            public RealmQuery<CommentItem> performQuery(Realm realm) {
                return realm.where(CommentItem.class);
            }
        };
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
        return Observable.fromCallable(databaseHelper.getItemFromRealmCallable(id))
                .retry(RETRY_COUNT)
                .flatMap(commentItem ->
                        Observable.fromIterable(parseItemToList(commentItem)));
    }
}
