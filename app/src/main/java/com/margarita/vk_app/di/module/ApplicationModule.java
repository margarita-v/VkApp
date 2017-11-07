package com.margarita.vk_app.di.module;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private Application application;

    public ApplicationModule(Application app) {
        application = app;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return application;
    }

    @Provides
    @Singleton
    public Typeface provideGoogleTypeface(Context context) {
        return Typeface.createFromAsset(context.getAssets(),
                "MaterialIcons-Regular.ttf");
    }
}
