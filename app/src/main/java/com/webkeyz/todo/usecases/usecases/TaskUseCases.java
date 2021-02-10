package com.webkeyz.todo.usecases.usecases;

import com.webkeyz.todo.usecases.repository.TasksRepository;

import io.reactivex.Observable;

public class TaskUseCases extends BaseUseCase {

    TasksRepository tasksRepository;

    public TaskUseCases() {
        tasksRepository=new TasksRepository();
    }

    @Override
    Observable getObservable() {
        return tasksRepository.getTasks();
    }
}
