package com.webkeyz.todo.presentation;

import android.app.ProgressDialog;
import android.os.Bundle;

import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.webkeyz.todo.R;

public abstract class BaseFragment<V extends BaseViewModel> extends Fragment {

    private V viewModel;
    private ProgressDialog progressDialog;
    private AlertDialog alertDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = getViewModel();
        progressDialog = new ProgressDialog(getActivity());
        observeLoadingIndicator();
        observeHasError();
        observeNetworkConnection();
    }

    public abstract V getViewModel();

    private void observeLoadingIndicator() {
        viewModel.isLoading.observe(this, isLoading -> {
            if (isLoading) {
                progressDialog.show();
            } else {
                progressDialog.dismiss();
            }
        });
    }


    private void observeNetworkConnection() {
        viewModel.hasNetworkError.observe(this, hasError -> {
            if (hasError) {
                setDialogue();
            }
        });
    }

    private void observeHasError() {
        viewModel.hasRequestError.observe(this, hasError -> {
            if (hasError) {
                Toast.makeText(getActivity(), getActivity().getString(R.string.msg_failure), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setDialogue() {
        if(getActivity()!=null) {
            alertDialog = new AlertDialog.Builder(getActivity())
                    .setMessage(getActivity().getResources().getString(R.string.msg_internet_connection))
                    .setPositiveButton(getActivity().getString(R.string.retry), (dialog, which) -> {
                        // Continue with delete operation
                    })
                    .show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }
}
