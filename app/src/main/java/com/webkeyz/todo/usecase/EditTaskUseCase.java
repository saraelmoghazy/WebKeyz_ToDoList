package com.webkeyz.todo.usecase;

import com.webkeyz.todo.baseCase.BaseResponse;
import com.webkeyz.todo.baseCase.BaseUseCase;
import com.webkeyz.todo.model.Task;
import com.webkeyz.todo.repo.TasksRepo;

import io.reactivex.Observable;

public class EditTaskUseCase extends BaseUseCase<BaseResponse> {

    private TasksRepo repo;
    private Task task;
    private String id;

    public EditTaskUseCase(TasksRepo repo) {
        this.repo = repo;
    }

    @Override
    public Observable<BaseResponse> getObservable() {
        return repo.editTask(id, task);
    }

    public void setID(String id) {
        this.id = id;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
