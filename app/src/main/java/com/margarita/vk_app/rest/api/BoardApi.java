package com.margarita.vk_app.rest.api;

import com.margarita.vk_app.rest.model.response.common.TopicGetResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface BoardApi {

    @GET(ApiMethods.BOARD_GET_TOPICS)
    Observable<TopicGetResponse> getTopics(@QueryMap Map<String, String> map);
}
