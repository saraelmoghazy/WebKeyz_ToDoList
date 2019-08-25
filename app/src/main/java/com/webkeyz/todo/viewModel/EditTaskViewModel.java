package com.webkeyz.todo.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.webkeyz.todo.components.DaggerEditTaskUCComponent;
import com.webkeyz.todo.model.AddTaskResponse;
import com.webkeyz.todo.model.ErrorResponse;
import com.webkeyz.todo.model.Task;
import com.webkeyz.todo.usecase.EditTaskUseCase;
import com.webkeyz.todo.utils.RetrofitException;

import java.io.IOException;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

public class EditTaskViewModel extends ViewModel {

    @Inject
    EditTaskUseCase editTaskUseCase;
    private MutableLiveData<AddTaskResponse> response = new MutableLiveData<>();
    private MutableLiveData<String> errorResponse = new MutableLiveData<>();

    public EditTaskViewModel(){
        DaggerEditTaskUCComponent.Initializer.buildComponent().inject(this);
    }

    public void editTask(String id, Task task){
        DisposableObserver<AddTaskResponse> observer = new DisposableObserver<AddTaskResponse>() {
            @Override
            public void onNext(AddTaskResponse addTaskResponse) {
                response.postValue(addTaskResponse);
            }

            @Override
            public void onError(Throwable e) {
                RetrofitException exception = (RetrofitException) e;
                try {
                    ErrorResponse error = exception.getErrorBodyAs(ErrorResponse.class);
                    errorResponse.postValue(error.getMessage());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            @Override
            public void onComplete() {

            }
        };
        editTaskUseCase.observableTask(id, task, observer);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        editTaskUseCase.setDisposable();
    }

    public LiveData<AddTaskResponse> getResponse(){
        return response;
    }

    public LiveData<String> getError(){
        return errorResponse;
    }
}
