package com.webkeyz.todo.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.webkeyz.todo.baseCase.BaseObserver;
import com.webkeyz.todo.baseCase.BaseViewModel;
import com.webkeyz.todo.components.EditTaskUCComponent;
import com.webkeyz.todo.model.AddTaskResponse;
import com.webkeyz.todo.model.Task;
import com.webkeyz.todo.usecase.EditTaskUseCase;

import javax.inject.Inject;

public class EditTaskViewModel extends BaseViewModel {

    @Inject
    EditTaskUseCase editTaskUseCase;
    private MutableLiveData<AddTaskResponse> response = new MutableLiveData<>();

    public EditTaskViewModel(){
        EditTaskUCComponent.Initializer.buildComponent().inject(this);
    }

    public void editTask(String id, Task task){
        editTaskUseCase.setID(id);
        editTaskUseCase.setTask(task);
       editTaskUseCase.execute(new BaseObserver<AddTaskResponse>(this){
           @Override
           public void onNext(AddTaskResponse addTaskResponse) {
               super.onNext(addTaskResponse);
               response.postValue(addTaskResponse);
           }
       });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        editTaskUseCase.onClear();
    }

    public LiveData<AddTaskResponse> getResponse(){
        return response;
    }
}
