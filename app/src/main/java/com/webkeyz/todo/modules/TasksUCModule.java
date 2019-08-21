package com.webkeyz.todo.modules;

import com.webkeyz.todo.scope.TasksUCScope;
import com.webkeyz.todo.usecase.TasksUseCase;

import dagger.Module;
import dagger.Provides;

@Module
public class TasksUCModule {

    @Provides
    @TasksUCScope
    public TasksUseCase provideTasksUC(){
        return new TasksUseCase();
    }
}
