package com.margarita.vk_app.di.components;

import com.margarita.vk_app.di.module.ApplicationModule;
import com.margarita.vk_app.di.module.ManagerModule;
import com.margarita.vk_app.di.module.RestModule;
import com.margarita.vk_app.mvp.presenter.NewsFeedPresenter;
import com.margarita.vk_app.ui.activity.BaseActivity;
import com.margarita.vk_app.ui.activity.MainActivity;
import com.margarita.vk_app.ui.fragment.NewsFeedFragment;
import com.margarita.vk_app.ui.holder.NewsItemBodyHolder;
import com.margarita.vk_app.ui.holder.NewsItemFooterHolder;

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

    // Presenters
    void inject(NewsFeedPresenter presenter);
}
