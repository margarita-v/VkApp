package com.margarita.vk_app.models.view.counter;

import com.margarita.vk_app.R;
import com.margarita.vk_app.models.countable.Countable;

public class CounterViewModel implements Countable {

    protected int count;

    private int iconColor, textColor;

    CounterViewModel(Countable countable) {
        this.count = countable != null ? countable.getCount() : 0;
        setColor(this.count > 0 ? R.color.colorIcon : R.color.colorIconDisabled);
    }

    @Override
    public int getCount() {
        return count;
    }

    /**
     * Set icon color which depends on icon state
     * @param colorRes Resource ID for icon color
     */
    protected void setColor(int colorRes) {
        iconColor = colorRes;
        textColor = colorRes;
    }

    /**
     * Function for setting an accent color to the view model if some condition returns True
     * @param condition Condition for setting an accent color
     */
    void setAccentColor(boolean condition) {
        if (condition)
            setColor(R.color.colorAccent);
    }

    public int getIconColor() {
        return iconColor;
    }

    public int getTextColor() {
        return textColor;
    }
}
