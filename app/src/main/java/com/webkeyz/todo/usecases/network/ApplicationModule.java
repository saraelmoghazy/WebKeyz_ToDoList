package com.webkeyz.todo.usecases.network;


import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    Context context;

    public ApplicationModule(Application mApplication) {
        this.context = mApplication;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return context;
    }
}
