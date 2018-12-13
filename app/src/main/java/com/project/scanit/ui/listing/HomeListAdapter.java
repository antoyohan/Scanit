package com.project.scanit.ui.listing;


import android.support.annotation.NonNull;

import com.project.scanit.ui.base.BaseRecyclerAdapter;
import com.project.scanit.ui.rvcomponents.viewtype.LoadingViewType;
import com.project.scanit.ui.rvcomponents.viewtype.QrListViewType;

import java.util.ArrayList;
import java.util.List;

public class HomeListAdapter extends BaseRecyclerAdapter {
    public HomeListAdapter(@NonNull List<IViewType> dataList) {
        super(dataList, HomeListAdapter.class.getName());
    }

    @NonNull
    @Override
    protected List<RecyclerViewDataBinder> getViewDataBinders() {
        ArrayList<RecyclerViewDataBinder> viewDataBinders = new ArrayList<>();
        viewDataBinders.add(new LoadingViewType());
        viewDataBinders.add(new QrListViewType());
        return viewDataBinders;
    }
}
