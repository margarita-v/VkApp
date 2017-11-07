package com.margarita.vk_app.models.view.counter;

import com.margarita.vk_app.R;
import com.margarita.vk_app.models.common.Likes;

public class LikeCounter extends CounterViewModel {

    private Likes likes;

    public LikeCounter(Likes likes) {
        super(likes.getCount());
        this.likes = likes;

        if (this.likes.getUserLikes() == 1)
            setColor(R.color.colorAccent);
    }

    public Likes getLikes() {
        return likes;
    }
}
