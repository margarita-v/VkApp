package com.margarita.vk_app.di.module;

import com.margarita.vk_app.common.manager.NetworkManager;
import com.margarita.vk_app.common.manager.VkFragmentManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ManagerModule {

    @Provides
    @Singleton
    VkFragmentManager provideFragmentManager() {
        return new VkFragmentManager();
    }

    @Provides
    @Singleton
    NetworkManager provideNetworkManager() {
        return new NetworkManager();
    }
}
