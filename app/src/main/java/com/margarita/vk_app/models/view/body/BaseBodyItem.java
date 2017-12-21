package com.margarita.vk_app.models.view.body;

import com.margarita.vk_app.models.common.CommentItem;
import com.margarita.vk_app.models.common.WallItem;
import com.margarita.vk_app.models.view.base.BaseIdModel;

/**
 * Base class for items which represent some item's body
 */
public abstract class BaseBodyItem extends BaseIdModel {

    private String text;
    private String attachmentsString;

    BaseBodyItem(CommentItem commentItem) {
        super(commentItem.getId());
        this.text = commentItem.getDisplayText();
        this.attachmentsString = commentItem.getDisplayAttachmentsString();
    }

    BaseBodyItem(WallItem wallItem) {
        super(wallItem.getId());
        if (wallItem.hasSharedRepost()) {
            this.text = wallItem.getSharedRepost().getText();
            this.attachmentsString = wallItem.getSharedRepost().getAttachmentsString();
        } else {
            this.text = wallItem.getText();
            this.attachmentsString = wallItem.getAttachmentsString();
        }
    }

    public String getText() {
        return text;
    }

    public String getAttachmentsString() {
        return attachmentsString;
    }

    @Override
    public boolean isItemDecorator() {
        return true;
    }
}
