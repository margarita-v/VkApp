package com.margarita.vk_app.rest.model.request.owner;

public class WallGetRequest extends BaseOwnerRequest {

    public WallGetRequest(int ownerId, int count, int offset) {
        super(ownerId, count, offset);
    }
}
