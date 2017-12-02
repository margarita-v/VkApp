package com.margarita.vk_app.rest.api;

import com.margarita.vk_app.models.common.Profile;
import com.margarita.vk_app.rest.model.response.Full;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface UsersApi {

    @GET(ApiMethods.USERS_GET)
    Observable<Full<List<Profile>>> get(@QueryMap Map<String, String> map);
}
