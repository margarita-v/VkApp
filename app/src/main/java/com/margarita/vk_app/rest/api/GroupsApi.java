package com.margarita.vk_app.rest.api;

import com.margarita.vk_app.rest.model.response.MemberGetResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface GroupsApi {

    @GET(ApiMethods.GROUPS_GET_MEMBERS)
    Observable<MemberGetResponse> getMembers(@QueryMap Map<String, String> map);
}
