package com.margarita.vk_app.rest.api;

import com.margarita.vk_app.rest.model.response.sender.WallGetByIdResponse;
import com.margarita.vk_app.rest.model.response.sender.WallGetResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface WallApi {

    @GET(ApiMethods.WALL_GET)
    Observable<WallGetResponse> get(@QueryMap Map<String, String> map);

    @GET(ApiMethods.WALL_GET_BY_ID)
    Observable<WallGetByIdResponse> getById(@QueryMap Map<String, String> map);
}
