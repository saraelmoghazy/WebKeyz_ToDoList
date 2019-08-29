package com.webkeyz.todo.components;

import com.webkeyz.todo.modules.TasksUseCaseModule;
import com.webkeyz.todo.scope.TasksUCScope;
import com.webkeyz.todo.viewModel.AddTaskViewModel;
import com.webkeyz.todo.viewModel.DeleteTaskViewModel;
import com.webkeyz.todo.viewModel.EditTaskViewModel;
import com.webkeyz.todo.viewModel.TasksViewModel;

import dagger.Component;

@Component(dependencies = {TasksRepoComponent.class}, modules = {TasksUseCaseModule.class})
@TasksUCScope
public interface TasksUseCaseComponent {
    void inject(TasksViewModel viewModel);
    void inject(AddTaskViewModel viewModel);
    void inject(EditTaskViewModel viewModel);
    void inject(DeleteTaskViewModel viewModel);
    class Initializer{
        static TasksUseCaseComponent tasksUseCaseComponent;
        public static TasksUseCaseComponent buildComponent(){
            if(tasksUseCaseComponent == null){
                tasksUseCaseComponent = DaggerTasksUseCaseComponent.builder()
                        .tasksRepoComponent(TasksRepoComponent.Initializer.buildComponent()).build();
            }
            return tasksUseCaseComponent;
        }
    }
}
