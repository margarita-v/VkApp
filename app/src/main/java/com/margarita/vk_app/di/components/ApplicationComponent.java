package com.margarita.vk_app.di.components;

import com.margarita.vk_app.di.module.ApplicationModule;
import com.margarita.vk_app.di.module.ManagerModule;
import com.margarita.vk_app.ui.activity.BaseActivity;
import com.margarita.vk_app.ui.activity.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ManagerModule.class, ApplicationModule.class})
public interface ApplicationComponent {

    void inject(BaseActivity activity);

    void inject(MainActivity activity);
}
