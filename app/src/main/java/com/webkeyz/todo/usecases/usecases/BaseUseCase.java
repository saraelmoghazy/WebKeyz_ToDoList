package com.webkeyz.todo.usecases.usecases;


import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public abstract class BaseUseCase<T> {

    abstract Observable<T> getObservable();

    public void execute(BaseObserver observer) {
        getObservable().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }


}
