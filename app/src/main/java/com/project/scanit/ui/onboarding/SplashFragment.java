package com.project.scanit.ui.onboarding;


import android.os.Handler;
import android.view.View;

import com.project.scanit.R;
import com.project.scanit.ui.HomeActivity;
import com.project.scanit.ui.base.BaseFragment;
import com.project.scanit.ui.model.ToolbarSettings;

public class SplashFragment extends BaseFragment {
    private static final long SPLASH_DELAY = 2000;

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_splash;
    }

    @Override
    protected void initViews(View view) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showHomeScreen();
            }
        }, SPLASH_DELAY);
    }

    private void showHomeScreen() {
        ((HomeActivity) getActivity()).launchHomeScreen(null);
    }

    @Override
    protected void initDagger() {

    }

    @Override
    protected void handleEvents(Object object) {

    }

    @Override
    public ToolbarSettings getToolBarSetting() {
        return new ToolbarSettings("", false);
    }
}
