package com.project.scanit.ui.listing;

import com.project.scanit.injection.component.ApplicationComponent;
import com.project.scanit.injection.scope.UserScope;
import com.project.scanit.ui.rxbus.RxBus;

import dagger.Component;

@UserScope
@Component(modules = ListingModule.class, dependencies = ApplicationComponent.class)
public interface ListingComponent {
    void inject(HomeListingFragment fragment);
}
