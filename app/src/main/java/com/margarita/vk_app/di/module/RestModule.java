package com.margarita.vk_app.di.module;

import com.margarita.vk_app.rest.RestClient;
import com.margarita.vk_app.rest.api.BoardApi;
import com.margarita.vk_app.rest.api.GroupsApi;
import com.margarita.vk_app.rest.api.UsersApi;
import com.margarita.vk_app.rest.api.VideoApi;
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

    @Provides
    @Singleton
    UsersApi provideUsersApi() {
        return restClient.createService(UsersApi.class);
    }

    @Provides
    @Singleton
    GroupsApi provideGroupsApi() {
        return restClient.createService(GroupsApi.class);
    }

    @Provides
    @Singleton
    BoardApi provideBoardApi() {
        return restClient.createService(BoardApi.class);
    }

    @Provides
    @Singleton
    public VideoApi provideVideoApi() {
        return restClient.createService(VideoApi.class);
    }
}
