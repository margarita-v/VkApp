package com.margarita.vk_app.rest.model.request.base;

import com.google.gson.annotations.SerializedName;
import com.margarita.vk_app.consts.ApiConstants;
import com.vk.sdk.api.VKApiConst;

import java.util.Map;

public class BaseCountableRequest extends BaseRequest {

    @SerializedName(VKApiConst.COUNT)
    private int count = ApiConstants.DEFAULT_COUNT;

    @SerializedName(VKApiConst.OFFSET)
    private int offset = 0;

    protected BaseCountableRequest() {

    }

    protected BaseCountableRequest(int count, int offset) {
        this.count = count;
        this.offset = offset;
    }

    protected BaseCountableRequest(int offset) {
        this.offset = offset;
    }

    @Override
    public void onMapCreate(Map<String, String> map) {
        putToMap(map, VKApiConst.COUNT, count);
        putToMap(map, VKApiConst.OFFSET, offset);
    }

    public int getCount() {
        return count;
    }

    public int getOffset() {
        return offset;
    }
}
