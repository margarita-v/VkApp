package com.margarita.vk_app.models;

import android.support.annotation.LayoutRes;

import com.margarita.vk_app.R;

/**
 * Enum for all layout types in project
 */
public enum LayoutTypes {
    NewsFeedItemHeader(R.layout.item_news_header),
    NewsFeedItemBody(R.layout.item_news_body),
    NewsFeedItemFooter(R.layout.item_news_footer),

    Member(R.layout.member_item),
    Topic(R.layout.topic_item),

    InfoStatus(R.layout.item_info_status),
    InfoContacts(R.layout.item_info_contacts),
    InfoLinks(R.layout.item_info_links),

    AttachmentAudio(R.layout.item_attachment_audio),
    AttachmentDoc(R.layout.item_attachment_doc),
    AttachmentDocImage(R.layout.item_attachment_doc_image),
    AttachmentImage(R.layout.item_attachment_image),
    AttachmentLink(R.layout.item_attachment_link),
    AttachmentLinkExternal(R.layout.item_attachment_link_external),
    AttachmentPage(R.layout.item_attachment_page),
    AttachmentVideo(R.layout.item_attachment_video),

    OpenedPostHeader(R.layout.item_opened_post_header),
    OpenedPostRepostHeader(R.layout.item_opened_post_repost_header),

    CommentHeader(R.layout.item_comment_header),
    CommentBody(R.layout.item_comment_body),
    CommentFooter(R.layout.item_comment_footer);

    private final int id;

    LayoutTypes(int resId) {
        this.id = resId;
    }

    @LayoutRes
    public int getId() {
        return id;
    }
}
