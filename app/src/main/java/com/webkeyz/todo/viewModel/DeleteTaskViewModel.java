package com.webkeyz.todo.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.webkeyz.todo.baseCase.BaseResponse;
import com.webkeyz.todo.model.ErrorResponse;
import com.webkeyz.todo.usecase.DeleteTaskUseCase;
import com.webkeyz.todo.utils.RetrofitException;

import java.io.IOException;

import io.reactivex.observers.DisposableObserver;

public class DeleteTaskViewModel extends ViewModel {

   // @Inject
    DeleteTaskUseCase deleteTaskUseCase;
    private MutableLiveData<BaseResponse> response = new MutableLiveData<>();
    private MutableLiveData<String> errorResponse = new MutableLiveData<>();

    public DeleteTaskViewModel(){
    }

    public void deleteTask(String id){
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
        deleteTaskUseCase.observableTask(id, observer);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        deleteTaskUseCase.setDisposable();
    }

    public LiveData<BaseResponse> getResponse(){
        return response;
    }

    public LiveData<String> getError(){
        return errorResponse;
    }

}
