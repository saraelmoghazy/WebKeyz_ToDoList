package com.webkeyz.todo.presentation.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.webkeyz.todo.R;
import com.webkeyz.todo.entities.task.Task;
import com.webkeyz.todo.presentation.BaseFragment;
import com.webkeyz.todo.presentation.BaseViewModel;
import com.webkeyz.todo.presentation.edit.EditFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.subjects.PublishSubject;

import static com.webkeyz.todo.presentation.edit.EditFragment.EXTRA_TASK_NAME;

public class HomeFragment extends BaseFragment {

    private TasksViewModel viewModel;
    @BindView(R.id.fab_open_sheet)
    FloatingActionButton fab;

    @BindView(R.id.recycler_task)
    RecyclerView taskRecycler;
    private TasksAdapter adapter;
    private PublishSubject<Task> subject;

    @Override
    public BaseViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this).get(TasksViewModel.class);

        return viewModel;
    }

    private void observeGetTasks() {
        viewModel.tasks.observe(this, tasks -> {
                    adapter = new TasksAdapter(tasks, subject);
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
        subject = PublishSubject.create();
        handleOnClick();
        initRecycler();
        observeGetTasks();
        observeAddTasks();
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

    }

    private void handleOnClick() {
        subject.subscribe(
                i -> openEditFragment(i)
        );
    }

    private void openEditFragment(Task task) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_TASK_NAME, task);
        EditFragment fragment = new EditFragment();
        fragment.setArguments(bundle);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contatiner, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
