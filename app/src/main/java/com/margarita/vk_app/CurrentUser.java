package com.margarita.vk_app;

import android.support.annotation.Nullable;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKSdk;

public class CurrentUser {

    // Токен доступа от сервера Vk
    @Nullable
    public static String getAccessToken() {
        return VKAccessToken.currentToken() == null
                ? null
                : VKAccessToken.currentToken().accessToken;
    }

    // ID пользователя, если токен получен
    @Nullable
    public static String getId() {
        return VKAccessToken.currentToken() == null
                ? null
                : VKAccessToken.currentToken().userId;
    }

    public static boolean isAuthorized() {
        return VKSdk.isLoggedIn()
                && VKAccessToken.currentToken() != null
                && !VKAccessToken.currentToken().isExpired(); // если токен не устарел
    }
}
