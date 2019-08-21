package com.webkeyz.todo.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.webkeyz.todo.components.TasksUCComponent;
import com.webkeyz.todo.model.Task;
import com.webkeyz.todo.usecase.TasksUseCase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

public class TasksViewModel extends ViewModel {

    @Inject
    TasksUseCase tasksUseCase;
    private MutableLiveData<List<Task>> taskList = new MutableLiveData<>();

    public TasksViewModel() {
        TasksUCComponent.Initializer.buildComponent().inject(this);
        getTasks();
    }

    public void getTasks() {
        DisposableObserver<List<Task>> observer = new DisposableObserver<List<Task>>() {
            @Override
            public void onNext(List<Task> tasks) {
                getTaskList().setValue(tasks);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        tasksUseCase.observableTasks(observer);
    }



    @Override
    protected void onCleared() {
        super.onCleared();
        tasksUseCase.setDisposable();
    }

    public MutableLiveData<List<Task>> getTaskList() {
        return taskList;
    }
}
