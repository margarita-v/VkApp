package com.margarita.vk_app.models.view.attachment;

import android.view.View;

import com.margarita.vk_app.common.utils.Utils;
import com.margarita.vk_app.models.LayoutTypes;
import com.margarita.vk_app.models.attachment.video.Video;
import com.margarita.vk_app.models.view.base.BaseViewModel;
import com.margarita.vk_app.ui.holder.BaseViewHolder;
import com.margarita.vk_app.ui.holder.attachment.VideoAttachmentHolder;

public class VideoAttachment extends BaseViewModel {

    private int id;
    private int ownerId;

    private String title;
    private String viewCount;
    private String duration;
    private String imageUrl;

    private static final String VIDEO_TITLE = "Video";

    public VideoAttachment(Video video) {
        this.id = video.getId();
        this.ownerId = video.getOwnerId();

        String videoTitle = video.getTitle();
        this.title = videoTitle.isEmpty() ? VIDEO_TITLE : videoTitle;

        this.viewCount = Utils.formatViewsCount(video.getViews());
        this.duration = Utils.parseDuration(video.getDuration());
        this.imageUrl = video.getPhoto320();
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.AttachmentVideo;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new VideoAttachmentHolder(view);
    }

    public int getOwnerId() {
        return ownerId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getViewCount() {
        return viewCount;
    }

    public String getDuration() {
        return duration;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
