package com.project.scanit.ui.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.scanit.ui.model.ToolbarSettings;
import com.project.scanit.ui.rxbus.RxBus;
import com.project.scanit.ui.rxbus.RxBusCallback;
import com.project.scanit.ui.rxbus.RxBusHelper;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment implements RxBusCallback {

    protected static final String TAG = BaseFragment.class.getSimpleName();

    @Inject
    RxBus mRxBus;
    private RxBusHelper mRxBusHelper;
    private Unbinder mUnBinder;

    abstract protected int getFragmentLayoutId();

    protected abstract void initViews(View view);

    protected abstract void initDagger();

    protected abstract void handleEvents(Object object);

    public abstract ToolbarSettings getToolBarSetting();

    protected View inflateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getFragmentLayoutId(), container, false);
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initDagger();

        View view = inflateView(inflater, container, savedInstanceState);

        setRetainInstance(true);

        mUnBinder = ButterKnife.bind(this, view);

        getBundleData();

        setupActionBar();

        registerEvents();

        initViews(view);

        return view;
    }

    private void setupActionBar() {
        setupActionBar(getToolBarSetting());
    }

    protected void setupActionBar(ToolbarSettings settings) {
        BaseActivity activity = (BaseActivity) getActivity();
        activity.setToolBar(settings);
    }


    private void registerEvents() {
        mRxBusHelper = new RxBusHelper();
        mRxBusHelper.registerEvents(mRxBus, TAG, this);
    }

    protected void getBundleData() {

    }

    @Override
    public void onEventTrigger(Object event) {
        handleEvents(event);
    }


    @Override
    public void onDestroyView() {

        if (mUnBinder != null) {
            mUnBinder.unbind();
            mUnBinder = null;
        }
        if (mRxBusHelper != null) {
            mRxBusHelper.unSubScribe();
        }
        super.onDestroyView();
    }

    public boolean onNavigationClick() {
        return false;
    }

    public boolean onBackPressed() {
        return false;
    }

    protected void showProgressBar() {
        ((BaseActivity) getActivity()).showProgressBar();
    }

    protected void hideProgressBar() {
        ((BaseActivity) getActivity()).hideProgressBar();
    }

    protected RxBus getRxBus() {
        return mRxBus;
    }
}
