package com.margarita.vk_app.models.view.counter;

import com.margarita.vk_app.models.countable.Reposts;

public class RepostCounter extends CounterViewModel {

    private Reposts reposts;

    public RepostCounter(Reposts reposts) {
        super(reposts);
        this.reposts = reposts;
        setAccentColor(this.reposts != null && this.reposts.isUserReposted());
    }

    public Reposts getReposts() {
        return reposts;
    }
}
