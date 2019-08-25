package com.webkeyz.todo.repo;

import com.webkeyz.todo.components.RetrofitClientComponent;
import com.webkeyz.todo.model.AddTaskResponse;
import com.webkeyz.todo.model.Task;
import com.webkeyz.todo.remote.ClientAPI;

import javax.inject.Inject;

import io.reactivex.Observable;

public class AddTaskRepo {

    @Inject
    ClientAPI clientAPI;

    public AddTaskRepo(){
        RetrofitClientComponent.Initializer.buildComponent().inject(this);
    }

    public Observable<AddTaskResponse> addTask(Task task){
        return clientAPI.addTask(task)
                .toObservable();
    }
}
