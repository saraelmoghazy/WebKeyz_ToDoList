package com.webkeyz.todo.viewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.webkeyz.todo.baseCase.BaseObserver;
import com.webkeyz.todo.baseCase.BaseResponse;
import com.webkeyz.todo.baseCase.BaseViewModel;
import com.webkeyz.todo.components.TasksUseCaseComponent;
import com.webkeyz.todo.model.ErrorResponse;
import com.webkeyz.todo.model.Task;
import com.webkeyz.todo.usecase.DeleteTaskUseCase;
import com.webkeyz.todo.usecase.EditTaskUseCase;
import com.webkeyz.todo.usecase.TasksUseCase;
import com.webkeyz.todo.utils.RetrofitException;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

public class TasksViewModel extends BaseViewModel {

    @Inject
    TasksUseCase tasksUseCase;
    @Inject
    DeleteTaskUseCase deleteUseCase;
    @Inject
    EditTaskUseCase editUseCase;
    private MutableLiveData<List<Task>> taskList = new MutableLiveData<>();
    private MutableLiveData<Boolean> noTasks = new MutableLiveData<>();
    private static final String TAG = TasksViewModel.class.getSimpleName();
    private MutableLiveData<BaseResponse> deleteResponse = new MutableLiveData<>();
    private MutableLiveData<BaseResponse> editResponse = new MutableLiveData<>();
    private MutableLiveData<String> errorResponse = new MutableLiveData<>();

    public TasksViewModel() {
        TasksUseCaseComponent.Initializer.buildComponent().inject(this);
        getTasks();
    }

    public void getTasks() {
        tasksUseCase.execute(new BaseObserver<List<Task>>(this) {
            @Override
            public void onNext(List<Task> tasks) {
                super.onNext(tasks);
                taskList.setValue(tasks);
                if(tasks.size() == 0)
                    noTasks.setValue(true);
            }
        });
    }

    public void deleteTask(String id){
        DisposableObserver<BaseResponse> observer = new DisposableObserver<BaseResponse>() {
            @Override
            public void onNext(BaseResponse addTaskResponse) {
                deleteResponse.postValue(addTaskResponse);
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
        deleteUseCase.observableTask(id, observer);
    }

    public void editTask(String id, Task task){
        editUseCase.setID(id);
        editUseCase.setTask(task);
        editUseCase.execute(new BaseObserver<BaseResponse>(this){
            @Override
            public void onNext(BaseResponse addTaskResponse) {
                super.onNext(addTaskResponse);
                editResponse.postValue(addTaskResponse);
            }
        });
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        tasksUseCase.onClear();
        Log.d(TAG, "onCleared");
    }

    public LiveData<List<Task>> getTaskList() {
        return taskList;
    }

    public LiveData<BaseResponse> getDeleteResponse(){
        return deleteResponse;
    }

    public LiveData<BaseResponse> getEditResponse(){
        return editResponse;
    }

    public LiveData<Boolean> getNoTasks(){
        return noTasks;
    }

    public LiveData<String> getErrorResponse(){
        return errorResponse;
    }
}
