package com.margarita.vk_app.ui.fragment.comments;

import android.os.Bundle;

import com.margarita.vk_app.R;
import com.margarita.vk_app.models.common.Place;
import com.margarita.vk_app.ui.fragment.base.BaseFeedFragment;

/**
 * Base class for fragments which show comments
 */
abstract class BaseCommentsFragment extends BaseFeedFragment {

    protected Place place;

    /**
     * Key for bundle
     */
    protected static final String PLACE_KEY = "Place";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null)
            place = getArguments().getParcelable(PLACE_KEY);
    }

    @Override
    protected int onCreateToolbarTitle() {
        return R.string.screen_name_comments;
    }
}
