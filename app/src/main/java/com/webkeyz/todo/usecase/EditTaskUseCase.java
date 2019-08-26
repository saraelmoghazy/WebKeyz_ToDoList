package com.webkeyz.todo.usecase;

import com.webkeyz.todo.baseCase.BaseUseCase;
import com.webkeyz.todo.components.EditTaskRepoComponent;
import com.webkeyz.todo.model.AddTaskResponse;
import com.webkeyz.todo.model.Task;
import com.webkeyz.todo.repo.EditTaskRepo;

import javax.inject.Inject;

import io.reactivex.Observable;

public class EditTaskUseCase extends BaseUseCase<AddTaskResponse> {

    @Inject
    EditTaskRepo repo;
    private Task task;
    private String id;

    public EditTaskUseCase(){
        EditTaskRepoComponent.initializer.builder().inject(this);
    }

    @Override
    public Observable<AddTaskResponse> getObservable() {
        return repo.editTask(id, task);
    }

    public void setID(String id){
        this.id = id;
    }

    public void setTask(Task task){
        this.task = task;
    }
}
