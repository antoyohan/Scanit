/*
 * Copyright © 2017,Eenadu Television Pvt. Ltd.
 * Modified under contract by Robosoft Technologies Pvt. Ltd.
 */

package com.project.scanit.ui.rvcomponents.viewtype;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.scanit.ui.base.BaseRecyclerAdapter;
import com.project.scanit.ui.rvcomponents.ViewTypes;
import com.project.scanit.ui.rvcomponents.viewholder.LoadingViewHolder;
import com.project.scanit.ui.rvcomponents.viewitem.LoadingViewItem;

public class LoadingViewType implements BaseRecyclerAdapter.RecyclerViewDataBinder<LoadingViewHolder, LoadingViewItem> {

    @Override
    public LoadingViewHolder getViewHolder(ViewGroup parent) {
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        ViewGroup loaderView = (ViewGroup) mInflater.inflate(LoadingViewHolder.getLayoutId(), parent, false);
        return new LoadingViewHolder(loaderView);
    }

    @Override
    public void bindDataToViewHolder(LoadingViewHolder viewHolder, LoadingViewItem data, int position, String tag) {
        if (data != null) {
            Boolean showError = data.showError;
            if (showError) {
                viewHolder.mProgressBar.setVisibility(View.GONE);
                viewHolder.mErrorLabel.setVisibility(View.VISIBLE);
            } else {
                viewHolder.mProgressBar.setVisibility(View.VISIBLE);
                viewHolder.mErrorLabel.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getViewType() {
        return ViewTypes.RecyclerViewTypes.LOADING_LIST_ITEM;
    }

    @Override
    public void onViewRecycled(LoadingViewHolder viewHolder) {

    }
}
