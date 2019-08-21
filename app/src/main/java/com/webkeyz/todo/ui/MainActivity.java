package com.webkeyz.todo.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;

import com.webkeyz.todo.R;
import com.webkeyz.todo.components.ContextComponent;
import com.webkeyz.todo.components.DaggerContextComponent;
import com.webkeyz.todo.model.Task;
import com.webkeyz.todo.modules.ContextModule;
import com.webkeyz.todo.viewModel.TaskViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    public static ContextComponent contextComponent;
    private TaskViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (contextComponent == null){
            contextComponent = DaggerContextComponent.builder()
                    .contextModule(new ContextModule(getApplicationContext())).build();
        }

        viewModel = ViewModelProviders.of(MainActivity.this).get(TaskViewModel.class);
        viewModel.getTasks().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                Log.d(TAG, "onChanged: " + tasks.size());
            }
        });
    }
}
