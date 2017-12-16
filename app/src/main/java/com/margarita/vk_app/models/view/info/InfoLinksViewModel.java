package com.margarita.vk_app.models.view.info;

import android.view.View;

import com.margarita.vk_app.models.LayoutTypes;
import com.margarita.vk_app.models.view.base.BaseViewModel;
import com.margarita.vk_app.ui.holder.BaseViewHolder;
import com.margarita.vk_app.ui.holder.info.InfoLinksViewHolder;

public class InfoLinksViewModel extends BaseViewModel {

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new InfoLinksViewHolder(view);
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.InfoLinks;
    }
}
