package com.margarita.vk_app.models.view.attachment;

import com.margarita.vk_app.models.attachment.Link;
import com.margarita.vk_app.models.view.base.BaseViewModel;

/**
 * Base class for all attachments
 */
public abstract class BaseAttachment extends BaseViewModel {

    protected String title;
    protected String url;

    public BaseAttachment(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public BaseAttachment(String defaultTitle, Link link) {
        this.url = link.getUrl();
        String linkTitle = link.getTitle();
        if (linkTitle == null || linkTitle.isEmpty()) {
            String linkName = link.getName();
            this.title = linkName != null ? linkName : defaultTitle;
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
