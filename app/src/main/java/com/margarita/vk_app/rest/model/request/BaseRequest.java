package com.margarita.vk_app.rest.model.request;

import com.google.gson.annotations.SerializedName;
import com.margarita.vk_app.CurrentUser;
import com.margarita.vk_app.consts.ApiConstants;
import com.vk.sdk.api.VKApiConst;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseRequest {

    @SerializedName(VKApiConst.VERSION)
    private Double version = ApiConstants.DEFAULT_VERSION;

    @SerializedName(VKApiConst.ACCESS_TOKEN)
    private String accessToken = CurrentUser.getAccessToken();

    public Double getVersion() {
        return version;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        map.put(VKApiConst.VERSION, String.valueOf(version));
        if (accessToken != null) {
            map.put(VKApiConst.ACCESS_TOKEN, accessToken);
        }

        onMapCreate(map);
        return map;
    }

    public abstract void onMapCreate(Map<String, String> map);

    /**
     * Method for putting to map int values which will be converted to String
     */
    void putToMap(Map<String, String> map, String key, int value) {
        map.put(key, String.valueOf(value));
    }
}
