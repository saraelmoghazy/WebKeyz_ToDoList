package com.webkeyz.todo.viewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.webkeyz.todo.components.TasksUCComponent;
import com.webkeyz.todo.model.ErrorResponse;
import com.webkeyz.todo.model.Task;
import com.webkeyz.todo.usecase.TasksUseCase;
import com.webkeyz.todo.utils.RetrofitException;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

public class TasksViewModel extends ViewModel {

    @Inject
    TasksUseCase tasksUseCase;
    private MutableLiveData<List<Task>> taskList = new MutableLiveData<>();
    private MutableLiveData<Boolean> noTasks = new MutableLiveData<>();
    private static final String TAG = TasksViewModel.class.getSimpleName();

    public TasksViewModel() {
        TasksUCComponent.Initializer.buildComponent().inject(this);
        setTasks();
    }

    public void setTasks() {
        DisposableObserver<List<Task>> observer = new DisposableObserver<List<Task>>() {
            @Override
            public void onNext(List<Task> tasks) {
                taskList.setValue(tasks);
                if(tasks.size() == 0)
                    noTasks.postValue(true);
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
        Log.d(TAG, "onCleared");
    }

    public LiveData<List<Task>> getTaskList() {
        return taskList;
    }

    public LiveData<Boolean> getNoTasks(){
        return noTasks;
    }
}
