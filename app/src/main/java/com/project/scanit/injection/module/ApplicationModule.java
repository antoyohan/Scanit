package com.project.scanit.injection.module;

import android.app.Application;


import com.project.scanit.injection.scope.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @ApplicationScope
    @Provides
    public Application getApplication() {
        return this.mApplication;
    }

}
