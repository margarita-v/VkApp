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
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmQuery;

@InjectViewState
public class OpenedCommentPresenter extends BaseFeedPresenter<BaseFeedView, CommentItem>
        implements BaseFeedPresenter.ParsingInterface<CommentItem> {

    private int id;

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
        addNewModel(result, new PostHeader(item));
        result.addAll(VkListHelper.getAttachmentVkItems(item.getAttachments()));
        addNewModel(result, new CommentFooter(item));
        return result;
    }

    /**
     * Function for adding a new view model to the list which represents
     * an opened comment
     * @param list List of opened comment's view models
     * @param model New view model which will be added to the list
     */
    private void addNewModel(List<BaseViewModel> list, BaseViewModel model) {
        model.setForOpenedComment(true);
        list.add(model);
    }

    public void setId(int id) {
        this.id = id;
    }

    private Observable<BaseViewModel> getCommentsObservable() {
        Callable<CommentItem> itemCallable = databaseHelper.getItemFromRealmCallable(id);
        return itemCallable != null
                ? Observable.fromCallable(itemCallable)
                .flatMap(commentItem ->
                        Observable.fromIterable(parseItemToList(commentItem)))
                : Observable.empty();
    }
}
