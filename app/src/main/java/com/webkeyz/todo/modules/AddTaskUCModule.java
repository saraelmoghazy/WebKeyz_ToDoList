package com.webkeyz.todo.modules;

import com.webkeyz.todo.scope.TasksUCScope;
import com.webkeyz.todo.usecase.AddTaskUseCase;

import dagger.Module;
import dagger.Provides;

@Module
public class AddTaskUCModule {

    @TasksUCScope
    @Provides
    public AddTaskUseCase provideModule(){
        return new AddTaskUseCase();
    }
}
