package com.webkeyz.todo.usecases.network;

import com.webkeyz.todo.usecases.repository.TasksRepository;

import dagger.Component;

@CustomScope
@Component(dependencies = ApplicationComponent.class , modules ={NetworkModule.class})
public interface NetworkComponent {
    void inject(TasksRepository repository);
}


