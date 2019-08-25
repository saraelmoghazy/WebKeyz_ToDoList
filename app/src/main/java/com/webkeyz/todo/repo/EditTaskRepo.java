package com.webkeyz.todo.repo;

import com.webkeyz.todo.components.RetrofitClientComponent;
import com.webkeyz.todo.model.AddTaskResponse;
import com.webkeyz.todo.model.Task;
import com.webkeyz.todo.remote.ClientAPI;

import javax.inject.Inject;

import io.reactivex.Observable;

public class EditTaskRepo {

    @Inject
    ClientAPI clientAPI;

    public EditTaskRepo(){
        RetrofitClientComponent.Initializer.buildComponent().inject(this);
    }

    public Observable<AddTaskResponse> editTask(String id, Task task){
        return clientAPI.editTask(id, task)
                .toObservable();
    }
}
