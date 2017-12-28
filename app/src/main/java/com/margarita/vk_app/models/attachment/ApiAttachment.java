package com.margarita.vk_app.models.attachment;

import com.google.gson.annotations.Expose;
import com.margarita.vk_app.models.attachment.doc.VkDocument;
import com.margarita.vk_app.models.attachment.video.Video;
import com.margarita.vk_app.models.view.attachment.AudioAttachment;
import com.margarita.vk_app.models.view.attachment.BaseAttachment;
import com.margarita.vk_app.models.view.attachment.ImageAttachment;
import com.margarita.vk_app.models.view.attachment.PageAttachment;
import com.margarita.vk_app.models.view.attachment.VideoAttachment;
import com.margarita.vk_app.models.view.attachment.doc.DocAttachment;
import com.margarita.vk_app.models.view.attachment.doc.DocImageAttachment;
import com.margarita.vk_app.models.view.attachment.link.LinkAttachment;
import com.margarita.vk_app.models.view.attachment.link.LinkExternal;
import com.vk.sdk.api.model.VKAttachments;

import io.realm.RealmObject;

public class ApiAttachment extends RealmObject {

    @Expose
    private String type;

    @Expose
    private Photo photo;

    @Expose
    private Audio audio;

    @Expose
    private Video video;

    @Expose
    private VkDocument document;

    @Expose
    private Link link;

    @Expose
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
                return null;
        }
    }

    public BaseAttachment getAttachmentViewModel() {
        BaseAttachment baseAttachment = null;
        switch (type) {
            case VKAttachments.TYPE_PHOTO:
                baseAttachment = new ImageAttachment(photo);
                break;
            case VKAttachments.TYPE_AUDIO:
                baseAttachment = new AudioAttachment(audio);
                break;
            case VKAttachments.TYPE_VIDEO:
                baseAttachment = new VideoAttachment(video);
                break;
            case VKAttachments.TYPE_DOC:
                //TODO Hot fix!
                if (document != null) {
                    baseAttachment = document.getPreview() != null
                            ? new DocImageAttachment(document)
                            : new DocAttachment(document);
                }
                break;
            case VKAttachments.TYPE_LINK:
                baseAttachment = link.isExternal()
                        ? new LinkExternal(link)
                        : new LinkAttachment(link);
                break;
            case VKAttachments.TYPE_WIKI_PAGE:
                baseAttachment = new PageAttachment(page);
                break;
        }
        return baseAttachment;
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
