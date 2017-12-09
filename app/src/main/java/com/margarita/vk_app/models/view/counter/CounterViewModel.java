package com.margarita.vk_app.models.view.counter;

import com.margarita.vk_app.R;

public class CounterViewModel {

    protected int count;

    private int iconColor, textColor;

    CounterViewModel(int count) {
        this.count = count;
        setColor(this.count > 0 ? R.color.colorIcon : R.color.colorIconDisabled);
    }

    /**
     * Set icon color which depends on icon state
     * @param colorRes Resource ID for icon color
     */
    protected void setColor(int colorRes) {
        iconColor = colorRes;
        textColor = colorRes;
    }

    public int getCount() {
        return count;
    }

    public int getIconColor() {
        return iconColor;
    }

    public int getTextColor() {
        return textColor;
    }
}
