/*
 * Copyright © 2017,Eenadu Television Pvt. Ltd.
 * Modified under contract by Robosoft Technologies Pvt. Ltd.
 */

package com.project.scanit.ui.rvcomponents.viewholder;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.project.scanit.R;
import com.project.scanit.ui.base.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoadingViewHolder extends BaseViewHolder {

    @BindView(R.id.progressbar)
    public ProgressBar mProgressBar;
    @BindView((R.id.pagination_fail_msg))
    public TextView mErrorLabel;

    public LoadingViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public static int getLayoutId() {
        return R.layout.item_list_loader;
    }

    @OnClick(R.id.pagination_fail_msg)
    void onPaginationLoaderClick() {
        if (mRxBus != null && mRxBus.hasObservers()) {
           // mRxBus.send(new OnPaginationLoaderClick());
        }
    }

}
