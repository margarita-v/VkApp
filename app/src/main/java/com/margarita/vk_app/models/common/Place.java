package com.margarita.vk_app.models.common;

import android.os.Parcel;
import android.os.Parcelable;

import com.margarita.vk_app.common.utils.Utils;

import java.util.Map;

import io.realm.RealmObject;

public class Place extends RealmObject implements Parcelable {

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

    public Place(Map<String, String> source) {
        this.ownerId = source.get(OWNER_ID);
        this.postId = source.get(POST_ID);
    }
    //endregion

    //region Parcelable implementation
    private Place(Parcel in) {
        ownerId = in.readString();
        postId = in.readString();
    }

    public static final Creator<Place> CREATOR = new Creator<Place>() {
        @Override
        public Place createFromParcel(Parcel in) {
            return new Place(in);
        }

        @Override
        public Place[] newArray(int size) {
            return new Place[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(ownerId);
        parcel.writeString(postId);
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

    public String getOwnerId() {
        return ownerId;
    }

    public String getPostId() {
        return postId;
    }
}
