package com.margarita.vk_app.rest.model.request;

import com.google.gson.annotations.SerializedName;
import com.margarita.vk_app.consts.ApiConstants;
import com.vk.sdk.api.VKApiConst;

import java.util.Map;

public class GroupsGetByIdRequest extends BaseRequest {

    @SerializedName(VKApiConst.GROUP_ID)
    private int groupId;

    @SerializedName(VKApiConst.FIELDS)
    private String fields = ApiConstants.DEFAULT_GROUP_FIELDS;

    public GroupsGetByIdRequest(int groupId) {
        this.groupId = Math.abs(groupId);
    }

    //region Getters
    public int getGroupId() {
        return groupId;
    }

    public String getFields() {
        return fields;
    }
    //endregion

    @Override
    public void onMapCreate(Map<String, String> map) {
        putToMap(map, VKApiConst.GROUP_ID, groupId);
        map.put(VKApiConst.FIELDS, fields);
    }
}