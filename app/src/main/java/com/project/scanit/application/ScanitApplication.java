package com.project.scanit.application;


import android.app.Application;

import com.project.scanit.injection.component.ApplicationComponent;
import com.project.scanit.injection.component.DaggerApplicationComponent;
import com.project.scanit.injection.module.ApplicationModule;

public class ScanitApplication extends Application {

    private static ScanitApplication sInstance;
    private ApplicationComponent mApplicationComponent;

    public ScanitApplication() {
        sInstance = this;
    }

    public static ScanitApplication getInstance() {
        return sInstance;
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initDagger();
    }

    private void initDagger() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }
}
