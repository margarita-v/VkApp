package com.margarita.vk_app.common.utils;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.TextView;

public class UIHelper {

    private UIHelper ourInstance;

    private Resources resources;
    private Context context;

    public UIHelper getInstance() {
        return ourInstance != null ? ourInstance : new UIHelper();
    }

    /**
     * Set up text view with its text and visibility
     * @param textView TextView widget
     * @param text Text for TextView
     */
    public static void setUpTextView(TextView textView, String text) {
        textView.setText(text);
        textView.setVisibility(text.isEmpty() ? View.GONE : View.VISIBLE);
    }
}
