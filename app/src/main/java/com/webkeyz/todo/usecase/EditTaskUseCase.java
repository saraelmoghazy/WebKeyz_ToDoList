package com.webkeyz.todo.usecase;

import com.webkeyz.todo.components.EditTaskRepoComponent;
import com.webkeyz.todo.model.AddTaskResponse;
import com.webkeyz.todo.model.Task;
import com.webkeyz.todo.repo.EditTaskRepo;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class EditTaskUseCase {

    @Inject
    EditTaskRepo repo;
    private CompositeDisposable disposable;

    public EditTaskUseCase(){
        EditTaskRepoComponent.initializer.builder().inject(this);
        disposable = new CompositeDisposable();
    }

    public void observableTask(String id, Task task, DisposableObserver<AddTaskResponse> observer){
        disposable.add(
                repo.editTask(id, task)
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
