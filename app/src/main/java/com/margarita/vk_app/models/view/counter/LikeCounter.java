package com.margarita.vk_app.models.view.counter;

import com.margarita.vk_app.models.countable.Likes;

public class LikeCounter extends CounterViewModel {

    private Likes likes;

    public LikeCounter(Likes likes) {
        super(likes);
        this.likes = likes;
        setAccentColor(this.likes != null && this.likes.isUserLikes());
    }

    public Likes getLikes() {
        return likes;
    }
}
