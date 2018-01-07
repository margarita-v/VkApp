package com.margarita.vk_app.models.view.counter;

import com.margarita.vk_app.models.countable.Comments;

public class CommentsCounter extends CounterViewModel {

    private Comments comments;

    public CommentsCounter(Comments comments) {
        super(comments, false);
        this.comments = comments;
    }

    public Comments getComments() {
        return comments;
    }
}
