package com.margarita.vk_app.rest.model.response.base;

import com.google.gson.annotations.Expose;

public class Full<T> {

    @Expose
    private T response;

    public T getResponse() {
        return response;
    }

}
