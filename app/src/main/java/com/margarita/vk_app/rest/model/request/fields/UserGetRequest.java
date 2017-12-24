package com.margarita.vk_app.rest.model.request.fields;

import com.google.gson.annotations.SerializedName;
import com.vk.sdk.api.VKApiConst;

import java.util.Map;

public class UserGetRequest extends BaseFieldsRequest {

    @SerializedName(VKApiConst.USER_IDS)
    private String userIds;

    public UserGetRequest(String userIds) {
        this.userIds = userIds;
    }

    @Override
    public void onMapCreate(Map<String, String> map) {
        super.onMapCreate(map);
        map.put(VKApiConst.USER_IDS, userIds);
    }

    public String getUserIds() {
        return userIds;
    }
}
