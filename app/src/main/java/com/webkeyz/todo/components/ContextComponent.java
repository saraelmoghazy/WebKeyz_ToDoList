package com.webkeyz.todo.components;

import android.content.Context;

import com.webkeyz.todo.modules.ContextModule;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {ContextModule.class})
@Singleton
public interface ContextComponent {

    Context getContext();
}
