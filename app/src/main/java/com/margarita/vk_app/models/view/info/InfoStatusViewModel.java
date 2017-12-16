package com.margarita.vk_app.models.view.info;

import android.view.View;

import com.margarita.vk_app.models.LayoutTypes;
import com.margarita.vk_app.models.common.Group;
import com.margarita.vk_app.models.view.base.BaseViewModel;
import com.margarita.vk_app.ui.holder.BaseViewHolder;
import com.margarita.vk_app.ui.holder.info.InfoStatusViewHolder;

public class InfoStatusViewModel extends BaseViewModel {

    private String status;
    private String description;
    private String site;

    public InfoStatusViewModel(Group group) {
        this.status = group.getStatus();
        this.description = group.getDescription();
        this.site = group.getSite();
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new InfoStatusViewHolder(view);
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.InfoStatus;
    }

    public String getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public String getSite() {
        return site;
    }
}
