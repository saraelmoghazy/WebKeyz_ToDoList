package com.webkeyz.todo.components;

import com.webkeyz.todo.modules.EditTaskRepoModule;
import com.webkeyz.todo.scope.RepoScope;
import com.webkeyz.todo.usecase.EditTaskUseCase;

import dagger.Component;

@RepoScope
@Component(dependencies = {RetrofitClientComponent.class}, modules = {EditTaskRepoModule.class})
public interface EditTaskRepoComponent {

    void inject(EditTaskUseCase editTaskUseCase);

    class initializer{
        static EditTaskRepoComponent editTaskRepoComponent;
        public static EditTaskRepoComponent builder(){
            if(editTaskRepoComponent == null){
                editTaskRepoComponent = DaggerEditTaskRepoComponent.builder()
                        .retrofitClientComponent(RetrofitClientComponent.Initializer.buildComponent()).build();
            }
            return editTaskRepoComponent;
        }
    }
}