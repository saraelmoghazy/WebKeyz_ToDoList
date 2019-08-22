package com.webkeyz.todo.presentation;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment<V extends BaseViewModel> extends Fragment {

    private V viewModel;
    private ProgressDialog progressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = getViewModel();
        progressDialog = new ProgressDialog(getActivity());
        observeLoadingIndicator();
        observeHasError();

    }

    public abstract V getViewModel();

    private void observeLoadingIndicator() {
        viewModel.isLoading.observe(this, isLoading -> {
            if (isLoading) {
                Toast.makeText(getActivity(), "show ", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "hide ", Toast.LENGTH_SHORT).show();

            }
        });
    }


    private void observeHasError() {
        viewModel.hasNetworkError.observe(this, hasError -> {
            if (hasError) {
                Toast.makeText(getActivity(), "has error ", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
