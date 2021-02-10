package com.webkeyz.todo.usecases.usecases;

import com.webkeyz.todo.entities.addtask.TaskBody;
import com.webkeyz.todo.usecases.repository.TasksRepository;

import io.reactivex.Observable;

public class EditTaskUseCase extends BaseUseCase {
    TasksRepository tasksRepository;
    TaskBody body;
    String name;

    public EditTaskUseCase() {
        tasksRepository = new TasksRepository();
    }

    @Override
    Observable getObservable() {
        return tasksRepository.editTask(body, name);
    }

    public void setTaskBody(TaskBody taskBody, String name) {
        this.body = taskBody;
        this.name = name;
    }
}
