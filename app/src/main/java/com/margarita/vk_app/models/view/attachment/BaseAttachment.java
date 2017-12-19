package com.margarita.vk_app.models.view.attachment;

import com.margarita.vk_app.common.utils.Utils;
import com.margarita.vk_app.models.attachment.Link;
import com.margarita.vk_app.models.attachment.doc.VkDocument;
import com.margarita.vk_app.models.attachment.video.Video;
import com.margarita.vk_app.models.view.base.BaseViewModel;

public abstract class BaseAttachment extends BaseViewModel {

    protected String title;
    protected String url;

    public BaseAttachment(String title, String url) {
        this.title = title;
        this.url = url;
    }

    BaseAttachment(Video video) {
        String videoTitle = video.getTitle();
        this.title = videoTitle.isEmpty() ? getDefaultTitle() : videoTitle;
        this.url = video.getPhoto320();
    }

    public BaseAttachment(Link link) {
        this.url = link.getUrl();
        String linkTitle = link.getTitle();
        if (linkTitle == null || linkTitle.isEmpty()) {
            String linkName = link.getName();
            this.title = linkName != null ? linkName : getDefaultTitle();
        } else
            this.title = linkTitle;
    }

    public BaseAttachment(VkDocument document) {
        String docTitle = document.getTitle();
        this.title = docTitle.isEmpty()
                ? getDefaultTitle() : Utils.removeExtFromText(docTitle);
        this.url = document.getUrl();
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    protected abstract String getDefaultTitle();
}
