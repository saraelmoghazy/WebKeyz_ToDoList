package com.webkeyz.todo.ui;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.TimePicker;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;
import com.webkeyz.todo.R;
import com.webkeyz.todo.baseCase.BaseFragment;
import com.webkeyz.todo.baseCase.BaseViewModel;
import com.webkeyz.todo.model.Task;
import com.webkeyz.todo.viewModel.EditTaskViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.webkeyz.todo.ui.MainFragment.BUNDLE_CONTENT;
import static com.webkeyz.todo.ui.MainFragment.BUNDLE_ID;
import static com.webkeyz.todo.ui.MainFragment.BUNDLE_NAME;
import static com.webkeyz.todo.ui.MainFragment.BUNDLE_STATUS;
import static com.webkeyz.todo.ui.MainFragment.BUNDLE_TIMESTAMP;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditTaskFragment extends BaseFragment {

    private static final String TAG = EditTaskFragment.class.getSimpleName();
    @BindView(R.id.tlName)
    TextInputLayout tlName;
    @BindView(R.id.tlContent)
    TextInputLayout tlContent;
    @BindView(R.id.linearLayout2)
    LinearLayout contentDate;
    @BindView(R.id.tlDate)
    TextInputLayout tlDate;
    @BindView(R.id.linearLayout3)
    LinearLayout contentTime;
    @BindView(R.id.tlTime)
    TextInputLayout tlTime;
    @BindView(R.id.animation)
    LottieAnimationView animation;
    @BindView(R.id.loadingAnimation)
    LottieAnimationView loadingAnimation;
    private String id, name, content, timeStamp, status, date, time;
    private EditTaskViewModel viewModel;

    public EditTaskFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public BaseViewModel getViewModel() {
        viewModel = ViewModelProviders.of(getActivity()).get(EditTaskViewModel.class);
        return viewModel;
    }

    @Override
    public LottieAnimationView getAnimation() {
        return loadingAnimation;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_edit_task, container, false);
        ButterKnife.bind(this, view);

        initToolbar();
        id = getArguments().getString(BUNDLE_ID);
        name = getArguments().getString(BUNDLE_NAME);
        content = getArguments().getString(BUNDLE_CONTENT);
        timeStamp = getArguments().getString(BUNDLE_TIMESTAMP);
        status = getArguments().getString(BUNDLE_STATUS);

        init();
        //TODO:: EDIT THE USER CAN CHOOSE RADIO BUTTON

        return view;
    }

    private void initToolbar() {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.edit_task));
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void init() {
        tlName.getEditText().setText(name);
        tlContent.getEditText().setText(content);
        long millisecond = Long.parseLong(timeStamp);
        String dateString = DateFormat.format("MMM, dd yyyy", new Date(millisecond)).toString();
        tlDate.getEditText().setText(dateString);
        String timeString = DateFormat.format("HH:mm", new Date(millisecond)).toString();
        tlTime.getEditText().setText(timeString);
    }

    @OnClick({R.id.linearLayout2, R.id.linearLayout3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.linearLayout2:
                showCalender();
                break;
            case R.id.linearLayout3:
                showClock();
                break;
        }
    }

    private void saveTask() {
        name = tlName.getEditText().getText().toString().trim();
        content = tlContent.getEditText().getText().toString().trim();

        Long mTimeStamp = 0L;
        String mDateTime = date + " " + time;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setTimeZone(TimeZone.getTimeZone("EET"));
        try {
            Log.d(TAG, "onSaveClicked: " + mDateTime);
            Date date = dateFormat.parse(mDateTime);
            mTimeStamp = date.getTime();
            Log.d(TAG, "onSaveClicked: " + mTimeStamp);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Task task = new Task();
        task.setContent(content);
        task.setDate(String.valueOf(mTimeStamp));
        task.setName(name);
        task.setStatus("initial");
        viewModel.editTask(id, task);
        viewModel.getResponse().observe(this, addTaskResponse -> {
            Log.d(TAG, "onChanged: " + addTaskResponse.getMessage());
            animation.setVisibility(View.VISIBLE);
            animation.playAnimation();
            animation.addAnimatorListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    hideAnimation();

                    //TODO:: UPDATE THE TASK IN MAIN FRAGMENT
                }
            });
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.edit_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.save) {
            loadingAnimation.setVisibility(View.VISIBLE);
            saveTask();
        } else if (item.getItemId() == android.R.id.home) {
        }
        return true;
    }

    public void showCalender() {
        final CalendarView calendarView = new CalendarView(getContext());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        calendarView.setLayoutParams(lp);
        calendarView.setOnDateChangeListener((calendarView1, year, month, day) -> {
            month++;
            date = "" + year + "-" + month + "-" + day;
            tlDate.getEditText().setText(date);
        });
        new MaterialAlertDialogBuilder(getContext())
                .setTitle(getString(R.string.new_task))
                .setView(calendarView)
                .setPositiveButton("OK", (dialogInterface, i) -> {

                })
                .setNegativeButton("Cancel", (dialogInterface, i) -> {

                })
                .show();
    }

    private void showClock() {
        Calendar currentTime = Calendar.getInstance();
        int hour = currentTime.get(Calendar.HOUR_OF_DAY);
        int minute = currentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                time = "" + selectedHour + ":" + selectedMinute;
                tlTime.getEditText().setText(time);
            }
        }, hour, minute, false);
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }

    private void hideAnimation() {
        animation.setVisibility(View.GONE);
    }
}
