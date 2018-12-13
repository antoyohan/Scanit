package com.project.scanit.injection.component;

import com.project.scanit.injection.module.ApplicationModule;
import com.project.scanit.injection.module.RxModule;
import com.project.scanit.injection.scope.ApplicationScope;
import com.project.scanit.ui.base.BaseActivity;
import com.project.scanit.ui.base.BaseViewHolder;
import com.project.scanit.ui.rvcomponents.viewtype.QrListViewType;
import com.project.scanit.ui.rxbus.RxBus;

import dagger.Component;

@ApplicationScope
@Component(modules = {ApplicationModule.class, RxModule.class})
public interface ApplicationComponent {

    RxBus rxBus();

    void inject(BaseActivity baseActivity);

    void inject(BaseViewHolder baseViewHolder);

    void inject(QrListViewType qrListViewType);
}
