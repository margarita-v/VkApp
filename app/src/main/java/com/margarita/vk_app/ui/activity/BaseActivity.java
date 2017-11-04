package com.margarita.vk_app.ui.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.margarita.vk_app.R;
import com.margarita.vk_app.common.manager.VkFragmentManager;
import com.margarita.vk_app.ui.fragment.BaseFragment;

public abstract class BaseActivity extends MvpAppCompatActivity {

    VkFragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        fragmentManager = new VkFragmentManager();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FrameLayout frameLayout = findViewById(R.id.container);
        getLayoutInflater().inflate(getMainContentLayout(), frameLayout);
    }

    @Override
    public void onBackPressed() {
        removeCurrentFragment();
    }

    @LayoutRes
    protected abstract int getMainContentLayout();

    public void setToolbarTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    public void setFragmentOnScreen(BaseFragment fragment) {
        setToolbarTitle(fragment.createToolbarTitle(this));
    }

    /**
     * Set root fragment
     * @param fragment Fragment which will be set as root in activity
     */
    public void setContent(BaseFragment fragment) {
        fragmentManager.setFragment(this, fragment, R.id.container);
    }

    /**
     * Set additional fragment
     * @param fragment Fragment which will be added
     */
    public void addContent(BaseFragment fragment) {
        fragmentManager.addFragment(this, fragment, R.id.container);
    }

    /**
     * Remove current fragment
     * @return True if the current fragment was removed
     */
    public boolean removeCurrentFragment() {
        return  fragmentManager.removeCurrentFragment(this);
    }

    /**
     * Remove chosen fragment
     * @param fragment Fragment which will be removed
     * @return True if the fragment was removed
     */
    public boolean removeFragment(BaseFragment fragment) {
        return fragmentManager.removeFragment(this, fragment);
    }
}
