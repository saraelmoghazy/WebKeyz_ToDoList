package com.webkeyz.todo.components;

import com.webkeyz.todo.modules.EditTaskUCModule;
import com.webkeyz.todo.scope.TasksUCScope;
import com.webkeyz.todo.viewModel.EditTaskViewModel;

import dagger.Component;

@Component(dependencies = {EditTaskRepoComponent.class}, modules = {EditTaskUCModule.class})
@TasksUCScope
public interface EditTaskUCComponent {

    void inject(EditTaskViewModel viewModel);

    class Initializer{
        static EditTaskUCComponent editTaskUCComponent;
        public static EditTaskUCComponent buildComponent(){
            if(editTaskUCComponent == null){
                editTaskUCComponent = DaggerEditTaskUCComponent.builder()
                        .editTaskRepoComponent(DaggerEditTaskRepoComponent.initializer.builder()).build();
            }
            return editTaskUCComponent;
        }
    }
}
