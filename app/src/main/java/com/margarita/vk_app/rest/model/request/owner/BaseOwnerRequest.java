package com.margarita.vk_app.rest.model.request.owner;

import com.google.gson.annotations.SerializedName;
import com.margarita.vk_app.rest.model.request.base.BaseCountableRequest;
import com.vk.sdk.api.VKApiConst;

import java.util.Map;

public class BaseOwnerRequest extends BaseCountableRequest {

    /**
     * For getting an array from profiles and groups
     */
    @SerializedName(VKApiConst.EXTENDED)
    private int extended = 1;

    @SerializedName(VKApiConst.OWNER_ID)
    private int ownerId;

    BaseOwnerRequest(int ownerId, int count, int offset) {
        super(count, offset);
        this.ownerId = ownerId;
    }

    BaseOwnerRequest(int ownerId, int offset) {
        super(offset);
        this.ownerId = ownerId;
    }

    @Override
    public void onMapCreate(Map<String, String> map) {
        super.onMapCreate(map);
        putToMap(map, VKApiConst.EXTENDED, extended);
        putToMap(map, VKApiConst.OWNER_ID, ownerId);
    }

    public int getExtended() {
        return extended;
    }

    public int getOwnerId() {
        return ownerId;
    }
}
