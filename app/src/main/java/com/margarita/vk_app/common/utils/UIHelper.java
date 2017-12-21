package com.margarita.vk_app.common.utils;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.TextView;

import com.margarita.vk_app.R;

//TODO Remove this class
public class UIHelper {

    private UIHelper ourInstance;

    private Resources resources;
    private Context context;

    /**
     * Message for reposts
     */
    private static final String REPOST_MESSAGE = "Поделился";

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

    /**
     * Set up text view for display the message for reposts
     * @param textView TextView widget
     * @param message Repost message
     */
    public static void setUpReposts(TextView textView, String message) {
        int color;
        if (!message.isEmpty()) {
            textView.setVisibility(View.VISIBLE);
            textView.setText(message);
            color = android.R.color.primary_text_light;
        } else {
            textView.setText(REPOST_MESSAGE);
            color = R.color.colorIcon;
        }
        textView.setTextColor(textView.getResources().getColor(color));
    }
}
