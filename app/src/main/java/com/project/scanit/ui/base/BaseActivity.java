package com.project.scanit.ui.base;


import android.content.BroadcastReceiver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.project.scanit.R;
import com.project.scanit.application.ScanitApplication;
import com.project.scanit.ui.model.ToolbarSettings;
import com.project.scanit.ui.rxbus.RxBus;
import com.project.scanit.ui.rxbus.RxBusCallback;
import com.project.scanit.ui.rxbus.RxBusHelper;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity implements RxBusCallback, View.OnClickListener {

    private static final String TAG = BaseActivity.class.getSimpleName();

    @Inject
    RxBus mRxBus;

    private Unbinder mUnBinder;
    private RxBusHelper mRxBusHelper;

    private BroadcastReceiver mBroadcastReceiver;


    abstract protected int getLayoutId();

    public abstract int getHomeContainerId();

    abstract protected void initViews();

    public abstract void showProgressBar();

    public abstract void hideProgressBar();

    protected abstract Toolbar getToolBar();

    protected abstract void readFromBundle(Bundle bundle);

    public void setToolBar(ToolbarSettings toolBarSetting) {
        Toolbar toolBar = getToolBar();
        if (toolBar != null && toolBarSetting != null) {
            if (toolBarSetting.isShowToolbar()) {
                toolBar.setVisibility(View.VISIBLE);
                setToolBarProperties(toolBarSetting, toolBar);

                setSupportActionBar(toolBar);
            } else {
                toolBar.setVisibility(View.GONE);
            }
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        setContentView(getLayoutId());

        readFromBundle(getIntent().getExtras());

        mUnBinder = ButterKnife.bind(this);

        initDagger();

        initViews();

        registerEvents();

        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                invalidateToolbar();
            }
        });
    }

    private void invalidateToolbar() {
        setToolBar(((BaseFragment)getTopFragment()).getToolBarSetting());
    }

    private void initDagger() {
        ((ScanitApplication) getApplication()).getApplicationComponent().inject(this);
    }

    private void registerEvents() {
        mRxBusHelper = new RxBusHelper();
        mRxBusHelper.registerEvents(mRxBus, TAG, this);
    }

    @Override
    public void onEventTrigger(Object event) {

    }

    /**
     * Set ActionBar for the base layout
     */
    protected void setupActionBar() {
        if (getToolBar() != null) {
            setSupportActionBar(getToolBar());
            if (getSupportActionBar() != null) {
                getSupportActionBar().setHomeButtonEnabled(true);
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void setFragmentToolBar(Toolbar toolbar, ToolbarSettings toolbarSettings) {
        this.setSupportActionBar(toolbar);
        if (toolbarSettings != null) {
            if (!toolbarSettings.isShowToolbar()) {
                toolbar.setVisibility(View.GONE);
            }
            toolbar.setTitle(toolbarSettings.getTitle());
        } else {
            toolbar.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onDestroy() {

        if (mUnBinder != null) {
            mUnBinder.unbind();
            mUnBinder = null;
        }
        if (mRxBusHelper != null) {
            mRxBusHelper.unSubScribe();
        }
        if (mBroadcastReceiver != null) {
            unregisterReceiver(mBroadcastReceiver);
            mBroadcastReceiver = null;
        }
        super.onDestroy();
    }

    protected RxBus getmRxBus() {
        return mRxBus;
    }

    private Fragment getTopFragment() {
        return getSupportFragmentManager().findFragmentById(getHomeContainerId());
    }

    public void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    protected final void replaceFragment(FragmentManager fm, @IdRes int containerId,
                                         Fragment fragment, String fragmentTag,
                                         Bundle args, boolean addToBackstack,
                                         String backstackTag) {
        if (this.isFinishing()) return;

        if (args != null) {
            fragment.setArguments(args);
        }
        FragmentTransaction ft = fm.beginTransaction().replace(containerId, fragment, fragmentTag);
        if (addToBackstack) {
            ft.addToBackStack(backstackTag).commitAllowingStateLoss();
            fm.executePendingTransactions();
        } else {
            ft.commitAllowingStateLoss();
        }
    }

    protected final void addFragment(FragmentManager fm, @IdRes int containerId,
                                     Fragment fragment, String fragmentTag,
                                     Bundle args, boolean addToBackstack, String backstackTag) {
        if (this.isFinishing()) return;
        if (args != null) {
            fragment.setArguments(args);
        }
        FragmentTransaction ft = fm.beginTransaction().add(containerId, fragment, fragmentTag);
        if (addToBackstack) {
            ft.addToBackStack(backstackTag).commitAllowingStateLoss();
            fm.executePendingTransactions();
        } else {
            ft.commitAllowingStateLoss();
        }
    }

    public void setToolBarProperties(ToolbarSettings toolBarProperties, Toolbar toolBar) {
        ((TextView)toolBar.findViewById(R.id.title)).setText(toolBarProperties.getTitle());
        ImageButton backButton = ((ImageButton)toolBar.findViewById(R.id.ib_back));
        ImageButton closeButton = ((ImageButton)toolBar.findViewById(R.id.ib_close));
        backButton.setVisibility(toolBarProperties.isShowbackButton() ? View.VISIBLE : View.GONE);
        closeButton.setVisibility(toolBarProperties.isShowbackButton() ? View.VISIBLE : View.GONE);
        backButton.setOnClickListener(this);
        closeButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_back:
            case R.id.ib_close:
                onBackPressed();
                break;
        }
    }
}
