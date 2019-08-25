package com.webkeyz.todo.repo;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

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

    public TasksRepo()
    {
        RetrofitClientComponent.Initializer.buildComponent().inject(this);
    }


    public Observable<List<Task>> observableTasks(){
        return clientAPI.getTasks()
                .toObservable();
    }
}
