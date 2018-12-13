package com.project.scanit.ui.rvcomponents.viewitem;


import com.project.scanit.database.entity.DetailEntity;
import com.project.scanit.ui.base.BaseRecyclerAdapter;
import com.project.scanit.ui.rvcomponents.ViewTypes;

public class QrListViewItem implements BaseRecyclerAdapter.IViewType  {

    private DetailEntity data;

    @Override
    public int getViewType() {
        return ViewTypes.RecyclerViewTypes.HOME_LIST_VIEW;
    }

    public DetailEntity getData() {
        return data;
    }

    public void setData(DetailEntity data) {
        this.data = data;
    }
}
