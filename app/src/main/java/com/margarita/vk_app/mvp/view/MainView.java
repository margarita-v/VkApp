package com.margarita.vk_app.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.margarita.vk_app.models.common.Profile;
import com.margarita.vk_app.ui.fragment.base.BaseFragment;

public interface MainView extends MvpView {

    void startSignIn();

    void signedIn();

    void showCurrentUser(Profile profile);

    void showFragmentFromDrawer(BaseFragment fragment);
}
