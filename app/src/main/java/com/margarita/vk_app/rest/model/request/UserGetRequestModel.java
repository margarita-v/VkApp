package com.margarita.vk_app.rest.model.request;

import com.google.gson.annotations.SerializedName;
import com.margarita.vk_app.consts.ApiConstants;
import com.vk.sdk.api.VKApiConst;

import java.util.Map;

public class UserGetRequestModel extends BaseRequestModel {

    @SerializedName(VKApiConst.USER_IDS)
    private String userIds;

    @SerializedName(VKApiConst.FIELDS)
    private String fields = ApiConstants.DEFAULT_USER_FIELDS;

    public UserGetRequestModel(String userIds) {
        this.userIds = userIds;
    }

    public String getUserIds() {
        return userIds;
    }

    public String getFields() {
        return fields;
    }

    @Override
    public void onMapCreate(Map<String, String> map) {
        map.put(VKApiConst.USER_ID, userIds);
        map.put(VKApiConst.FIELDS, fields);
    }
}
