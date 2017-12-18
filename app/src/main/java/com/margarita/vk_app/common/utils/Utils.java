package com.margarita.vk_app.common.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.margarita.vk_app.models.attachment.ApiAttachment;
import com.margarita.vk_app.models.attachment.Attachment;
import com.margarita.vk_app.models.attachment.doc.VkDocument;
import com.vk.sdk.api.model.VKAttachments;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Utils {

    //region String patterns

    /**
     * Patterns for date parsing
     */
    private static final String DEFAULT_PATTERN = "dd.MM.yy в H:mm";
    private static final String SAME_YEAR_PATTERN = "d MMM в H:mm";
    private static final String TODAY_PATTERN = "сегодня в H:mm";

    /**
     * Pattern for duration parsing
     */
    private static final String DURATION_PATTERN = "mm:ss";

    /**
     * Pattern for showing a size value with its unit
     */
    private static final String SIZE_PATTERN = "%.1f %sB";

    //endregion

    /**
     * Constant value for parsing milliseconds to seconds
     */
    private static final int TIME_UNIT = 1000;

    /**
     * Constant value for parsing size from bytes to another units
     */
    private static final int SIZE_UNIT = 1000;

    /**
     * Denominator for parsing size from bytes to another units
     */
    private static final double DENOMINATOR = Math.log(SIZE_UNIT);

    /**
     * String which contains first letters of all size units
     */
    private static final String SIZE_UNITS_NAMES = "kMGTPE";

    private static final String BYTE_UNIT_NAME = " B", DIVIDER = "_";
    private static final char SPACE = ' ', DOT = '.';

    /**
     * Convert attachments to string of a font icons
     * @param apiAttachments List of attachments which will be converted
     * @return String as a font icons for all attachments
     */
    @NonNull
    static String convertAttachmentsToFontIcons(List<ApiAttachment> apiAttachments) {
        StringBuilder result = new StringBuilder();

        for (ApiAttachment apiAttachment: apiAttachments) {
            //TODO Hot fix! Attachment for VkDocument accidentally returns null always.
            Attachment attachment = apiAttachment.getAttachment();
            if (attachment != null)
                result.append(attachment.getIconFont()).append(" ");
            else if (apiAttachment.getType().equals(VKAttachments.TYPE_DOC))
                result.append(VkDocument.ICON_FONT).append(" ");
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
        Date date = new Date(initialDate * TIME_UNIT);

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

    public static String parseDuration(long initialDuration) {
        Locale locale = Locale.getDefault();
        Date date = new Date(initialDuration * TIME_UNIT);
        SimpleDateFormat sdf = new SimpleDateFormat(DURATION_PATTERN, locale);
        return sdf.format(date);
    }

    public static String formatViewsCount(int viewsCount) {
        DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();

        symbols.setGroupingSeparator(SPACE);
        formatter.setDecimalFormatSymbols(symbols);

        return formatter.format(viewsCount);
    }

    /**
     * Format size from bytes to another size units as String
     * @param bytes Size in bytes
     * @return Formatted string for size in its correct unit
     */
    public static String formatSize(long bytes) {
        if (bytes < SIZE_UNIT)
            return bytes + BYTE_UNIT_NAME;

        int exp = (int) (Math.log(bytes) / DENOMINATOR);
        char sizeUnit = SIZE_UNITS_NAMES.charAt(exp - 1);

        return String.format(Locale.getDefault(), SIZE_PATTERN,
                bytes / Math.pow(SIZE_UNIT, exp), sizeUnit);
    }

    /**
     * Remove extension from name of file
     * @param name Name of some file with its extension
     * @return Files name without extension
     */
    public static String removeExtFromText(String name) {
        int length = name.length();
        int removedIndex = length;

        // Find index of last dot symbol
        for (int i = length - 1; i >= 0; i--) {
            if (name.charAt(i) == DOT) {
                removedIndex = i;
                break;
            }
        }
        return name.substring(0, removedIndex);
    }

    /**
     * Function for opening URL in action view
     * @param url URL
     * @param context Context (where this function was called)
     */
    public static void openUrlInActionView(String url, Context context) {
        if (url != null) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri uri = Uri.parse(url);
            intent.setData(uri);
            if (intent.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(intent);
            }
        }
    }

    //region Functions for joining owner id and owner's item id
    public static String concatIds(int ownerId, int itemId) {
        return ownerId + DIVIDER + itemId;
    }

    public static String concatIds(String ownerId, String itemId) {
        return ownerId + DIVIDER + itemId;
    }
    //endregion
}
