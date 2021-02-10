package com.webkeyz.todo.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.webkeyz.todo.baseCase.BaseResponse;
import com.webkeyz.todo.components.TasksUseCaseComponent;
import com.webkeyz.todo.model.ErrorResponse;
import com.webkeyz.todo.model.Task;
import com.webkeyz.todo.usecase.AddTaskUseCase;
import com.webkeyz.todo.utils.RetrofitException;

import java.io.IOException;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

public class AddTaskViewModel extends ViewModel {

   @Inject
    AddTaskUseCase addTaskUseCase;
    private MutableLiveData<BaseResponse> response = new MutableLiveData<>();
    private MutableLiveData<String> errorResponse = new MutableLiveData<>();

    public AddTaskViewModel(){
        TasksUseCaseComponent.Initializer.buildComponent().inject(this);
    }

    public void addTask(Task task){
        DisposableObserver<BaseResponse> observer = new DisposableObserver<BaseResponse>() {
            @Override
            public void onNext(BaseResponse addTaskResponse) {
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
        addTaskUseCase.observableTask(task, observer);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        addTaskUseCase.setDisposable();
    }

    public LiveData<BaseResponse> getResponse(){
        return response;
    }

    public LiveData<String> getError(){
        return errorResponse;
    }
}