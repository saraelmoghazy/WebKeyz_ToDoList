package com.webkeyz.todo.modules;

import com.webkeyz.todo.repo.TasksRepo;
import com.webkeyz.todo.scope.RepoScope;

import dagger.Module;
import dagger.Provides;

@Module
public class TasksRepoModule {

    @RepoScope
    @Provides
    public TasksRepo provideDeleteTaskRepo(){
        return new TasksRepo();
    }

}
