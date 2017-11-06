package com.margarita.vk_app.rest.model.response;

import java.util.ArrayList;
import java.util.List;

public class BaseItemResponse<T> {

    private Integer count;

    private List<T> items = new ArrayList<>();

    public Integer getCount() {
        return count;
    }

    public List<T> getItems() {
        return items;
    }

}
