package com.margarita.vk_app.ui.holder.attachment;

import android.view.View;
import android.widget.TextView;

import com.margarita.vk_app.R;
import com.margarita.vk_app.models.view.attachment.AudioAttachment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AudioAttachmentHolder extends BaseAttachmentHolder<AudioAttachment> {

    @BindView(R.id.tvAudioName)
    TextView tvName;

    @BindView(R.id.tvAudioArtist)
    TextView tvArtist;

    @BindView(R.id.tvAudioDuration)
    TextView tvDuration;

    public AudioAttachmentHolder(View view) {
        super(view);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindViewHolder(AudioAttachment audioAttachment) {
        tvName.setText(audioAttachment.getTitle());
        tvArtist.setText(audioAttachment.getArtist());
        tvDuration.setText(audioAttachment.getDuration());
    }

    @Override
    public void unbindViewHolder() {
        super.unbindViewHolder();
        clearTextView(tvName);
        clearTextView(tvArtist);
        clearTextView(tvDuration);
    }
}
