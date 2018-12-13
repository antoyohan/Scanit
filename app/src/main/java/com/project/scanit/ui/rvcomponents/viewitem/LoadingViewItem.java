/*
 * Copyright © 2017,Eenadu Television Pvt. Ltd.
 * Modified under contract by Robosoft Technologies Pvt. Ltd.
 */

package com.project.scanit.ui.rvcomponents.viewitem;

import com.project.scanit.ui.base.BaseRecyclerAdapter;
import com.project.scanit.ui.rvcomponents.ViewTypes;

public class LoadingViewItem implements BaseRecyclerAdapter.IViewType {

    public Boolean showError;

    @Override
    public int getViewType() {
        return ViewTypes.RecyclerViewTypes.LOADING_LIST_ITEM;
    }

    public void setData(Boolean showError) {
        this.showError = showError;
    }
}