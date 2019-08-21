package com.webkeyz.todo.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.webkeyz.todo.components.TaskRepoComponent;
import com.webkeyz.todo.model.Task;
import com.webkeyz.todo.repo.TasksRepo;

import java.util.List;

import javax.inject.Inject;

public class TaskViewModel extends ViewModel {

    @Inject
    TasksRepo tasksRepo;
    MutableLiveData<List<Task>> taskList = new MutableLiveData<>();

    public TaskViewModel(){
        TaskRepoComponent.Initializer.buildComponent().Inject(this);
        taskList = tasksRepo.getTasks();
    }

    public LiveData<List<Task>> getTasks(){
        return taskList;
    }
}
