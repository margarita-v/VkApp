package com.margarita.vk_app.rest.api;

import com.margarita.vk_app.rest.model.response.common.MemberGetResponse;
import com.margarita.vk_app.rest.model.response.list.GroupsGetResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface GroupsApi {

    @GET(ApiMethods.GROUPS_GET_MEMBERS)
    Observable<MemberGetResponse> getMembers(@QueryMap Map<String, String> map);

    @GET(ApiMethods.GROUPS_GET_BY_ID)
    Observable<GroupsGetResponse> getById(@QueryMap Map<String, String> map);
}
