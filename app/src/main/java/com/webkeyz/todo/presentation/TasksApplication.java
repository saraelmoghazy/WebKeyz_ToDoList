package com.webkeyz.todo.presentation;

import android.app.Application;
import android.content.Context;

import com.webkeyz.todo.usecases.network.ApplicationModule;
import com.webkeyz.todo.usecases.network.DaggerApplicationComponent;
import com.webkeyz.todo.usecases.network.DaggerNetworkComponent;
import com.webkeyz.todo.usecases.network.NetworkComponent;
import com.webkeyz.todo.usecases.network.NetworkModule;


public class TasksApplication extends Application {

    private static Context context;
    private static NetworkComponent component;

    public void onCreate() {
        super.onCreate();
        TasksApplication.context = getApplicationContext();

        component = DaggerNetworkComponent.builder()
                .networkModule(new NetworkModule())
                .applicationComponent(DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build()).build();
    }

    public static Context getAppContext() {
        return TasksApplication.context;
    }

    public static NetworkComponent getNetComponent() {
        return component;
    }

}
