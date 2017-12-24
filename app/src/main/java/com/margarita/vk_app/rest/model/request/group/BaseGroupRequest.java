package com.margarita.vk_app.rest.model.request.group;

import com.google.gson.annotations.SerializedName;
import com.margarita.vk_app.rest.model.request.countable.BaseCountableRequest;
import com.vk.sdk.api.VKApiConst;

import java.util.Map;

public class BaseGroupRequest extends BaseCountableRequest {

    @SerializedName(VKApiConst.GROUP_ID)
    private int groupId;

    protected BaseGroupRequest(int groupId, int count, int offset) {
        super(count, offset);
        this.groupId = getGroupId(groupId);
    }

    @Override
    public void onMapCreate(Map<String, String> map) {
        super.onMapCreate(map);
        putToMap(map, VKApiConst.GROUP_ID, groupId);
    }

    public int getGroupId() {
        return groupId;
    }

    public static int getGroupId(int groupId) {
        return Math.abs(groupId);
    }
}
