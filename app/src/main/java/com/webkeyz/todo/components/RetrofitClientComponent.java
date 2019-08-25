package com.webkeyz.todo.components;

import com.webkeyz.todo.modules.RetrofitClientModule;
import com.webkeyz.todo.repo.AddTaskRepo;
import com.webkeyz.todo.repo.EditTaskRepo;
import com.webkeyz.todo.repo.TasksRepo;
import com.webkeyz.todo.scope.RetrofitScope;
import com.webkeyz.todo.ui.MainActivity;

import dagger.Component;

@Component(dependencies = {ContextComponent.class}, modules = {RetrofitClientModule.class})
@RetrofitScope
public interface RetrofitClientComponent {

    void inject(TasksRepo tasksRepo);
    void inject(AddTaskRepo taskRepo);
    void inject(EditTaskRepo taskRepo);

    class Initializer {
        static RetrofitClientComponent retrofitClientComponent;

        public static RetrofitClientComponent buildComponent() {
            if (retrofitClientComponent == null) {
                retrofitClientComponent = DaggerRetrofitClientComponent.builder().contextComponent(MainActivity.contextComponent).build();
            }
            return retrofitClientComponent;
        }
    }
}
