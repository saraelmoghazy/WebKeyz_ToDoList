package com.webkeyz.todo.presentation;


import androidx.lifecycle.MutableLiveData;

import com.webkeyz.todo.entities.addtask.AddTaskResponse;
import com.webkeyz.todo.entities.addtask.TaskBody;
import com.webkeyz.todo.entities.task.Task;
import com.webkeyz.todo.entities.task.TasksResponse;
import com.webkeyz.todo.usecases.usecases.AddTaskUseCase;
import com.webkeyz.todo.usecases.usecases.BaseObserver;
import com.webkeyz.todo.usecases.usecases.EditTaskUseCase;
import com.webkeyz.todo.usecases.usecases.RemoveTaskUseCase;
import com.webkeyz.todo.usecases.usecases.TaskUseCases;

import java.util.List;

public class TasksViewModel extends BaseViewModel {

    MutableLiveData<List<Task>> tasks;
    MutableLiveData<String> status;
    private TaskUseCases useCases;
    private AddTaskUseCase addTaskUseCase;
    private EditTaskUseCase editTaskUseCase;
    private RemoveTaskUseCase removeTaskUseCase;

    public TasksViewModel() {
        tasks = new MutableLiveData<>();
        status = new MutableLiveData<>();
        useCases = new TaskUseCases();
        addTaskUseCase=new AddTaskUseCase();
        editTaskUseCase=new EditTaskUseCase();
        removeTaskUseCase=new RemoveTaskUseCase();
        getTask();
    }

    private void getTask() {
        BaseObserver observer = new BaseObserver<TasksResponse>(this){
            @Override
            public void onNext(TasksResponse response) {
                super.onNext(response);
                tasks.setValue(response.getTasks());
            }
        };
        useCases.execute(observer);
    }

     void addTask(TaskBody body){
       addTaskUseCase.setTaskBody(body);
        BaseObserver observer = new BaseObserver<AddTaskResponse>(this){
            @Override
            public void onNext(AddTaskResponse response) {
                super.onNext(response);
                status.setValue(response.getStatus());
            }
        };
        addTaskUseCase.execute(observer);
    }

    public void editTask(TaskBody body,String name){
       editTaskUseCase.setTaskBody(body,name);
        BaseObserver observer = new BaseObserver<AddTaskResponse>(this){
            @Override
            public void onNext(AddTaskResponse response) {
                super.onNext(response);
                status.setValue(response.getStatus());
            }
        };
        editTaskUseCase.execute(observer);
    }

    public void removeTask(String name){
        removeTaskUseCase.setTaskName(name);
        BaseObserver observer = new BaseObserver<AddTaskResponse>(this){
            @Override
            public void onNext(AddTaskResponse response) {
                super.onNext(response);
                status.setValue(response.getStatus());
            }
        };
        removeTaskUseCase.execute(observer);
    }
}
