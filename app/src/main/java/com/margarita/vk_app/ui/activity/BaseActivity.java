package com.margarita.vk_app.ui.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.margarita.vk_app.R;
import com.margarita.vk_app.ui.fragment.BaseFragment;

public abstract class BaseActivity extends MvpAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FrameLayout frameLayout = findViewById(R.id.container);
        getLayoutInflater().inflate(getMainContentLayout(), frameLayout);
    }

    @LayoutRes
    protected abstract int getMainContentLayout();

    public void changeFragment(BaseFragment fragment) {

    }
}
