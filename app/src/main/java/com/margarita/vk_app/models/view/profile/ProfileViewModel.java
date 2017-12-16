package com.margarita.vk_app.models.view.profile;

import com.margarita.vk_app.models.view.base.BaseIdModel;

/**
 * Base class for view models which contain info about profile
 */
public abstract class ProfileViewModel extends BaseIdModel {

    protected String name;
    protected String photo;

    ProfileViewModel(int id, String name, String photo) {
        super(id);
        this.name = name;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public String getPhoto() {
        return photo;
    }
}
