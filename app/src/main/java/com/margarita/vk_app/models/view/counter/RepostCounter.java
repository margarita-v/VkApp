package com.margarita.vk_app.models.view.counter;

import com.margarita.vk_app.models.countable.Reposts;

public class RepostCounter extends CounterViewModel {

    private Reposts reposts;

    public RepostCounter(Reposts reposts) {
        super(reposts, true);
        this.reposts = reposts;
    }

    public Reposts getReposts() {
        return reposts;
    }
}
