package com.webkeyz.todo.usecase;

import android.util.Log;

import com.webkeyz.todo.components.AddTaskRepoComponent;
import com.webkeyz.todo.model.AddTaskResponse;
import com.webkeyz.todo.model.Task;
import com.webkeyz.todo.repo.AddTaskRepo;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class AddTaskUseCase {

    @Inject
    AddTaskRepo repo;
    private CompositeDisposable disposable;

    public AddTaskUseCase(){
        AddTaskRepoComponent.initializer.builder().inject(this);
        disposable = new CompositeDisposable();
    }

    public void observableTask(Task task, DisposableObserver<AddTaskResponse> observer){
        disposable.add(
                repo.addTask(task)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer)
        );
    }

    public void setDisposable(){
        if(!disposable.isDisposed()){
            disposable.dispose();
        }
    }
}
