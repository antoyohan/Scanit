/*
 *  Copyright © 2017,Eenadu Television Pvt. Ltd.
 *  Modified under contract by Robosoft Technologies Pvt. Ltd.
 */

package com.project.scanit.ui.base;


import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.project.scanit.application.ScanitApplication;
import com.project.scanit.ui.rxbus.RxBus;

import javax.inject.Inject;

import butterknife.ButterKnife;

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    @Inject
    protected RxBus mRxBus;

    private String tag;

    public BaseViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        ScanitApplication.getInstance().getApplicationComponent().inject(this);
    }

    public String getTag() {
        return tag;
    }

    /**
     * @param tag - identifies source of list item
     */
    public void setTag(String tag) {
        this.tag = tag;
    }
}
