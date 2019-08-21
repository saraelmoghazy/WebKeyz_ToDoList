package com.webkeyz.todo.components;

import com.webkeyz.todo.modules.TasksRepoModule;
import com.webkeyz.todo.scope.RepoScope;
import com.webkeyz.todo.usecase.TasksUseCase;

import dagger.Component;

@Component(dependencies = {RetrofitClientComponent.class}, modules = {TasksRepoModule.class})
@RepoScope
public interface TaskRepoComponent {

    void Inject(TasksUseCase tasksUseCase);

    class Initializer{
        static TaskRepoComponent taskRepoComponent;
        public static TaskRepoComponent buildComponent(){
            if(taskRepoComponent == null){
                taskRepoComponent = DaggerTaskRepoComponent.builder()
                        .retrofitClientComponent(RetrofitClientComponent.Initializer.buildComponent()).build();
            }
            return taskRepoComponent;
        }
    }
}
