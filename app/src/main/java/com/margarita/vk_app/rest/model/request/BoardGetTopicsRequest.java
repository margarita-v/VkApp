package com.margarita.vk_app.rest.model.request;

import com.google.gson.annotations.SerializedName;
import com.margarita.vk_app.consts.ApiConstants;
import com.vk.sdk.api.VKApiConst;

import java.util.Map;

public class BoardGetTopicsRequest extends BaseRequest {

    @SerializedName(VKApiConst.GROUP_ID)
    private int groupId;

    @SerializedName(VKApiConst.COUNT)
    private int count = ApiConstants.DEFAULT_COUNT;

    @SerializedName(VKApiConst.OFFSET)
    private int offset = 0;

    private BoardGetTopicsRequest(int groupId) {
        this.groupId = Math.abs(groupId);
    }

    public BoardGetTopicsRequest(int groupId, int count, int offset) {
        this(groupId);
        this.count = count;
        this.offset = offset;
    }

    //region Getters
    public int getGroupId() {
        return groupId;
    }

    public int getCount() {
        return count;
    }

    public int getOffset() {
        return offset;
    }
    //endregion

    @Override
    public void onMapCreate(Map<String, String> map) {
        putToMap(map, VKApiConst.GROUP_ID, groupId);
        putToMap(map, VKApiConst.COUNT, count);
        putToMap(map, VKApiConst.OFFSET, offset);
    }
}
