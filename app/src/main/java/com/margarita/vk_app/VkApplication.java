package com.margarita.vk_app;

import android.app.Application;

import com.vk.sdk.VKSdk;

public class VkApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        VKSdk.initialize(this);
    }
}
