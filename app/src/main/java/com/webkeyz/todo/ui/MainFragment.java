package com.webkeyz.todo.ui;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.webkeyz.todo.R;
import com.webkeyz.todo.baseCase.BaseFragment;
import com.webkeyz.todo.baseCase.BaseViewModel;
import com.webkeyz.todo.model.Task;
import com.webkeyz.todo.ui.adapter.RecyclerItemClickListener;
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
public class MainFragment extends BaseFragment implements AddTaskFragment.AddTaskListener {

    private static final String TAG = MainFragment.class.getSimpleName();
    @BindView(R.id.rvTasks)
    RecyclerView rvTasks;
    @BindView(R.id.linearNoTasks)
    LinearLayout linearNoTasks;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.animation)
    LottieAnimationView animation;
    @BindView(R.id.loadingAnimation)
    LottieAnimationView loadingAnimation;
    private TaskAdapter adapter;
    private List<Task> tasksList = new ArrayList<>();
    private TasksViewModel viewModel;
    public static final String BUNDLE_ID = "BUNDLE_ID";
    public static final String BUNDLE_NAME = "BUNDLE_NAME";
    public static final String BUNDLE_CONTENT = "BUNDLE_CONTENT";
    public static final String BUNDLE_TIMESTAMP = "BUNDLE_TIMESTAMP";
    public static final String BUNDLE_STATUS = "BUNDLE_STATUS";

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public BaseViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this).get(TasksViewModel.class);
        return viewModel;
    }

    @Override
    public LottieAnimationView getAnimation() {
        return loadingAnimation;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        init();

        //TODO:: ADD THE TASK THAT ADDED BEFORE FROM ADD TASK FRAGMENT

        return view;
    }

    private void init() {
        initRV();
        initVM();
    }

    private void initVM() {
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
        rvTasks.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), rvTasks, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Bundle bundle = new Bundle();
                        bundle.putString(BUNDLE_ID, tasksList.get(position).getId());
                        bundle.putString(BUNDLE_NAME, tasksList.get(position).getName());
                        bundle.putString(BUNDLE_CONTENT, tasksList.get(position).getContent());
                        bundle.putString(BUNDLE_TIMESTAMP, tasksList.get(position).getDate());
                        bundle.putString(BUNDLE_STATUS, tasksList.get(position).getStatus());
                        Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_editTaskFragment, bundle);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                })
        );
    }

    @OnClick(R.id.fab)
    public void onViewClicked() {
        AddTaskFragment bottomSheetFragment = new AddTaskFragment(this);
        bottomSheetFragment.show(getFragmentManager(), bottomSheetFragment.getTag());
    }

    @Override
    public void onSuccess() {
        animation.setVisibility(View.VISIBLE);
        animation.playAnimation();
        animation.addAnimatorListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                hideAnimation();
            }
        });
    }

    private void hideAnimation() {
        animation.setVisibility(View.GONE);
    }
}
