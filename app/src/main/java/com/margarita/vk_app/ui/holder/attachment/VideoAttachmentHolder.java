package com.margarita.vk_app.ui.holder.attachment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.margarita.vk_app.R;
import com.margarita.vk_app.VkApplication;
import com.margarita.vk_app.common.utils.Utils;
import com.margarita.vk_app.models.attachment.video.Files;
import com.margarita.vk_app.models.view.attachment.VideoAttachment;
import com.margarita.vk_app.rest.api.VideoApi;
import com.margarita.vk_app.rest.model.request.VideoGetRequest;
import com.margarita.vk_app.ui.holder.BaseViewHolder;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class VideoAttachmentHolder extends BaseViewHolder<VideoAttachment> {

    @BindView(R.id.tvAttachmentVideoTitle)
    TextView tvTitle;

    @BindView(R.id.tvVideoDuration)
    TextView tvDuration;

    @BindView(R.id.ivAttachmentVideoPicture)
    ImageView ivVideoPicture;

    @BindView(R.id.tvViewsCount)
    TextView tvViewsCount;

    @Inject
    VideoApi videoApi;

    public VideoAttachmentHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        VkApplication.getApplicationComponent().inject(this);
    }

    @Override
    public void bindViewHolder(VideoAttachment videoAttachment) {
        itemView.setOnClickListener(view ->
                videoApi.get(new VideoGetRequest(
                        videoAttachment.getOwnerId(), videoAttachment.getId())
                        .toMap())

                .flatMap(full ->
                        Observable.fromIterable(full.getResponse().getItems()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(newVideo -> {
                    Files files = newVideo.getFiles();
                    String url = files == null ? newVideo.getPlayer() : files.getExternal();
                    Utils.openUrlInActionView(url, view.getContext());
                })
        );

        tvTitle.setText(videoAttachment.getTitle());
        tvViewsCount.setText(videoAttachment.getViewCount());
        tvDuration.setText(videoAttachment.getDuration());

        loadImage(videoAttachment.getImageUrl(), ivVideoPicture);
    }

    @Override
    public void unbindViewHolder() {
        itemView.setOnClickListener(null);
        clearTextView(tvTitle);
        clearTextView(tvDuration);
        clearImageView(ivVideoPicture);
        clearTextView(tvViewsCount);
    }
}
