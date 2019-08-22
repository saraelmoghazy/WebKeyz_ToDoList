package com.webkeyz.todo.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.webkeyz.todo.R;
import com.webkeyz.todo.components.ContextComponent;
import com.webkeyz.todo.components.DaggerContextComponent;
import com.webkeyz.todo.modules.ContextModule;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    public static ContextComponent contextComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (contextComponent == null) {
            contextComponent = DaggerContextComponent.builder()
                    .contextModule(new ContextModule(getApplicationContext())).build();
        }
    }
}
