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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;
import com.webkeyz.todo.R;
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
public class EditTaskFragment extends Fragment {

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
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.save)
    MaterialButton save;
    @BindView(R.id.animation)
    LottieAnimationView animation;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_task, container, false);
        ButterKnife.bind(this, view);

        initToolbar();
        id = getArguments().getString(BUNDLE_ID);
        name = getArguments().getString(BUNDLE_NAME);
        content = getArguments().getString(BUNDLE_CONTENT);
        timeStamp = getArguments().getString(BUNDLE_TIMESTAMP);
        status = getArguments().getString(BUNDLE_STATUS);

        init();


        return view;
    }

    private void initToolbar() {
        toolbar.setTitle(getString(R.string.edit_task));
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(view -> getActivity().onBackPressed());
    }

    private void init() {
        tlName.getEditText().setText(name);
        tlContent.getEditText().setText(content);

        long millisecond = Long.parseLong(timeStamp);
        String dateString = DateFormat.format("MMM, dd yyyy", new Date(millisecond)).toString();
        tlDate.getEditText().setText(dateString);
        String timeString  = DateFormat.format("HH:mm", new Date(millisecond)).toString();
        tlTime.getEditText().setText(timeString);

        viewModel = ViewModelProviders.of(getActivity()).get(EditTaskViewModel.class);
    }

    @OnClick({R.id.linearLayout2, R.id.linearLayout3, R.id.save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.linearLayout2:
                showCalender();
                break;
            case R.id.linearLayout3:
                showClock();
                break;
            case R.id.save:
                saveTask();
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
                }
            });
        });

        viewModel.getError().observe(this, s -> Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show());
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.edit_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
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

    private void hideAnimation(){
        animation.setVisibility(View.GONE);
    }
}
