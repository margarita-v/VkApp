package com.margarita.vk_app.di.components;

import com.margarita.vk_app.common.manager.NetworkManager;
import com.margarita.vk_app.di.module.ApplicationModule;
import com.margarita.vk_app.di.module.ManagerModule;
import com.margarita.vk_app.di.module.RestModule;
import com.margarita.vk_app.mvp.presenter.BoardPresenter;
import com.margarita.vk_app.mvp.presenter.InfoPresenter;
import com.margarita.vk_app.mvp.presenter.main.MainPresenter;
import com.margarita.vk_app.mvp.presenter.MembersPresenter;
import com.margarita.vk_app.mvp.presenter.NewsFeedPresenter;
import com.margarita.vk_app.ui.activity.BaseActivity;
import com.margarita.vk_app.ui.activity.MainActivity;
import com.margarita.vk_app.ui.fragment.NewsFeedFragment;
import com.margarita.vk_app.ui.holder.NewsItemBodyHolder;
import com.margarita.vk_app.ui.holder.NewsItemFooterHolder;
import com.margarita.vk_app.ui.holder.attachment.ImageAttachmentHolder;

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

    // View holders
    void inject(NewsItemBodyHolder holder);
    void inject(NewsItemFooterHolder holder);
    void inject(ImageAttachmentHolder holder);

    // Presenters
    void inject(NewsFeedPresenter presenter);
    void inject(MainPresenter presenter);
    void inject(MembersPresenter presenter);
    void inject(BoardPresenter presenter);
    void inject(InfoPresenter presenter);

    // Managers
    void inject(NetworkManager manager);
}
