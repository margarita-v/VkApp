package com.margarita.vk_app.models.view.counter;

import com.margarita.vk_app.R;
import com.margarita.vk_app.models.common.Reposts;

public class RepostCounter extends CounterViewModel {

    private Reposts reposts;

    public RepostCounter(Reposts reposts) {
        super(reposts.getCount());
        this.reposts = reposts;

        if (reposts.getUserReposted() == 1)
            setColor(R.color.colorAccent);
    }

    public Reposts getReposts() {
        return reposts;
    }
}
