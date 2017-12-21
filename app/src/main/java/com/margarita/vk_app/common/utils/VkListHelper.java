package com.margarita.vk_app.common.utils;

import com.margarita.vk_app.models.Owner;
import com.margarita.vk_app.models.attachment.ApiAttachment;
import com.margarita.vk_app.models.attachment.Link;
import com.margarita.vk_app.models.attachment.doc.VkDocument;
import com.margarita.vk_app.models.common.CommentItem;
import com.margarita.vk_app.models.common.WallItem;
import com.margarita.vk_app.models.view.attachment.AudioAttachment;
import com.margarita.vk_app.models.view.attachment.BaseAttachment;
import com.margarita.vk_app.models.view.attachment.ImageAttachment;
import com.margarita.vk_app.models.view.attachment.PageAttachment;
import com.margarita.vk_app.models.view.attachment.VideoAttachment;
import com.margarita.vk_app.models.view.attachment.doc.DocAttachment;
import com.margarita.vk_app.models.view.attachment.doc.DocImageAttachment;
import com.margarita.vk_app.models.view.attachment.link.LinkAttachment;
import com.margarita.vk_app.models.view.attachment.link.LinkExternal;
import com.margarita.vk_app.rest.model.response.base.ItemWithSendersResponse;
import com.vk.sdk.api.model.VKAttachments;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class VkListHelper {

    /**
     * Function for set sender's name and photo for all wall items in response.
     * @param response Response which contains info about sender
     * @return WallItem list with sender info
     */
    public static List<WallItem> getWallItemsInfo(ItemWithSendersResponse<WallItem> response) {
        List<WallItem> wallItems = response.getItems();

        for (WallItem wallItem: wallItems) {
            Owner sender = response.getSender(wallItem.getFromId());

            wallItem.setSenderName(sender.getFullName());
            wallItem.setSenderPhoto(sender.getPhoto());
            wallItem.setAttachmentsString(
                    Utils.convertAttachmentsToFontIcons(wallItem.getAttachments()));

            if (wallItem.hasSharedRepost()) {
                Owner repostSender = response.getSender(
                        wallItem.getSharedRepost().getFromId());
                WallItem sharedRepost = wallItem.getSharedRepost();

                sharedRepost.setSenderName(repostSender.getFullName());
                sharedRepost.setSenderPhoto(repostSender.getPhoto());
                sharedRepost.setAttachmentsString(
                        Utils.convertAttachmentsToFontIcons(sharedRepost.getAttachments()));
            }
        }
        return wallItems;
    }

    /**
     * Function for getting list of view models for every attachment type
     * @param attachments List of attachments
     * @return List of view models for every attachment
     */
    public static List<BaseAttachment> getAttachmentVkItems(List<ApiAttachment> attachments) {

        List<BaseAttachment> result = new ArrayList<>();

        for (ApiAttachment attachment : attachments) {
            switch (attachment.getType()) {
                case VKAttachments.TYPE_PHOTO:
                    result.add(new ImageAttachment(attachment.getPhoto()));
                    break;
                case VKAttachments.TYPE_AUDIO:
                    result.add(new AudioAttachment(attachment.getAudio()));
                    break;
                case VKAttachments.TYPE_VIDEO:
                    result.add(new VideoAttachment(attachment.getVideo()));
                    break;
                case VKAttachments.TYPE_DOC:
                    VkDocument document = attachment.getDocument();
                    result.add(document.getPreview() != null
                            ? new DocImageAttachment(document)
                            : new DocAttachment(document));
                    break;
                case VKAttachments.TYPE_LINK:
                    Link link = attachment.getLink();
                    result.add(link.isExternal() == 1
                            ? new LinkExternal(link)
                            : new LinkAttachment(link));
                    break;
                case VKAttachments.TYPE_WIKI_PAGE:
                    result.add(new PageAttachment(attachment.getPage()));
                    break;
                default:
                    throw new NoSuchElementException("Attachment type " +
                            attachment.getType() + " is not supported.");
            }
        }
        return result;
    }

    /**
     * Get comments list from response
     * @param response Response for getting comments list
     * @param isFromTopic Flag for every comment item in the comments list
     * @return List of comments
     */
    public static List<CommentItem> getComments(
            ItemWithSendersResponse<CommentItem> response, boolean isFromTopic) {

        List<CommentItem> result = response.getItems();
        for (CommentItem item: result) {
            Owner sender = response.getSender(item.getSenderId());
            item.setSenderName(sender.getFullName());
            item.setSenderPhoto(sender.getPhoto());
            item.setFromTopic(isFromTopic);
            item.setAttachmentsString(
                    Utils.convertAttachmentsToFontIcons(item.getAttachments()));
        }
        return result;
    }
}
