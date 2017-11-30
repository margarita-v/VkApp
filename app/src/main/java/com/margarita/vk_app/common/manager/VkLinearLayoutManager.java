package com.margarita.vk_app.common.manager;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

/**
 * Class which checks if we need to load next elements
 */
public class VkLinearLayoutManager extends LinearLayoutManager {

    public VkLinearLayoutManager(Context context) {
        super(context);
    }

    public boolean isOnNextPagePosition() {
        int visibleItemCount = getChildCount();
        int totalItemCount = getItemCount();
        int pastVisibleItems = findFirstVisibleItemPosition();

        return (visibleItemCount + pastVisibleItems) >= totalItemCount / 2;
    }
}
