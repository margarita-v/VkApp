package com.margarita.vk_app.rest.model.request;

import com.google.gson.annotations.SerializedName;
import com.margarita.vk_app.consts.ApiConstants;
import com.vk.sdk.api.VKApiConst;

import java.util.Map;

public class WallGetRequest extends BaseRequest {

    @SerializedName(VKApiConst.OWNER_ID)
    private int ownerId;

    @SerializedName(VKApiConst.COUNT)
    private int count = ApiConstants.DEFAULT_COUNT;

    @SerializedName(VKApiConst.OFFSET)
    private int offset;

    // Для возможности получения массива из profiles и groups
    @SerializedName(VKApiConst.EXTENDED)
    private int extended = 1;

    public WallGetRequest(int ownerId) {
        this.ownerId = ownerId;
    }

    public WallGetRequest(int ownerId, int count, int offset) {
        this.ownerId = ownerId;
        this.count = count;
        this.offset = offset;
    }

    //region Getters and setters
    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getExtended() {
        return extended;
    }

    public void setExtended(int extended) {
        this.extended = extended;
    }
    //endregion

    @Override
    public void onMapCreate(Map<String, String> map) {
        putToMap(map, VKApiConst.OWNER_ID, ownerId);
        putToMap(map, VKApiConst.COUNT, count);
        putToMap(map, VKApiConst.OFFSET, offset);
        putToMap(map, VKApiConst.EXTENDED, extended);
    }
}
