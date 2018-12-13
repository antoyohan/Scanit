package com.project.scanit.ui.rvcomponents.viewholder;


import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.scanit.R;
import com.project.scanit.ui.base.BaseViewHolder;

import butterknife.BindView;

public class QrlistViewHolder extends BaseViewHolder {

    @BindView(R.id.tv_title)
    public TextView mTitle;
    @BindView(R.id.tv_format)
    public TextView mFormat;
    @BindView(R.id.ll_container)
    public LinearLayout mItemContainer;

    public QrlistViewHolder(View itemView) {
        super(itemView);
    }

    public static int getLayoutId() {
        return R.layout.qr_list_view_holder;
    }
}
