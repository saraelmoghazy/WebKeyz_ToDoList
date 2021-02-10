package com.webkeyz.todo.usecase;

import com.webkeyz.todo.baseCase.BaseResponse;
import com.webkeyz.todo.repo.TasksRepo;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class DeleteTaskUseCase {

    TasksRepo repo;
    private CompositeDisposable disposable;

    public DeleteTaskUseCase(TasksRepo repo) {
        this.repo = repo;
        disposable = new CompositeDisposable();
    }

    public void observableTask(String id, DisposableObserver<BaseResponse> observer) {
        disposable.add(
                repo.deleteTask(id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(observer)
        );
    }

    public void setDisposable() {
        if (!disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
