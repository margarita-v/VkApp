package com.margarita.vk_app.ui.fragment.open;

import android.os.Bundle;

import com.margarita.vk_app.ui.fragment.base.BaseFeedFragment;

/**
 * Base class for fragments which show some opened item
 */
abstract class BaseOpenedFragment extends BaseFeedFragment {

    protected int id;

    protected static final String ID_KEY = "id";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasEndlessList(false);
        Bundle args = getArguments();
        if (args != null) {
            this.id = args.getInt(ID_KEY);
        }
    }
}
