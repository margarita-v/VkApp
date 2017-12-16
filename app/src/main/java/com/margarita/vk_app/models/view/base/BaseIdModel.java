package com.margarita.vk_app.models.view.base;

/**
 * Class for view models with ID field
 */
public abstract class BaseIdModel extends BaseViewModel {

    private int id;

    public BaseIdModel(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
