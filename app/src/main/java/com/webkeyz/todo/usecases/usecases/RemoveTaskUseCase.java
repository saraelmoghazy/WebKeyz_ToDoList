package com.webkeyz.todo.usecases.usecases;

import com.webkeyz.todo.usecases.repository.TasksRepository;

import io.reactivex.Observable;

public class RemoveTaskUseCase extends BaseUseCase {
    TasksRepository tasksRepository;
    String name;

    public RemoveTaskUseCase() {
        tasksRepository = new TasksRepository();
    }

    @Override
    Observable getObservable() {
        return tasksRepository.removeTask(name);
    }

    public void setTaskName( String name) {
        this.name = name;
    }
}
