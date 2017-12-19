package com.margarita.vk_app.models.view.attachment;

import android.view.View;

import com.margarita.vk_app.common.utils.Utils;
import com.margarita.vk_app.models.LayoutTypes;
import com.margarita.vk_app.models.attachment.video.Video;
import com.margarita.vk_app.ui.holder.BaseViewHolder;
import com.margarita.vk_app.ui.holder.attachment.VideoAttachmentHolder;

public class VideoAttachment extends BaseAttachment {

    private int id;
    private int ownerId;

    private String viewCount;
    private String duration;

    private static final String VIDEO_TITLE = "Video";

    public VideoAttachment(Video video) {
        super(video);
        this.id = video.getId();
        this.ownerId = video.getOwnerId();

        this.viewCount = Utils.formatViewsCount(video.getViews());
        this.duration = Utils.parseDuration(video.getDuration());
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.AttachmentVideo;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new VideoAttachmentHolder(view);
    }

    @Override
    protected String getDefaultTitle() {
        return VIDEO_TITLE;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public int getId() {
        return id;
    }

    public String getViewCount() {
        return viewCount;
    }

    public String getDuration() {
        return duration;
    }
}
