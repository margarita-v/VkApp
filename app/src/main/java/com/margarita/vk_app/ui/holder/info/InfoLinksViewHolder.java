package com.margarita.vk_app.ui.holder.info;

import android.view.View;
import android.widget.RelativeLayout;

import com.margarita.vk_app.R;
import com.margarita.vk_app.models.view.info.InfoLinksViewModel;
import com.margarita.vk_app.ui.holder.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InfoLinksViewHolder extends BaseViewHolder<InfoLinksViewModel> {

    @BindView(R.id.rvLinks)
    RelativeLayout rvLinks;

    public InfoLinksViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindViewHolder(InfoLinksViewModel infoLinksViewModel) {

    }

    @Override
    public void unbindViewHolder() {

    }
}
