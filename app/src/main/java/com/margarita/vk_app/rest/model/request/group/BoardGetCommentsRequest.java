package com.margarita.vk_app.rest.model.request.group;

import com.google.gson.annotations.SerializedName;
import com.margarita.vk_app.consts.ApiConstants;

import java.util.Map;

public class BoardGetCommentsRequest extends BaseGroupRequest {

    @SerializedName(ApiConstants.TOPIC_ID)
    private int topicId;

    @SerializedName(ApiConstants.EXTENDED)
    private int extended = 1;

    @SerializedName(ApiConstants.NEED_LIKES)
    private int needLikes = 1;

    public BoardGetCommentsRequest(int groupId, int topicId) {
        super(groupId);
        this.topicId = topicId;
    }

    public BoardGetCommentsRequest(int groupId, int topicId, int offset) {
        super(groupId, offset);
        this.topicId = topicId;
    }

    @Override
    public void onMapCreate(Map<String, String> map) {
        super.onMapCreate(map);
        putToMap(map, ApiConstants.TOPIC_ID, topicId);
        putToMap(map, ApiConstants.EXTENDED, extended);
        putToMap(map, ApiConstants.NEED_LIKES, needLikes);
    }

    public int getTopicId() {
        return topicId;
    }

    public int getExtended() {
        return extended;
    }

    public int getNeedLikes() {
        return needLikes;
    }
}
