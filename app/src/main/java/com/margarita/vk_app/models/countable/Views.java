
package com.margarita.vk_app.models.countable;

import com.google.gson.annotations.Expose;

import io.realm.RealmObject;

public class Views extends RealmObject implements Countable{

    @Expose
    private Integer count;

    @Override
    public int getCount() {
        return count;
    }
}
