package com.margarita.vk_app.ui.holder.info;

import android.view.View;
import android.widget.TextView;

import com.margarita.vk_app.R;
import com.margarita.vk_app.models.view.info.InfoStatusViewModel;
import com.margarita.vk_app.ui.holder.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InfoStatusViewHolder extends BaseViewHolder<InfoStatusViewModel> {

    @BindView(R.id.tvStatusText)
    TextView tvStatusText;

    @BindView(R.id.tvDescriptionText)
    TextView tvDescriptionText;

    @BindView(R.id.tvSiteText)
    TextView tvSiteText;

    public InfoStatusViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindViewHolder(InfoStatusViewModel item) {
        tvStatusText.setText(item.getStatus());
        tvDescriptionText.setText(item.getDescription());
        tvSiteText.setText(item.getSite());
    }

    @Override
    public void unbindViewHolder() {
        clearTextView(tvStatusText);
        clearTextView(tvDescriptionText);
        clearTextView(tvSiteText);
    }
}
