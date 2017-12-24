package com.margarita.vk_app.rest.model.request.fields;

import com.google.gson.annotations.SerializedName;
import com.margarita.vk_app.rest.model.request.group.BaseGroupRequest;
import com.vk.sdk.api.VKApiConst;

import java.util.Map;

public class GroupsGetByIdRequest extends BaseFieldsRequest {

    @SerializedName(VKApiConst.GROUP_ID)
    private int groupId;

    public GroupsGetByIdRequest(int groupId) {
        this.groupId = BaseGroupRequest.getGroupId(groupId);
    }

    @Override
    public void onMapCreate(Map<String, String> map) {
        super.onMapCreate(map);
        putToMap(map, VKApiConst.GROUP_ID, groupId);
    }

    public int getGroupId() {
        return groupId;
    }
}