package com.webkeyz.todo.components;

import com.webkeyz.todo.modules.TasksRepoModule;
import com.webkeyz.todo.repo.TasksRepo;
import com.webkeyz.todo.scope.RepoScope;
import com.webkeyz.todo.usecase.AddTaskUseCase;
import com.webkeyz.todo.usecase.TasksUseCase;

import dagger.Component;

@Component(dependencies = {RetrofitClientComponent.class}, modules = {TasksRepoModule.class})
@RepoScope
public interface TasksRepoComponent {

    TasksRepo getRepo();

        class Initializer{
            static TasksRepoComponent tasksRepoComponent;
            public static TasksRepoComponent buildComponent(){
                if(tasksRepoComponent == null){
                    tasksRepoComponent = DaggerTasksRepoComponent.builder()
                          .retrofitClientComponent(RetrofitClientComponent.Initializer.buildComponent()).build();
                }
                return tasksRepoComponent;
            }
        }
}
