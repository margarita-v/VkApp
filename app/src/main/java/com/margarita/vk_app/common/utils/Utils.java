package com.margarita.vk_app.common.utils;

import android.support.annotation.NonNull;

import com.margarita.vk_app.models.attachment.ApiAttachment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Utils {

    /**
     * Patterns for date parsing
     */
    private static final String DEFAULT_PATTERN = "dd.MM.yy в H:mm";
    private static final String SAME_YEAR_PATTERN = "d MMM в H:mm";
    private static final String TODAY_PATTERN = "сегодня в H:mm";

    /**
     * Convert attachments to string of a font icons
     * @param attachments List of attachments which will be converted
     * @return String as a font icons for all attachments
     */
    @NonNull
    static String convertAttachmentsToFontIcons(List<ApiAttachment> attachments) {
        StringBuilder result = new StringBuilder();

        for (ApiAttachment attachment: attachments) {
            result.append(attachment.getAttachment().getIconFont()).append(" ");
        }

        return result.toString();
    }

    /**
     * Parse date to String
     * @param initialDate Date which will be parsed
     * @return Date in a string format
     */
    public static String parseDate(long initialDate) {
        Locale locale = Locale.getDefault();
        Date date = new Date(initialDate * 1000);

        Calendar calendarDate = Calendar.getInstance(), now = Calendar.getInstance();
        calendarDate.setTime(date);

        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_PATTERN, locale);

        if (calendarDate.get(Calendar.YEAR) == now.get(Calendar.YEAR)) {
            boolean isToday =
                    calendarDate.get(Calendar.DAY_OF_YEAR) == now.get(Calendar.DAY_OF_YEAR);
            sdf.applyLocalizedPattern(isToday ? TODAY_PATTERN : SAME_YEAR_PATTERN);
        }
        return sdf.format(date);
    }
}
