package com.webkeyz.todo.components;

import com.webkeyz.todo.modules.TasksUCModule;
import com.webkeyz.todo.scope.TasksUCScope;
import com.webkeyz.todo.viewModel.TasksViewModel;

import dagger.Component;

@Component(dependencies = {TaskRepoComponent.class}, modules = {TasksUCModule.class})
@TasksUCScope
public interface TasksUCComponent {
    void inject(TasksViewModel viewModel);

    class Initializer{
        static TasksUCComponent tasksUCComponent;
        public static TasksUCComponent buildComponent(){
            if(tasksUCComponent == null){
                tasksUCComponent = DaggerTasksUCComponent.builder()
                        .taskRepoComponent(TaskRepoComponent.Initializer.buildComponent()).build();
            }
            return tasksUCComponent;
        }
    }
}
