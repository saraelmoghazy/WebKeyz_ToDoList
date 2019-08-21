package com.webkeyz.todo.components;

import com.webkeyz.todo.modules.RetrofitClientModule;
import com.webkeyz.todo.modules.TasksRepoModule;
import com.webkeyz.todo.scope.RepoScope;
import com.webkeyz.todo.viewModel.TaskViewModel;

import dagger.Component;

@Component(dependencies = {RetrofitClientComponent.class}, modules = {TasksRepoModule.class})
@RepoScope
public interface TaskRepoComponent {

    void Inject(TaskViewModel viewModel);

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
