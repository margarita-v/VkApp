package com.margarita.vk_app.models;

import com.vk.sdk.api.model.Identifiable;

public interface Owner extends Identifiable {

    String getFullName();

    String getPhoto();
}
