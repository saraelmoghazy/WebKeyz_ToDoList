package com.webkeyz.todo.presentation.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.webkeyz.todo.R;
import com.webkeyz.todo.presentation.BaseFragment;
import com.webkeyz.todo.presentation.BaseViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeFragment extends BaseFragment {

    private TasksViewModel viewModel;
    @BindView(R.id.fab_open_sheet)
    FloatingActionButton fab;

    @BindView(R.id.recycler_task)
    RecyclerView taskRecycler;
    private TasksAdapter adapter;

    @Override
    public BaseViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this).get(TasksViewModel.class);
        observeGetTasks();
        observeAddTasks();
        return viewModel;
    }

    private void observeGetTasks() {
        viewModel.tasks.observe(this, tasks -> {
                    adapter = new TasksAdapter(tasks, getActivity(), position -> viewModel.removeTask(tasks.get(position).getName()));
                    taskRecycler.setAdapter(adapter);
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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        initRecycler();
        return view;
    }

    @OnClick(R.id.fab_open_sheet)
    public void modalOnClick(View view) {
        BottomDialogFragment addBottomDialogFragment =
                new BottomDialogFragment(body -> viewModel.addTask(body));
        addBottomDialogFragment.show(getFragmentManager(),
                "add_photo_dialog_fragment");
    }

    private void initRecycler() {
        taskRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        taskRecycler.setHasFixedSize(true);
        ItemTouchHelper itemTouchHelper = new
                ItemTouchHelper(new SwipeToDeleteCallback(adapter));
        itemTouchHelper.attachToRecyclerView(taskRecycler);
    }
}
