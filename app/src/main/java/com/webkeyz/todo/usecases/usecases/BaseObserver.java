package com.webkeyz.todo.usecases.usecases;


import com.webkeyz.todo.presentation.BaseViewModel;
import com.webkeyz.todo.usecases.network.RetrofitException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

public class BaseObserver<T> extends DisposableObserver<T> {

    BaseViewModel viewModel;

    public BaseObserver(BaseViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void onNext(T o) {
        viewModel.isLoading.setValue(false);
    }

    @Override
    public void onError(Throwable throwable) {
        viewModel.isLoading.setValue(false);
        RetrofitException error = (RetrofitException) throwable;
        String s = error.getKind().name();
        if (s.equalsIgnoreCase("NETWORK"))
            viewModel.hasNetworkError.setValue(true);
        else viewModel.hasRequestError.setValue(true);
    }

    @Override
    public void onComplete() {

    }
}
