package com.margarita.vk_app.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.margarita.vk_app.CurrentUser;
import com.margarita.vk_app.mvp.view.MainView;

// Для привязки ViewState к презентеру
@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    public void checkAuth() {
        if (!CurrentUser.isAuthorized()) {
            getViewState().startSignIn();
        } else {
            getViewState().signedIn();
        }
    }
}
