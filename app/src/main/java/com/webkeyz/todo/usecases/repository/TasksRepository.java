package com.webkeyz.todo.usecases.repository;

import com.webkeyz.todo.entities.addtask.AddTaskResponse;
import com.webkeyz.todo.entities.addtask.TaskBody;
import com.webkeyz.todo.entities.task.TasksResponse;
import com.webkeyz.todo.presentation.TasksApplication;
import com.webkeyz.todo.usecases.network.TasksApi;

import javax.inject.Inject;

import io.reactivex.Observable;

public class TasksRepository {

    @Inject
    TasksApi tasksApi;

    public TasksRepository() {
        TasksApplication.getNetComponent().inject(this);
    }

    public Observable<TasksResponse> getTasks() {
        return tasksApi.getTasks();
    }

    public Observable<AddTaskResponse> addTask(TaskBody body) {
        return tasksApi.addTask(body);
    }

    public Observable<AddTaskResponse> removeTask(String name) {
        return tasksApi.removeTask(name);
    }

    public Observable<AddTaskResponse> editTask(TaskBody body, String name) {
        return tasksApi.editTask(body, name);
    }


}
