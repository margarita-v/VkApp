package com.margarita.vk_app.models.view.attachment.doc;

import com.margarita.vk_app.common.utils.Utils;
import com.margarita.vk_app.models.attachment.doc.VkDocument;
import com.margarita.vk_app.models.view.base.BaseViewModel;

/**
 * Base class for doc attachments
 */
public abstract class BaseDocAttachment extends BaseViewModel {

    private String title;
    private String size;
    private String ext;
    private String url;

    private static final String DOC_TITLE = "Document";
    private static final String DOT = ".";

    private BaseDocAttachment(String title, long size, String ext) {
        this.title = title.isEmpty() ? DOC_TITLE : Utils.removeExtFromText(title);
        this.size = Utils.formatSize(size);
        this.ext = DOT + ext;
    }

    BaseDocAttachment(String fileName, long size) {
        // We should get file extension from the full filename to use a private constructor
        this(fileName, size, fileName.substring(fileName.lastIndexOf(DOT + 1)));
    }

    BaseDocAttachment(VkDocument document) {
        this(document.getTitle(), document.getSize(), document.getExt());
        this.url = document.getUrl();
    }

    public String getTitle() {
        return title;
    }

    public String getSize() {
        return size;
    }

    public String getExt() {
        return ext;
    }

    public String getUrl() {
        return url;
    }
}
