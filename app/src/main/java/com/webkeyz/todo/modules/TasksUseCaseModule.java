package com.webkeyz.todo.modules;

import com.webkeyz.todo.repo.TasksRepo;
import com.webkeyz.todo.scope.TasksUCScope;
import com.webkeyz.todo.usecase.AddTaskUseCase;
import com.webkeyz.todo.usecase.DeleteTaskUseCase;
import com.webkeyz.todo.usecase.EditTaskUseCase;
import com.webkeyz.todo.usecase.TasksUseCase;

import dagger.Module;
import dagger.Provides;

@Module
public class TasksUseCaseModule {

    @TasksUCScope
    @Provides
    public AddTaskUseCase providAddTaskUseCase(TasksRepo addTaskRepo){
        return new AddTaskUseCase(addTaskRepo);
    }

    @TasksUCScope
    @Provides
    public DeleteTaskUseCase provideDeleteUseCase(TasksRepo deleteTaskRepo){
        return new DeleteTaskUseCase(deleteTaskRepo);
    }

    @TasksUCScope
    @Provides
    public TasksUseCase provideGetTasksUseCase(TasksRepo tasksRepo){
        return new TasksUseCase(tasksRepo);
    }


    @TasksUCScope
    @Provides
    public EditTaskUseCase provideEditTasksUseCase(TasksRepo tasksRepo){
        return new EditTaskUseCase(tasksRepo);
    }


}
