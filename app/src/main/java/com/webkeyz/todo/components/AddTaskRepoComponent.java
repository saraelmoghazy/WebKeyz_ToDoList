package com.webkeyz.todo.components;

import com.webkeyz.todo.modules.AddTaskRepoModule;
import com.webkeyz.todo.scope.RepoScope;
import com.webkeyz.todo.usecase.AddTaskUseCase;

import dagger.Component;

@RepoScope
@Component(dependencies = {RetrofitClientComponent.class}, modules = {AddTaskRepoModule.class})
public interface AddTaskRepoComponent {

    void inject(AddTaskUseCase addTaskUseCase);

    class initializer{
        static AddTaskRepoComponent addTaskRepoComponent;
        public static AddTaskRepoComponent builder(){
            if(addTaskRepoComponent == null){
                addTaskRepoComponent = DaggerAddTaskRepoComponent.builder()
                        .retrofitClientComponent(RetrofitClientComponent.Initializer.buildComponent()).build();
            }
            return addTaskRepoComponent;
        }
    }
}
