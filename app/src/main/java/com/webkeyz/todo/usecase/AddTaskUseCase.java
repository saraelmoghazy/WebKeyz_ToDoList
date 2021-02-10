package com.webkeyz.todo.usecase;

import com.webkeyz.todo.baseCase.BaseResponse;
import com.webkeyz.todo.model.Task;
import com.webkeyz.todo.repo.TasksRepo;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class AddTaskUseCase {

    TasksRepo repo;
    private CompositeDisposable disposable;

    public AddTaskUseCase(TasksRepo repo) {
        this.repo = repo;
        disposable = new CompositeDisposable();
    }

    public void observableTask(Task task, DisposableObserver<BaseResponse> observer) {
        disposable.add(
                repo.addTask(task)
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
