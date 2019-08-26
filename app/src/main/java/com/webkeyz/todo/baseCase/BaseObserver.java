package com.webkeyz.todo.baseCase;

import com.webkeyz.todo.model.ErrorResponse;
import com.webkeyz.todo.utils.RetrofitException;

import java.io.IOException;

import io.reactivex.observers.DisposableObserver;

public class BaseObserver<T> extends DisposableObserver<T> {

    BaseViewModel viewModel;

    public BaseObserver(BaseViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void onNext(T t) {
        viewModel.getLoading().setValue(false);
    }

    @Override
    public void onError(Throwable e) {
        {
            viewModel.getLoading().setValue(false);
            RetrofitException exception = (RetrofitException) e;
            try {
                ErrorResponse error = exception.getErrorBodyAs(ErrorResponse.class);
                viewModel.getError().setValue(error.getMessage());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void onComplete() {

    }
}
