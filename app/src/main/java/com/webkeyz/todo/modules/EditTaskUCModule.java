package com.webkeyz.todo.modules;

import com.webkeyz.todo.scope.TasksUCScope;
import com.webkeyz.todo.usecase.EditTaskUseCase;

import dagger.Module;
import dagger.Provides;

@Module
public class EditTaskUCModule {

    @TasksUCScope
    @Provides
    public EditTaskUseCase provideModule(){
        return new EditTaskUseCase();
    }
}
