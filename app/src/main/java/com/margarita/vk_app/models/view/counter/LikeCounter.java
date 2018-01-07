package com.margarita.vk_app.models.view.counter;

import com.margarita.vk_app.models.countable.Likes;

public class LikeCounter extends CounterViewModel {

    private Likes likes;

    public LikeCounter(Likes likes) {
        super(likes, true);
        this.likes = likes;
    }

    public Likes getLikes() {
        return likes;
    }
}
