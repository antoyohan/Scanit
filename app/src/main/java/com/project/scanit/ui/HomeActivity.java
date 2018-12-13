package com.project.scanit.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;

import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.project.scanit.R;
import com.project.scanit.ui.base.BaseActivity;
import com.project.scanit.ui.detail.DetailFragment;
import com.project.scanit.ui.listing.HomeListingFragment;
import com.project.scanit.ui.onboarding.SplashFragment;
import com.project.scanit.ui.scan.ScanFragment;

import butterknife.BindView;

public class HomeActivity extends BaseActivity implements Navigator{

    DecoratedBarcodeView mDecoratedBarcodeView;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public int getHomeContainerId() {
        return R.id.frame_container;
    }

    @Override
    protected void initViews() {
        showSplashScreen();
    }

    private void showSplashScreen() {
       // replaceFragment(new SplashFragment(), "splash_screen" , null, false, "");
        launchHomeScreen(null);
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    protected Toolbar getToolBar() {
        return mToolbar;
    }

    @Override
    protected void readFromBundle(Bundle bundle) {

    }

    public void replaceFragment(Fragment fragment, String fragmentTag,
                                Bundle args, boolean addToBackstack,
                                String backstackTag) {
        super.replaceFragment(getSupportFragmentManager(), getHomeContainerId(), fragment, fragmentTag , args, addToBackstack, backstackTag);
    }

    public void addFragment(Fragment fragment, String fragmentTag,
                                Bundle args, boolean addToBackstack,
                                String backstackTag) {
        super.addFragment(getSupportFragmentManager(), getHomeContainerId(), fragment, fragmentTag , args, addToBackstack, backstackTag);
    }

    public void setDecoratedBarcodeView(DecoratedBarcodeView decoratedBarcodeView) {
        mDecoratedBarcodeView = decoratedBarcodeView;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mDecoratedBarcodeView != null) {
            return mDecoratedBarcodeView.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    @Override
    public void launchHomeScreen(Bundle args) {
        addFragment(new HomeListingFragment(), "home", args, false, null);
    }

    @Override
    public void launchSplashScreen(Bundle args) {
        replaceFragment(new HomeListingFragment(), "splash", args, false, null);
    }

    @Override
    public void launchScanningScreen(Bundle args) {
        addFragment(new ScanFragment(), "scan", args, true, "scan");
    }

    @Override
    public void launchDetailScreen(Bundle args) {
        replaceFragment(new DetailFragment(), "detail", args, true, "detail");
    }
}
