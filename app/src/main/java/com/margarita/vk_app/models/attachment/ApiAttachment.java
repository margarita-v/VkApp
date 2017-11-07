package com.margarita.vk_app.models.attachment;

import com.google.gson.annotations.Expose;
import com.vk.sdk.api.model.VKAttachments;

import java.util.NoSuchElementException;

public class ApiAttachment {

    @Expose
    private String type;

    @Expose
    private Photo photo;

    private Audio audio;

    private Video video;

    private VkDocument document;

    private Link link;

    private Page page;

    public Attachment getAttachment() {
        switch (type) {
            case VKAttachments.TYPE_PHOTO:
                return photo;
            case VKAttachments.TYPE_AUDIO:
                return audio;
            case VKAttachments.TYPE_VIDEO:
                return video;
            case VKAttachments.TYPE_DOC:
                return document;
            case VKAttachments.TYPE_LINK:
                return link;
            case VKAttachments.TYPE_WIKI_PAGE:
                return page;
            default:
                throw new NoSuchElementException(
                        "Attachment type " + type + " is not supported");
        }
    }

    public String getType() {
        return type;
    }

    public Photo getPhoto() {
        return photo;
    }

    public Audio getAudio() {
        return audio;
    }

    public Video getVideo() {
        return video;
    }

    public VkDocument getDocument() {
        return document;
    }

    public Link getLink() {
        return link;
    }

    public Page getPage() {
        return page;
    }
}
