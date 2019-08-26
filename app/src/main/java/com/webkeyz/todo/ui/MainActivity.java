package com.webkeyz.todo.ui;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.webkeyz.todo.R;
import com.webkeyz.todo.components.ContextComponent;
import com.webkeyz.todo.components.DaggerContextComponent;
import com.webkeyz.todo.modules.ContextModule;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    public static ContextComponent contextComponent;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (contextComponent == null) {
            contextComponent = DaggerContextComponent.builder()
                    .contextModule(new ContextModule(getApplicationContext())).build();
        }

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.app_name));

    }
}
