package com.webkeyz.todo.components;

import com.webkeyz.todo.modules.RetrofitClientModule;
import com.webkeyz.todo.repo.TasksRepo;
import com.webkeyz.todo.scope.RetrofitScope;
import com.webkeyz.todo.ui.MainActivity;

import dagger.Component;

@Component(dependencies = {ContextComponent.class}, modules = {RetrofitClientModule.class})
@RetrofitScope
public interface RetrofitClientComponent {

   //ClientAPI getClientApi();

   void inject(TasksRepo repo);

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
