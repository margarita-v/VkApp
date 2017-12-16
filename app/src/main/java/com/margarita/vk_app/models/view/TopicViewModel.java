package com.margarita.vk_app.models.view;

import android.view.View;

import com.margarita.vk_app.models.LayoutTypes;
import com.margarita.vk_app.models.common.Topic;
import com.margarita.vk_app.models.view.base.BaseIdModel;
import com.margarita.vk_app.ui.holder.BaseViewHolder;
import com.margarita.vk_app.ui.holder.TopicViewHolder;

public class TopicViewModel extends BaseIdModel {

    private int groupId;

    private String title;
    private String commentsCount;
    private static final String COMMENTS_MESSAGE = " сообщений";

    public TopicViewModel(Topic topic) {
        super(topic.getId());
        this.groupId = topic.getGroupId();
        this.title = topic.getTitle();
        this.commentsCount = topic.getComments() + COMMENTS_MESSAGE;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new TopicViewHolder(view);
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.Topic;
    }

    public int getGroupId() {
        return groupId;
    }

    public String getTitle() {
        return title;
    }

    public String getCommentsCount() {
        return commentsCount;
    }
}
