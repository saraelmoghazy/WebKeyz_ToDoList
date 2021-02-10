package com.webkeyz.todo.usecases.network;


import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    Context getContext();
}
