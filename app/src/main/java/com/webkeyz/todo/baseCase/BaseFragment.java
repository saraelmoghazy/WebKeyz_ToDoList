package com.webkeyz.todo.baseCase;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieAnimationView;

public abstract class BaseFragment extends Fragment {

    private static final String TAG = BaseFragment.class.getSimpleName();

    public abstract BaseViewModel getViewModel();

    public abstract LottieAnimationView getAnimation();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getViewModel().getLoading().observe(this, isLoading->{
            getAnimation().setVisibility(View.GONE);

        });
        getViewModel().getError().observe(this, error->{
            Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
        });
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
