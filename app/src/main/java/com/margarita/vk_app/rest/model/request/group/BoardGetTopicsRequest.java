package com.margarita.vk_app.rest.model.request.group;

public class BoardGetTopicsRequest extends BaseGroupRequest {

    public BoardGetTopicsRequest(int groupId, int count, int offset) {
        super(groupId, count, offset);
    }
}
