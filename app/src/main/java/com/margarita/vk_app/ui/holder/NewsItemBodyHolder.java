package com.margarita.vk_app.ui.holder;

import android.view.View;
import android.widget.TextView;

import com.margarita.vk_app.R;
import com.margarita.vk_app.models.view.NewsFeedItemBody;

public class NewsItemBodyHolder extends BaseViewHolder<NewsFeedItemBody> {

    private TextView textView;

    public NewsItemBodyHolder(View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.tvText);
    }

    @Override
    public void bindViewHolder(NewsFeedItemBody newsFeedItemBody) {
        textView.setText(newsFeedItemBody.getText());
    }

    @Override
    public void unbindViewHolder() {
        textView.setText(null);
    }

    public TextView getTextView() {
        return textView;
    }
}