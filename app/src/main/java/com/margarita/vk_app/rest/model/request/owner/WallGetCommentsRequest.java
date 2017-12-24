package com.margarita.vk_app.rest.model.request.owner;

import com.google.gson.annotations.SerializedName;
import com.margarita.vk_app.consts.ApiConstants;
import com.vk.sdk.api.VKApiConst;

import java.util.Map;

public class WallGetCommentsRequest extends BaseOwnerRequest {

    @SerializedName(VKApiConst.POST_ID)
    private int postId;

    @SerializedName(ApiConstants.NEED_LIKES)
    private int needLikes = 1;

    public WallGetCommentsRequest(int ownerId, int offset, int postId) {
        super(ownerId, offset);
        this.postId = postId;
    }

    //region Getters
    public int getPostId() {
        return postId;
    }

    public int getNeedLikes() {
        return needLikes;
    }
    //endregion

    @Override
    public void onMapCreate(Map<String, String> map) {
        super.onMapCreate(map);
        putToMap(map, ApiConstants.POST_ID, postId);
        putToMap(map, ApiConstants.NEED_LIKES, needLikes);
    }
}
