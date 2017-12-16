package com.margarita.vk_app.rest.model.request;

import com.google.gson.annotations.SerializedName;
import com.margarita.vk_app.consts.ApiConstants;

import java.util.Map;

public class WallGetByIdRequest extends BaseRequest {

    @SerializedName(ApiConstants.POSTS)
    private String posts;

    @SerializedName(ApiConstants.EXTENDED)
    private int extended = 1;

    private static final String DIVIDER = "_";

    public WallGetByIdRequest(int ownerId, int postId) {
        this.posts = ownerId + DIVIDER + postId;
    }

    @Override
    public void onMapCreate(Map<String, String> map) {
        map.put(ApiConstants.POSTS, posts);
        putToMap(map, ApiConstants.EXTENDED, extended);
    }

    //region Getters
    public String getPosts() {
        return posts;
    }

    public int getExtended() {
        return extended;
    }
    //endregion
}
