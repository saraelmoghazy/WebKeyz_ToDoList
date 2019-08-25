package com.webkeyz.todo.baseCase;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import io.reactivex.observers.DisposableObserver;

public abstract class BaseViewModel<T> {

    private MutableLiveData<T> date = new MutableLiveData<>();

    public LiveData<T> execute(DisposableObserver<T> observer){
        observer = new DisposableObserver<T>() {
            @Override
            public void onNext(T t) {
                date.postValue(t);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        return date;
    }
}
