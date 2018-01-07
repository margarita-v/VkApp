package com.margarita.vk_app.models.view.counter;

import com.margarita.vk_app.R;
import com.margarita.vk_app.models.Countable;
import com.margarita.vk_app.models.countable.BaseAction;

public class CounterViewModel implements Countable {

    protected int count;

    private int iconColor, textColor;

    CounterViewModel(BaseAction countable, boolean checkForAction) {
        boolean isNotNullable = countable != null;
        this.count = isNotNullable ? countable.getCount() : 0;

        if (checkForAction && isNotNullable && countable.isUserPerformed())
            setColor(R.color.colorAccent);
        else
            setColor(isNotNullable ? R.color.colorIcon : R.color.colorIconDisabled);
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

    public int getIconColor() {
        return iconColor;
    }

    public int getTextColor() {
        return textColor;
    }
}
