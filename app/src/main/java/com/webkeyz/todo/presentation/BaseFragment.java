package com.webkeyz.todo.presentation;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.webkeyz.todo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.supercharge.shimmerlayout.ShimmerLayout;

public abstract class BaseFragment<V extends BaseViewModel> extends Fragment {

    private V viewModel;
    private ProgressDialog progressDialog;
    private AlertDialog alertDialog;

    @BindView(R.id.shimmer_layout)
    ShimmerLayout shimmerLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = getViewModel();
        progressDialog = new ProgressDialog(getActivity());


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base, container, false);
        ButterKnife.bind(this, view);
        observeLoadingIndicator();
        observeHasError();
        observeNetworkConnection();
        return view;
    }

    public abstract V getViewModel();

    private void observeLoadingIndicator() {
        viewModel.isLoading.observe(this, isLoading -> {
            if (isLoading) {
                shimmerLayout.startShimmerAnimation();
            } else {
                shimmerLayout.stopShimmerAnimation();
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
        alertDialog = new AlertDialog.Builder(getActivity())
                .setMessage(getActivity().getResources().getString(R.string.msg_internet_connection))
                .setPositiveButton(getActivity().getString(R.string.retry), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with delete operation
                    }
                })
                .show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
    }
}
