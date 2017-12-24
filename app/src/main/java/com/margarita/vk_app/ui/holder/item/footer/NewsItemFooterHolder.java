package com.margarita.vk_app.ui.holder.item.footer;

import android.view.View;
import android.widget.TextView;

import com.margarita.vk_app.R;
import com.margarita.vk_app.VkApplication;
import com.margarita.vk_app.common.manager.VkFragmentManager;
import com.margarita.vk_app.common.utils.Utils;
import com.margarita.vk_app.common.utils.VkListHelper;
import com.margarita.vk_app.models.common.Place;
import com.margarita.vk_app.models.common.WallItem;
import com.margarita.vk_app.models.countable.Likes;
import com.margarita.vk_app.models.view.counter.LikeCounter;
import com.margarita.vk_app.models.view.item.footer.NewsItemFooter;
import com.margarita.vk_app.rest.api.LikeEventOnSubscribe;
import com.margarita.vk_app.rest.api.WallApi;
import com.margarita.vk_app.rest.model.request.WallGetByIdRequest;
import com.margarita.vk_app.ui.activity.BaseActivity;
import com.margarita.vk_app.ui.fragment.CommentsFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;

public class NewsItemFooterHolder extends BaseFooterHolder<NewsItemFooter> {

    @BindView(R.id.tvCommentsIcon)
    TextView tvCommentsIcon;

    @BindView(R.id.tvCommentsCount)
    TextView tvCommentsCount;

    @BindView(R.id.tvRepostsIcon)
    TextView tvRepostIcon;

    @BindView(R.id.tvRepostsCount)
    TextView tvRepostCount;

    @BindView(R.id.comments)
    View viewComments;

    @Inject
    WallApi wallApi;

    @Inject
    VkFragmentManager fragmentManager;

    private static final String POST = "post";
    private static final String ID_KEY = "id";

    public NewsItemFooterHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        VkApplication.getApplicationComponent().inject(this);
        tvLikesIcon.setTypeface(googleFont);
        tvCommentsIcon.setTypeface(googleFont);
        tvRepostIcon.setTypeface(googleFont);
    }

    @Override
    public void bindViewHolder(NewsItemFooter item) {
        super.bindViewHolder(item);
        bindFooterItem(tvCommentsCount, tvCommentsIcon, item.getCommentsCounter());
        bindFooterItem(tvRepostCount, tvRepostIcon, item.getRepostCounter());
        viewComments.setOnClickListener(view ->
                fragmentManager.addFragment(
                        (BaseActivity) view.getContext(),
                        CommentsFragment.newInstance(new Place(
                                parseIntToString(item.getOwnerId()),
                                parseIntToString(item.getId()))),
                        R.id.container));
        viewLikes.setOnClickListener(view -> like(item));
    }

    @Override
    public void unbindViewHolder() {
        super.unbindViewHolder();
        viewComments.setOnClickListener(null);
        clearTextView(tvCommentsCount);
        clearTextView(tvRepostCount);
    }

    private Observable<LikeCounter> likeObservable(int ownerId, int postId, Likes likes) {
        return Observable.create(new LikeEventOnSubscribe(POST, ownerId, postId, likes))
                .observeOn(Schedulers.io())
                .flatMap(count ->
                        wallApi.getById(new WallGetByIdRequest(ownerId, postId).toMap()))
                .flatMap(full -> Observable.fromIterable(
                        VkListHelper.getWallItemsInfo(full.getResponse())))
                .doOnNext(Utils::saveToDatabase)
                .map(wallItem -> new LikeCounter(wallItem.getLikes()));
    }

    private void like(NewsItemFooter item) {
        WallItem wallItem = getWallItemFromRealm(item.getId());
        likeObservable(wallItem.getOwnerId(), wallItem.getId(), wallItem.getLikes())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(likeCounter -> {
                    item.setLikeCounter(likeCounter);
                    bindFooterItem(tvLikesCount, tvLikesIcon, likeCounter);
                }, Throwable::printStackTrace);
    }

    private WallItem getWallItemFromRealm(int postId) {
        Realm realm = Realm.getDefaultInstance();
        WallItem wallItem = realm.where(WallItem.class)
                .equalTo(ID_KEY, postId)
                .findFirst();
        return realm.copyFromRealm(wallItem);
    }
}
