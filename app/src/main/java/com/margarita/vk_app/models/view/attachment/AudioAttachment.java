package com.margarita.vk_app.models.view.attachment;

import android.view.View;

import com.margarita.vk_app.common.utils.Utils;
import com.margarita.vk_app.models.LayoutTypes;
import com.margarita.vk_app.models.attachment.Audio;
import com.margarita.vk_app.ui.holder.BaseViewHolder;
import com.margarita.vk_app.ui.holder.attachment.AudioAttachmentHolder;

public class AudioAttachment extends BaseAttachment {

    private String artist;
    private String duration;

    private static final String AUDIO_TITLE = "Title";
    private static final String AUDIO_ARTIST = "Artist";

    private AudioAttachment(String title) {
        super(title.isEmpty() ? AUDIO_TITLE : title);
    }

    public AudioAttachment(Audio audio) {
        this(audio.getTitle());
        String audioArtist = audio.getArtist();
        this.artist = audioArtist.isEmpty() ? AUDIO_ARTIST : audioArtist;
        this.duration = Utils.parseDuration(audio.getDuration());
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new AudioAttachmentHolder(view);
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.AttachmentAudio;
    }

    public String getArtist() {
        return artist;
    }

    public String getDuration() {
        return duration;
    }
}
