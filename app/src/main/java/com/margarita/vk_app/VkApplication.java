package com.margarita.vk_app;

import android.app.Application;

import com.margarita.vk_app.di.components.ApplicationComponent;
import com.margarita.vk_app.di.components.DaggerApplicationComponent;
import com.vk.sdk.VKSdk;

public class VkApplication extends Application {

    private static ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        VKSdk.initialize(this);
        initComponent();
    }

    private void initComponent() {
        applicationComponent = DaggerApplicationComponent.builder().build();
    }

    public static ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
