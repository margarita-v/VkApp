package com.margarita.vk_app.common.utils;

import com.margarita.vk_app.models.Owner;
import com.margarita.vk_app.models.common.WallItem;
import com.margarita.vk_app.rest.model.response.ItemWithSendersResponse;

import java.util.List;

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
}
