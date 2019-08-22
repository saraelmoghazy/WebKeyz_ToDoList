package com.webkeyz.todo.ui;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.webkeyz.todo.R;
import com.webkeyz.todo.model.Task;
import com.webkeyz.todo.ui.adapter.TaskAdapter;
import com.webkeyz.todo.viewModel.TasksViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    @BindView(R.id.rvTasks)
    RecyclerView rvTasks;
    @BindView(R.id.linearNoTasks)
    LinearLayout linearNoTasks;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    private TaskAdapter adapter;
    private List<Task> tasksList = new ArrayList<>();
    private TasksViewModel viewModel;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);

        init();

        return view;
    }

    private void init() {
        initRV();
        initVM();
    }

    private void initVM() {
        viewModel = ViewModelProviders.of(this).get(TasksViewModel.class);
        viewModel.getTaskList().observe(this, tasks -> {
            tasksList.clear();
            tasksList.addAll(tasks);
            adapter.notifyDataSetChanged();
        });
        viewModel.getNoTasks().observe(this, visible -> linearNoTasks.setVisibility(View.VISIBLE));
    }

    private void initRV() {
        rvTasks.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        rvTasks.setHasFixedSize(true);
        adapter = new TaskAdapter(tasksList);
        rvTasks.setAdapter(adapter);
    }

    @OnClick(R.id.fab)
    public void onViewClicked() {
        AddTaskFragment bottomSheetFragment = new AddTaskFragment();
        bottomSheetFragment.show(getFragmentManager(), bottomSheetFragment.getTag());
    }
}
