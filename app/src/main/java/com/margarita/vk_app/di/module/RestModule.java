package com.margarita.vk_app.di.module;

import com.margarita.vk_app.rest.RestClient;
import com.margarita.vk_app.rest.api.WallApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RestModule {

    private RestClient restClient;

    public RestModule() {
        restClient = new RestClient();
    }

    @Provides
    @Singleton
    RestClient provideRestClient() {
        return restClient;
    }

    @Provides
    @Singleton
    WallApi provideWallApi() {
        return restClient.createService(WallApi.class);
    }
}
