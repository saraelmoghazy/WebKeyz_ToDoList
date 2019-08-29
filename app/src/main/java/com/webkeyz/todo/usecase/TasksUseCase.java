package com.webkeyz.todo.usecase;

import com.webkeyz.todo.baseCase.BaseUseCase;
import com.webkeyz.todo.components.DaggerTasksRepoComponent;
import com.webkeyz.todo.components.TasksRepoComponent;
import com.webkeyz.todo.model.Task;
import com.webkeyz.todo.repo.TasksRepo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class TasksUseCase extends BaseUseCase<List<Task>> {

    private TasksRepo repo;

    public TasksUseCase(TasksRepo repo) {
        this.repo = repo;
    }

    @Override
    public Observable<List<Task>> getObservable() {
        return repo.observableTasks();
    }
}
