package com.webkeyz.todo.presentation;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BaseViewModel extends ViewModel {

    public MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    public MutableLiveData<Boolean> hasNetworkError = new MutableLiveData<>();
    public MutableLiveData<Boolean> hasRequestError = new MutableLiveData<>();

    
}
