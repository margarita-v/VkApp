package com.margarita.vk_app.rest.model.request.fields;

import com.google.gson.annotations.SerializedName;
import com.margarita.vk_app.consts.ApiConstants;
import com.margarita.vk_app.rest.model.request.base.BaseRequest;
import com.vk.sdk.api.VKApiConst;

import java.util.Map;

public class BaseFieldsRequest extends BaseRequest {

    @SerializedName(VKApiConst.FIELDS)
    protected String fields;

    BaseFieldsRequest(String defaultFields) {
        this.fields = defaultFields;
    }

    @Override
    public void onMapCreate(Map<String, String> map) {
        map.put(VKApiConst.FIELDS, fields);
    }

    public String getFields() {
        return fields;
    }
}
