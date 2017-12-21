package com.margarita.vk_app.rest.model.request;

import com.google.gson.annotations.SerializedName;
import com.margarita.vk_app.consts.ApiConstants;

import java.util.Map;

public class WallGetCommentsRequest extends BaseRequest {

    @SerializedName(ApiConstants.OWNER_ID)
    private int ownerId;

    @SerializedName(ApiConstants.POST_ID)
    private int postId;

    @SerializedName(ApiConstants.COUNT)
    private int count = ApiConstants.DEFAULT_COUNT;

    @SerializedName(ApiConstants.OFFSET)
    private int offset = 0;

    @SerializedName(ApiConstants.EXTENDED)
    private int extended = 1;

    @SerializedName(ApiConstants.NEED_LIKES)
    private int needLikes = 1;

    public WallGetCommentsRequest(int ownerId, int postId) {
        this.ownerId = ownerId;
        this.postId = postId;
    }

    public WallGetCommentsRequest(int ownerId, int postId, int offset) {
        this(ownerId, postId);
        this.offset = offset;
    }

    //region Getters
    public int getOwnerId() {
        return ownerId;
    }

    public int getPostId() {
        return postId;
    }

    public int getCount() {
        return count;
    }

    public int getOffset() {
        return offset;
    }

    public int getExtended() {
        return extended;
    }

    public int getNeedLikes() {
        return needLikes;
    }
    //endregion

    @Override
    public void onMapCreate(Map<String, String> map) {
        putToMap(map, ApiConstants.OWNER_ID, ownerId);
        putToMap(map, ApiConstants.POST_ID, postId);
        putToMap(map, ApiConstants.COUNT, count);
        putToMap(map, ApiConstants.OFFSET, offset);
        putToMap(map, ApiConstants.EXTENDED, extended);
        putToMap(map, ApiConstants.NEED_LIKES, needLikes);
    }
}
