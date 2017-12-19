package com.margarita.vk_app.models.view.attachment.link;

import com.margarita.vk_app.models.attachment.Link;
import com.margarita.vk_app.models.view.base.BaseViewModel;

/**
 * Base class for link attachments
 */
public abstract class BaseLinkAttachment extends BaseViewModel {

    private String title;
    private String url;

    private static final String LINK_TITLE = "Link";

    BaseLinkAttachment(Link link) {
        this.url = link.getUrl();
        String linkTitle = link.getTitle();
        if (linkTitle == null || linkTitle.isEmpty()) {
            String linkName = link.getName();
            this.title = linkName != null ? linkName : LINK_TITLE;
        } else
            this.title = linkTitle;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }
}
