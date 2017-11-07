package com.margarita.vk_app.common.utils;

import android.support.annotation.NonNull;

import com.margarita.vk_app.models.attachment.ApiAttachment;

import java.util.List;

public class Utils {

    /**
     * Convert attachments to string of a font icons
     * @param attachments List of attachments which will be converted
     * @return String as a font icons for all attachments
     */
    @NonNull
    public static String convertAttachmentsToFontIcons(List<ApiAttachment> attachments) {
        StringBuilder result = new StringBuilder();

        for (ApiAttachment attachment: attachments) {
            result.append(attachment.getAttachment().getIconFont()).append(" ");
        }

        return result.toString();
    }
}
