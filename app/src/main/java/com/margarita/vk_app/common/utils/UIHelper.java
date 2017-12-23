package com.margarita.vk_app.common.utils;

import android.view.View;
import android.widget.TextView;

import com.margarita.vk_app.R;

//TODO Remove this class
public class UIHelper {

    /**
     * Message for reposts
     */
    private static final String REPOST_MESSAGE = "Поделился";

    /**
     * Set up text view with its text and visibility
     * @param textView TextView widget
     * @param text Text for TextView
     */
    public static void setUpTextViewVisible(TextView textView, String text) {
        textView.setText(text);
        textView.setVisibility(text.isEmpty() ? View.GONE : View.VISIBLE);
    }

    /**
     * Set up text view for display the message
     * @param textView TextView widget
     * @param message Message text
     */
    public static void setUpTextViewMessage(TextView textView, String message) {
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
