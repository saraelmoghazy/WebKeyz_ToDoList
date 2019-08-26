package com.webkeyz.todo.viewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.webkeyz.todo.baseCase.BaseObserver;
import com.webkeyz.todo.baseCase.BaseViewModel;
import com.webkeyz.todo.components.TasksUCComponent;
import com.webkeyz.todo.model.Task;
import com.webkeyz.todo.usecase.TasksUseCase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

public class TasksViewModel extends BaseViewModel {

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
        tasksUseCase.execute(new BaseObserver<List<Task>>(this) {
            @Override
            public void onNext(List<Task> tasks) {
                super.onNext(tasks);
                taskList.setValue(tasks);
                if(tasks.size() == 0)
                    noTasks.setValue(true);
            }
        });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        tasksUseCase.onClear();
        Log.d(TAG, "onCleared");
    }

    public LiveData<List<Task>> getTaskList() {
        return taskList;
    }

    public LiveData<Boolean> getNoTasks(){
        return noTasks;
    }
}
