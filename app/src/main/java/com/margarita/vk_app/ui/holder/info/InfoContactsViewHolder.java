package com.margarita.vk_app.ui.holder.info;

import android.view.View;
import android.widget.RelativeLayout;

import com.margarita.vk_app.R;
import com.margarita.vk_app.models.view.info.InfoContactsViewModel;
import com.margarita.vk_app.ui.holder.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InfoContactsViewHolder extends BaseViewHolder<InfoContactsViewModel> {

    @BindView(R.id.rvContacts)
    RelativeLayout rvContacts;

    public InfoContactsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindViewHolder(InfoContactsViewModel infoContactsViewModel) {

    }

    @Override
    public void unbindViewHolder() {

    }
}
