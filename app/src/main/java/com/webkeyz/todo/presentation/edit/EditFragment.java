package com.webkeyz.todo.presentation.edit;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.button.MaterialButton;
import com.webkeyz.todo.R;
import com.webkeyz.todo.entities.addtask.TaskBody;
import com.webkeyz.todo.presentation.BaseFragment;
import com.webkeyz.todo.presentation.BaseViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditFragment extends BaseFragment {
    @BindView(R.id.edit_txt_details)
    EditText detailsEditText;
    @BindView(R.id.edit_date)
    EditText dateEditText;
    @BindView(R.id.delete_image_view)
    ImageView deleteImageView;
    @BindView(R.id.btn_save)
    MaterialButton saveButton;
    public static final String EXTRA_TASK_NAME = "details";
    public static final String EXTRA_TASK_DATE = "date";
    public static final String EXTRA_TASK_STATUS = "status";
    private EditViewModel viewModel;
    private String taskName,date,status;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit, container, false);
        ButterKnife.bind(this, view);
        if (getArguments() != null) {
            Bundle bundle = getArguments();
             taskName= bundle.getString(EXTRA_TASK_NAME);
             date= bundle.getString(EXTRA_TASK_DATE);
             status= bundle.getString(EXTRA_TASK_STATUS);
        }
        return view;
    }

    @Override
    public BaseViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this).get(EditViewModel.class);
        return viewModel;
    }

    @OnClick(R.id.delete_image_view)
    public void deleteTask(){
        viewModel.removeTask(taskName);

    }
    @OnClick(R.id.btn_save)
    public void editTask(){
        TaskBody body=new TaskBody(taskName,date,status);
        viewModel.editTask(body,taskName);

    }

}
