package com.webkeyz.todo.repo;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.webkeyz.todo.baseCase.BaseResponse;
import com.webkeyz.todo.components.RetrofitClientComponent;
import com.webkeyz.todo.model.Task;
import com.webkeyz.todo.remote.ClientAPI;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

public class TasksRepo {

    @Inject
    ClientAPI clientAPI;
    private static final String TAG = TasksRepo.class.getSimpleName();

    public TasksRepo() {
      RetrofitClientComponent.Initializer.buildComponent().inject(this);
    }


    public Observable<List<Task>> observableTasks() {
        return clientAPI.getTasks()
                .toObservable();
    }

    public Observable<BaseResponse> addTask(Task task){
        return clientAPI.addTask(task)
                .toObservable();
    }

    public Observable<BaseResponse> deleteTask(String id) {
        Log.d(TAG, "deleteTask: " + id);
        return clientAPI.deleteTask(id)
                .toObservable();
    }

    public Observable<BaseResponse> editTask(String id, Task task){
        return clientAPI.editTask(id, task)
                .toObservable();
    }
}
