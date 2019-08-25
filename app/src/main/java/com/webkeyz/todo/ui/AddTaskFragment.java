package com.webkeyz.todo.ui;


import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;
import com.webkeyz.todo.R;
import com.webkeyz.todo.model.AddTaskResponse;
import com.webkeyz.todo.model.Task;
import com.webkeyz.todo.viewModel.AddTaskViewModel;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

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
    @BindView(R.id.save)
    MaterialButton save;
    @BindView(R.id.imgWatch)
    ImageView imgWatch;
    private String date, time, name, details;
    private long timeStamp;
    private AddTaskViewModel viewModel;
    private AddTaskListener listener;

    public AddTaskFragment(AddTaskListener listener) {
        // Required empty public constructor
        this.listener = listener;
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

        init();

        return view;
    }

    private void init() {
        viewModel = ViewModelProviders.of(getActivity()).get(AddTaskViewModel.class);
        tlName.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0)
                    tlName.getEditText().setHint(getResources().getString(R.string.new_task));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        tlContent.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0)
                    tlContent.getEditText().setHint(getResources().getString(R.string.add_details));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @OnClick(R.id.imgContent)
    public void onContentClicked() {
        tlContent.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.imgDate)
    public void onDateClicked() {
        showCalender();
    }

    @OnClick(R.id.save)
    public void onSaveClicked() {

        name = tlName.getEditText().getText().toString().trim();
        details = tlContent.getEditText().getText().toString().trim();
        String mDateTime = date + " " + time;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setTimeZone(TimeZone.getTimeZone("EET"));
        try {
            Date date = dateFormat.parse(mDateTime);
            timeStamp = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Task task = new Task();
        task.setContent(details);
        task.setDate(String.valueOf(timeStamp));
        task.setId(String.valueOf(System.currentTimeMillis()));
        task.setName(name);
        task.setStatus("initial");
        initViewModel(task);

    }

    @OnClick(R.id.imgWatch)
    public void onWatchClicked() {
        showClock();
    }

    private void initViewModel(Task task) {
        viewModel.addTask(task);
        viewModel.getResponse().observe(this, addTaskResponse -> {
            Log.d(TAG, "onChanged: " + addTaskResponse.getMessage());
            this.dismiss();
            listener.onSuccess();

        });
        viewModel.getError().observe(this, s -> Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show());
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
            }
        }, hour, minute, false);
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }

    interface AddTaskListener {
        void onSuccess();
    }
}
