package com.margarita.vk_app.rest.model.request;

import com.google.gson.annotations.SerializedName;
import com.margarita.vk_app.common.utils.Utils;
import com.margarita.vk_app.consts.ApiConstants;

import java.util.Map;

public class VideoGetRequest extends BaseRequest {

    @SerializedName(ApiConstants.VIDEOS)
    private String videos;

    public VideoGetRequest(String ownerId, String videoId) {
        this.videos = Utils.concatIds(ownerId, videoId);
    }

    public VideoGetRequest(int ownerId, int videoId) {
        this.videos = Utils.concatIds(ownerId, videoId);
    }

    public VideoGetRequest(String videos) {
        this.videos = videos;
    }

    @Override
    public void onMapCreate(Map<String, String> map) {
        map.put(ApiConstants.VIDEOS, videos);
    }

    public String getVideos() {
        return videos;
    }
}
