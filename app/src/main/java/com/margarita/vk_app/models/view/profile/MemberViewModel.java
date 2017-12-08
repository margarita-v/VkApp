package com.margarita.vk_app.models.view.profile;

import android.view.View;

import com.margarita.vk_app.models.common.Member;
import com.margarita.vk_app.ui.holder.BaseViewHolder;
import com.margarita.vk_app.ui.holder.profile.MemberHolder;

public class MemberViewModel extends ProfileViewModel {

    private int groupId;

    public MemberViewModel(Member member) {
        super(member.getId(), member.getPhoto(), member.getFullName());
        this.groupId = member.getGroupId();
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new MemberHolder(view);
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.Member;
    }

    public int getGroupId() {
        return groupId;
    }
}
