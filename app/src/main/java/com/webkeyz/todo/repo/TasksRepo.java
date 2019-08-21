package com.webkeyz.todo.repo;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.webkeyz.todo.components.RetrofitClientComponent;
import com.webkeyz.todo.model.Task;
import com.webkeyz.todo.remote.ClientAPI;

import java.util.List;

import javax.inject.Inject;

import dagger.Provides;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class TasksRepo {

    @Inject
    ClientAPI clientAPI;
    private ConnectableObservable<List<Task>> observable;
    private static final String TAG = TasksRepo.class.getSimpleName();
    private MutableLiveData<List<Task>> taskList = new MutableLiveData<>();

    public TasksRepo()
    {
        RetrofitClientComponent.Initializer.buildComponent().inject(this);
    }


    public Observable<List<Task>> observableTasks(){
        return clientAPI.getTasks()
                .toObservable();
    }
}
