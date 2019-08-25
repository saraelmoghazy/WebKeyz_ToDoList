package com.webkeyz.todo.modules;

import com.webkeyz.todo.repo.EditTaskRepo;
import com.webkeyz.todo.scope.RepoScope;

import dagger.Module;
import dagger.Provides;

@Module
public class EditTaskRepoModule {

    @Provides
    @RepoScope
    public EditTaskRepo provideRepo(){
        return new EditTaskRepo();
    }
}
