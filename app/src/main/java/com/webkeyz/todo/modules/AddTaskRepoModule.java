package com.webkeyz.todo.modules;

import com.webkeyz.todo.repo.AddTaskRepo;
import com.webkeyz.todo.scope.RepoScope;

import dagger.Module;
import dagger.Provides;

@Module
public class AddTaskRepoModule {

    @Provides
    @RepoScope
    public AddTaskRepo provideRepo(){
        return new AddTaskRepo();
    }
}
