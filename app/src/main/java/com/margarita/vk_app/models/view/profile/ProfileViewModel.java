package com.margarita.vk_app.models.view.profile;

import com.margarita.vk_app.models.view.BaseViewModel;

/**
 * Base class for view models which contain info about profile
 */
public abstract class ProfileViewModel extends BaseViewModel {

    protected int id;

    protected String name;

    protected String photo;

    ProfileViewModel(int id, String name, String photo) {
        this.id = id;
        this.name = name;
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoto() {
        return photo;
    }
}
