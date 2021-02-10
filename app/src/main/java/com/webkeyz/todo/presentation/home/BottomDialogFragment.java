package com.webkeyz.todo.presentation.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.textfield.TextInputEditText;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.webkeyz.todo.R;
import com.webkeyz.todo.entities.addtask.TaskBody;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BottomDialogFragment extends BottomSheetDialogFragment implements DatePickerDialog.OnDateSetListener {

    @BindView(R.id.chip_date)
    Chip chip;
    @BindView(R.id.img_calender)
    ImageView imgDatePicker;
    @BindView(R.id.btn_save)
    MaterialButton saveTextView;
    @BindView(R.id.edit_txt_details)
    TextInputEditText taskEditText;

    String date;
    private OnSaveClickListener listener;

    public BottomDialogFragment(OnSaveClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.DialogStyle);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.modal_sheet, container,
                false);
        ButterKnife.bind(this, view);
        return view;

    }

    private void setCalender() {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.show(getFragmentManager(), "Datepickerdialog");
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        date = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
        chip.setVisibility(View.VISIBLE);
        chip.setText(date);

    }


    @OnClick(R.id.btn_save)
    public void saveTask() {
        String taskDetails = taskEditText.getText().toString();
        TaskBody body = new TaskBody(taskDetails, date, "initial");
        listener.onSaveClick(body);

    }

    @OnClick(R.id.img_calender)
    public void openCalender() {
        setCalender();
    }
}
