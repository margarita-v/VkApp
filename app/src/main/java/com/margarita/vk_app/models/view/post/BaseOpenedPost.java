package com.margarita.vk_app.models.view.post;

import com.margarita.vk_app.models.common.CommentItem;
import com.margarita.vk_app.models.common.WallItem;
import com.margarita.vk_app.models.view.base.BaseViewModel;

/**
 * Base class for opened posts
 */
public abstract class BaseOpenedPost extends BaseViewModel {

    private int id;
    private String profileName;
    private String profilePhoto;
    private String text;

    BaseOpenedPost(WallItem wallItem) {
        this.id = wallItem.getId();
        this.profileName = wallItem.getSenderName();
        this.profilePhoto = wallItem.getSenderPhoto();
        this.text = wallItem.getText();
    }

    BaseOpenedPost(CommentItem commentItem) {
        this.id = commentItem.getId();
        this.profileName = commentItem.getSenderName();
        this.profilePhoto = commentItem.getSenderPhoto();
        this.text = commentItem.getDisplayText();
    }

    public int getId() {
        return id;
    }

    public String getProfileName() {
        return profileName;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public String getText() {
        return text;
    }
}
