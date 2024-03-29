package com.margarita.vk_app.di.components;

import com.margarita.vk_app.common.manager.NetworkManager;
import com.margarita.vk_app.di.module.ApplicationModule;
import com.margarita.vk_app.di.module.ManagerModule;
import com.margarita.vk_app.di.module.RestModule;
import com.margarita.vk_app.mvp.presenter.BoardPresenter;
import com.margarita.vk_app.mvp.presenter.LikePresenter;
import com.margarita.vk_app.mvp.presenter.complex.comments.CommentsPresenter;
import com.margarita.vk_app.mvp.presenter.complex.InfoPresenter;
import com.margarita.vk_app.mvp.presenter.complex.comments.TopicCommentsPresenter;
import com.margarita.vk_app.mvp.presenter.open.OpenedCommentPresenter;
import com.margarita.vk_app.mvp.presenter.open.OpenedPostPresenter;
import com.margarita.vk_app.mvp.presenter.main.MainPresenter;
import com.margarita.vk_app.mvp.presenter.MembersPresenter;
import com.margarita.vk_app.mvp.presenter.complex.NewsFeedPresenter;
import com.margarita.vk_app.ui.activity.BaseActivity;
import com.margarita.vk_app.ui.activity.MainActivity;
import com.margarita.vk_app.ui.fragment.comments.CommentsFragment;
import com.margarita.vk_app.ui.fragment.NewsFeedFragment;
import com.margarita.vk_app.ui.fragment.comments.TopicCommentsFragment;
import com.margarita.vk_app.ui.fragment.open.OpenedCommentFragment;
import com.margarita.vk_app.ui.fragment.open.OpenedPostFragment;
import com.margarita.vk_app.ui.holder.TopicViewHolder;
import com.margarita.vk_app.ui.holder.item.footer.CommentFooterHolder;
import com.margarita.vk_app.ui.holder.item.footer.NewsItemFooterHolder;
import com.margarita.vk_app.ui.holder.attachment.ImageAttachmentHolder;
import com.margarita.vk_app.ui.holder.attachment.VideoAttachmentHolder;
import com.margarita.vk_app.ui.holder.item.body.CommentBodyHolder;
import com.margarita.vk_app.ui.holder.item.body.NewsItemBodyHolder;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ManagerModule.class, ApplicationModule.class, RestModule.class})
public interface ApplicationComponent {

    // Activities
    void inject(BaseActivity activity);
    void inject(MainActivity activity);

    // Fragments
    void inject(NewsFeedFragment fragment);
    void inject(OpenedPostFragment fragment);
    void inject(CommentsFragment fragment);
    void inject(OpenedCommentFragment fragment);
    void inject(TopicCommentsFragment fragment);

    // View holders
    void inject(ImageAttachmentHolder holder);
    void inject(VideoAttachmentHolder holder);
    void inject(TopicViewHolder holder);
    // body view holders
    void inject(NewsItemBodyHolder holder);
    void inject(CommentBodyHolder holder);
    // footer view holders
    void inject(NewsItemFooterHolder holder);
    void inject(CommentFooterHolder holder);

    // Presenters
    void inject(NewsFeedPresenter presenter);
    void inject(MainPresenter presenter);
    void inject(MembersPresenter presenter);
    void inject(BoardPresenter presenter);
    void inject(InfoPresenter presenter);
    void inject(OpenedPostPresenter presenter);
    void inject(CommentsPresenter presenter);
    void inject(OpenedCommentPresenter presenter);
    void inject(TopicCommentsPresenter presenter);
    void inject(LikePresenter presenter);

    // Managers
    void inject(NetworkManager manager);
}
