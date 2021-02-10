package com.webkeyz.todo.usecases.usecases;


import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public abstract class BaseUseCase<T> {
    BaseObserver<T> observer;
    abstract Observable<T> getObservable();

    public void execute(BaseObserver<T> observer) {
        this.observer=observer;
        getObservable().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
    public void onClear(){
        if(!observer.isDisposed()){
            observer.dispose();
        }
    }

}
