package com.webkeyz.todo.baseCase;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public abstract class BaseViewModel<T> extends ViewModel {

    private MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private MutableLiveData<String> error = new MutableLiveData<>();


    public MutableLiveData<Boolean> getLoading() {
        return loading;
    }

    public void setLoading(MutableLiveData<Boolean> loading) {
        this.loading = loading;
    }

    public MutableLiveData<String> getError() {
        return error;
    }

    public void setError(MutableLiveData<String> error) {
        this.error = error;
    }
}