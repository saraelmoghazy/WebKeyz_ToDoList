package com.webkeyz.todo.presentation.edit;

import androidx.lifecycle.MutableLiveData;

import com.webkeyz.todo.entities.addtask.AddTaskResponse;
import com.webkeyz.todo.entities.addtask.TaskBody;
import com.webkeyz.todo.presentation.BaseViewModel;
import com.webkeyz.todo.usecases.usecases.BaseObserver;
import com.webkeyz.todo.usecases.usecases.EditTaskUseCase;
import com.webkeyz.todo.usecases.usecases.RemoveTaskUseCase;

public class EditViewModel extends BaseViewModel {
    private EditTaskUseCase editTaskUseCase;
    private RemoveTaskUseCase removeTaskUseCase;
    MutableLiveData<String> status;

    public EditViewModel() {
        editTaskUseCase=new EditTaskUseCase();
        removeTaskUseCase=new RemoveTaskUseCase();
        status = new MutableLiveData<>();
    }
    public void editTask(TaskBody body, String name){
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
