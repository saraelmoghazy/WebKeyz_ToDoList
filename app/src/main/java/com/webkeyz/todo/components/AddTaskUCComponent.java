package com.webkeyz.todo.components;

import com.webkeyz.todo.modules.AddTaskUCModule;
import com.webkeyz.todo.scope.TasksUCScope;
import com.webkeyz.todo.viewModel.AddTaskViewModel;

import dagger.Component;

@Component(dependencies = {AddTaskRepoComponent.class}, modules = {AddTaskUCModule.class})
@TasksUCScope
public interface AddTaskUCComponent {

    void inject(AddTaskViewModel viewModel);

    class Initializer{
        static AddTaskUCComponent addTaskUCComponent;
        public static AddTaskUCComponent buildComponent(){
            if(addTaskUCComponent == null){
                addTaskUCComponent = DaggerAddTaskUCComponent.builder()
                        .addTaskRepoComponent(DaggerAddTaskRepoComponent.initializer.builder()).build();
            }
            return addTaskUCComponent;
        }
    }
}
