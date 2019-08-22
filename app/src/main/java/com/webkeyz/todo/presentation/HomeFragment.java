package com.webkeyz.todo.presentation;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.webkeyz.todo.R;
import com.webkeyz.todo.entities.addtask.TaskBody;


public class HomeFragment extends BaseFragment {


    private TasksViewModel viewModel;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public BaseViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this).get(TasksViewModel.class);
        observeGetTasks();
        observeAddTasks();
        TaskBody body=new TaskBody("soha","15/12","initial");
        viewModel.addTask(body);
        return viewModel;
    }


    private void observeGetTasks() {
        viewModel.tasks.observe(this, tasks -> {
                    Toast.makeText(getActivity(), tasks.get(0).getName(), Toast.LENGTH_SHORT).show();
                }
        );
    }

    private void observeAddTasks() {
        viewModel.status.observe(this, status -> {
                    Toast.makeText(getActivity(), status, Toast.LENGTH_SHORT).show();
                }
        );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }


}
