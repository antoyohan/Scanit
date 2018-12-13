package com.project.scanit.injection.module;

import com.project.scanit.injection.scope.ApplicationScope;
import com.project.scanit.ui.rxbus.RxBus;
import com.project.scanit.ui.rxbus.RxBusImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class RxModule {
    @ApplicationScope
    @Provides
    public RxBus provideRxBus() {
        return new RxBusImpl();
    }
}
