package com.webkeyz.todo.modules;

import com.webkeyz.todo.repo.TasksRepo;
import com.webkeyz.todo.scope.RepoScope;

import dagger.Module;
import dagger.Provides;

@Module
public class TasksRepoModule {

    @Provides
    @RepoScope
    public TasksRepo provideTaskRepo(){
        return new TasksRepo();
    }
}
