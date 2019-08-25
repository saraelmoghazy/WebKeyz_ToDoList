package com.webkeyz.todo.baseCase;

import android.util.Log;

import com.webkeyz.todo.model.Task;
import com.webkeyz.todo.usecase.TasksUseCase;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public abstract class BaseUseCase<T> {

    private static final String TAG = TasksUseCase.class.getSimpleName();
    private CompositeDisposable disposables;

    public BaseUseCase(){
        disposables = new CompositeDisposable();
    }

    public void execute(Observable<T> observable, DisposableObserver<T> observer){

        disposables.add(
                observable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(observer)
        );

    }

    public void setDisposable(){
        if(!disposables.isDisposed()){
            disposables.dispose();
        }
    }
}
