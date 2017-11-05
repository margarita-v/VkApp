package com.margarita.vk_app.di.module;

import com.margarita.vk_app.rest.RestClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RestModule {

    private RestClient restClient;

    public RestModule() {
        this.restClient = new RestClient();
    }

    @Provides
    @Singleton
    public RestClient provideRestClient() {
        return this.restClient;
    }
}
