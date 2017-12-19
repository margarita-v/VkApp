package com.margarita.vk_app.models.view.attachment.doc;

import com.margarita.vk_app.common.utils.Utils;
import com.margarita.vk_app.models.attachment.doc.VkDocument;
import com.margarita.vk_app.models.view.attachment.BaseAttachment;

/**
 * Base class for doc attachments
 */
public abstract class BaseDocAttachment extends BaseAttachment {

    private String size;
    private String ext;

    private static final String DOC_TITLE = "Document";
    private static final String DOT = ".";

    private BaseDocAttachment(String title, long size, String ext) {
        super(title.isEmpty() ? DOC_TITLE : Utils.removeExtFromText(title));
        this.size = Utils.formatSize(size);
        this.ext = DOT + ext;
    }

    BaseDocAttachment(String fileName, long size) {
        // We should get file extension from the full filename to use a private constructor
        this(fileName, size, fileName.substring(fileName.lastIndexOf(DOT + 1)));
    }

    BaseDocAttachment(VkDocument document) {
        super(document);
        this.size = Utils.formatSize(document.getSize());
        this.ext = DOT + document.getExt();
    }

    @Override
    protected String getDefaultTitle() {
        return DOC_TITLE;
    }

    public String getSize() {
        return size;
    }

    public String getExt() {
        return ext;
    }
}
