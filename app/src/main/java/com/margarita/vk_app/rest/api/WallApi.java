package com.margarita.vk_app.rest.api;

import com.margarita.vk_app.rest.model.response.WallGetResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WallApi {

    @GET(ApiMethods.WALL_GET)
    Call<WallGetResponse> get(@Query("owner_id") String ownerId,
                              @Query("access_token") String accessToken,
                              @Query("extended") Integer extended,
                              @Query("v") String version);
}
