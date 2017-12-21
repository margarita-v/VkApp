package com.margarita.vk_app.models.common;

import android.os.Bundle;
import android.util.Log;

import com.margarita.vk_app.common.utils.Utils;

import java.util.Map;

import io.realm.RealmObject;

public class Place extends RealmObject {

    private static final String PLACE = "place";
    private static final String OWNER_ID = "owner_id";
    private static final String POST_ID = "post_id";

    private String ownerId;
    private String postId;

    //region Constructors
    public Place() {
    }

    public Place(String source) {
        String[] place = Utils.splitIds(source);
        this.ownerId = place[0];
        this.postId = place[1];
    }

    public Place(String ownerId, String postId) {
        this.ownerId = ownerId;
        this.postId = postId;
    }

    public Place(Bundle source) {
        this.ownerId = source.getString(OWNER_ID);
        this.postId = source.getString(POST_ID);
    }

    public Place(Map<String, String> source) {
        this.ownerId = source.get(OWNER_ID);
        this.postId = source.get(POST_ID);
    }
    //endregion

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Place)) return false;
        Place other = (Place) o;
        return other.ownerId.equals(ownerId)
                && other.postId.equals(postId);
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        Log.d(PLACE, "toBundle. from: " + ownerId + " post: " + postId);
        bundle.putString(OWNER_ID, this.ownerId);
        bundle.putString(POST_ID, this.postId);
        return bundle;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getPostId() {
        return postId;
    }
}
