package com.margarita.vk_app;

import android.support.annotation.Nullable;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKSdk;

public class CurrentUser {

    /**
     * Get access token to Vk server
     * @return Token for access to Vk server
     */
    @Nullable
    public static String getAccessToken() {
        return VKAccessToken.currentToken() == null
                ? null
                : VKAccessToken.currentToken().accessToken;
    }

    /**
     * Get user's id
     * @return User' id, if token is available
     */
    @Nullable
    public static String getId() {
        return VKAccessToken.currentToken() == null
                ? null
                : VKAccessToken.currentToken().userId;
    }

    /**
     * Check if the user is authorized
     * @return True if the user if logged in and his token is not obsolete
     */
    public static boolean isAuthorized() {
        return VKSdk.isLoggedIn()
                && VKAccessToken.currentToken() != null
                // Check if the token is not obsolete
                && !VKAccessToken.currentToken().isExpired();
    }
}
