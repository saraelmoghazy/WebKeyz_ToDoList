package com.webkeyz.todo.ui;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputLayout;
import com.webkeyz.todo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddTaskFragment extends BottomSheetDialogFragment {

    private static final String TAG = AddTaskFragment.class.getSimpleName();
    @BindView(R.id.tlName)
    TextInputLayout tlName;
    @BindView(R.id.tlContent)
    TextInputLayout tlContent;
    @BindView(R.id.imgContent)
    ImageView imgContent;
    @BindView(R.id.imgDate)
    ImageView imgDate;

    public AddTaskFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_task, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @OnClick(R.id.imgContent)
    public void onContentClicked() {
        tlContent.setVisibility(View.VISIBLE);
        Log.d(TAG, "onContentClicked");
    }

    @OnClick(R.id.imgDate)
    public void onDateClicked() {

    }
}
