package com.webkeyz.todo.usecase;

import android.util.Log;

import com.webkeyz.todo.components.TaskRepoComponent;
import com.webkeyz.todo.model.Task;
import com.webkeyz.todo.repo.TasksRepo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class TasksUseCase {

    private static final String TAG = TasksUseCase.class.getSimpleName();
    @Inject
    TasksRepo tasksRepo;
    private CompositeDisposable disposables;

    public TasksUseCase(){
        TaskRepoComponent.Initializer.buildComponent().Inject(this);
        disposables = new CompositeDisposable();
    }

    public void observableTasks(DisposableObserver<List<Task>> observer){
        disposables.add(
                tasksRepo.observableTasks()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer)
        );
    }

    public void setDisposable(){
        if(!disposables.isDisposed()){
            disposables.dispose();
            Log.d(TAG, "setDisposable");
        }
    }
}
