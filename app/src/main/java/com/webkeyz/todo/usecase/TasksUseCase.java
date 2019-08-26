package com.webkeyz.todo.usecase;

import com.webkeyz.todo.baseCase.BaseUseCase;
import com.webkeyz.todo.components.TaskRepoComponent;
import com.webkeyz.todo.model.Task;
import com.webkeyz.todo.repo.TasksRepo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class TasksUseCase extends BaseUseCase<List<Task>> {

    @Inject
    TasksRepo tasksRepo;

    public TasksUseCase() {
        TaskRepoComponent.Initializer.buildComponent().Inject(this);
    }

    @Override
    public Observable<List<Task>> getObservable() {
        return tasksRepo.observableTasks();
    }
}
