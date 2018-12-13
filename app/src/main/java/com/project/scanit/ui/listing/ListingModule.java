package com.project.scanit.ui.listing;


import com.project.scanit.injection.scope.UserScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ListingModule {
    private final ListingPresenterContract.View mView;

    ListingModule(ListingPresenterContract.View view) {
        mView = view;
    }

    @Provides
    @UserScope
    ListingPresenterContract.Presenter provideHomeListingPresenter() {
        return new ListingPresenterImpl(mView);
    }
}
