package com.margarita.vk_app.rest.model.request.group;

import com.google.gson.annotations.SerializedName;
import com.margarita.vk_app.consts.ApiConstants;
import com.vk.sdk.api.VKApiConst;

import java.util.Map;

public class GroupsGetMembersRequest extends BaseGroupRequest {

    @SerializedName(VKApiConst.FIELDS)
    private String fields = ApiConstants.DEFAULT_MEMBERS_FIELDS;

    public GroupsGetMembersRequest(int groupId, int count, int offset) {
        super(groupId, count, offset);
    }

    @Override
    public void onMapCreate(Map<String, String> map) {
        super.onMapCreate(map);
        map.put(VKApiConst.FIELDS, fields);
    }

    public String getFields() {
        return fields;
    }
}
