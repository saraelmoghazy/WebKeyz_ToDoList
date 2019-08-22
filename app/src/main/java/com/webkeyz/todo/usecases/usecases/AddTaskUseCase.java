package com.webkeyz.todo.usecases.usecases;

import com.webkeyz.todo.entities.addtask.TaskBody;
import com.webkeyz.todo.usecases.repository.TasksRepository;

import io.reactivex.Observable;

public class AddTaskUseCase extends BaseUseCase{
    TasksRepository tasksRepository;
    TaskBody body;

    public AddTaskUseCase() {
        tasksRepository=new TasksRepository();
    }

    @Override
    Observable getObservable() {
        return tasksRepository.addTask(body);
    }

    public void setTaskBody(TaskBody taskBody)
    {
        this.body=taskBody;
    }
}
