package com.project.scanit.ui.rvcomponents.viewtype;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.scanit.application.ScanitApplication;
import com.project.scanit.database.entity.DetailEntity;
import com.project.scanit.ui.base.BaseRecyclerAdapter;
import com.project.scanit.ui.rvcomponents.ViewTypes;
import com.project.scanit.ui.rvcomponents.viewholder.QrlistViewHolder;
import com.project.scanit.ui.rvcomponents.viewitem.QrListViewItem;
import com.project.scanit.ui.rxbus.RxBus;
import com.project.scanit.utils.events.QrItemClick;

import javax.inject.Inject;

public class QrListViewType implements BaseRecyclerAdapter.RecyclerViewDataBinder<QrlistViewHolder, QrListViewItem> {

    @Inject
    RxBus mRxBus;

    public QrListViewType() {
        ScanitApplication.getInstance().getApplicationComponent().inject(this);
    }

    @Override
    public int getViewType() {
        return ViewTypes.RecyclerViewTypes.HOME_LIST_VIEW;
    }

    @Override
    public QrlistViewHolder getViewHolder(ViewGroup parent) {
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        ViewGroup loaderView = (ViewGroup) mInflater.inflate(QrlistViewHolder.getLayoutId(), parent, false);
        return new QrlistViewHolder(loaderView);
    }

    @Override
    public void bindDataToViewHolder(QrlistViewHolder viewHolder, QrListViewItem data, int position, String tag) {
        final DetailEntity detailEntity = data.getData();
        viewHolder.mTitle.setText(detailEntity.getTitle());
        viewHolder.mFormat.setText(detailEntity.getFormat());
        viewHolder.mItemContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mRxBus != null && mRxBus.hasObservers()) {
                    mRxBus.send(new QrItemClick(detailEntity));
                }
            }
        });
    }

    @Override
    public void onViewRecycled(QrlistViewHolder viewHolder) {

    }
}
