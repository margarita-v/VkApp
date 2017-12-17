package com.margarita.vk_app.rest.api;

import com.margarita.vk_app.rest.model.response.common.VideoGetResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface VideoApi {

    @GET(ApiMethods.VIDEO_GET)
    Observable<VideoGetResponse> get(@QueryMap Map<String, String> map);
}
