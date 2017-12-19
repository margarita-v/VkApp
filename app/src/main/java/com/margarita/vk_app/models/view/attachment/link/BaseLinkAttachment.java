package com.margarita.vk_app.models.view.attachment.link;

import com.margarita.vk_app.models.attachment.Link;
import com.margarita.vk_app.models.view.attachment.BaseAttachment;

/**
 * Base class for link attachments
 */
public abstract class BaseLinkAttachment extends BaseAttachment {

    private static final String LINK_TITLE = "Link";

    BaseLinkAttachment(Link link) {
        super(LINK_TITLE, link);
    }
}
