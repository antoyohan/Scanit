/*
 * Copyright © 2017, Eenadu Television Pvt. Ltd.
 * Modified under contract by Robosoft Technologies Pvt. Ltd.
 */

package com.project.scanit.ui.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.project.scanit.ui.rvcomponents.ViewTypes;
import com.project.scanit.ui.rvcomponents.viewitem.LoadingViewItem;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private SparseArray<RecyclerViewDataBinder> mRecyclerViewTypes;
    private List<IViewType> mDataList = new ArrayList<>();
    private OnPageEndListener mOnPageEndListener = null;
    private String mTag;


    public BaseRecyclerAdapter(@NonNull List<IViewType> dataList, String tag) {
        mTag = tag;
        setDataList(dataList);
        List<RecyclerViewDataBinder> recyclerViewHolderTypes = getViewDataBinders();
        mRecyclerViewTypes = new SparseArray<>(recyclerViewHolderTypes.size());

        for (RecyclerViewDataBinder recyclerViewHolderType : recyclerViewHolderTypes) {
            mRecyclerViewTypes.put(recyclerViewHolderType.getViewType(), recyclerViewHolderType);
        }
    }

    protected abstract
    @NonNull
    List<RecyclerViewDataBinder> getViewDataBinders();

    public void setDataList(List<IViewType> dataList) {
        mDataList = dataList;
    }

    public List<IViewType> getDataList() {
        return mDataList;
    }

    public void clearDataList() {
        if (mDataList != null)
            mDataList.clear();
    }

    public void setRecyclerOnPageEndListener(OnPageEndListener onPageEndListener) {
        mOnPageEndListener = onPageEndListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerViewDataBinder recyclerViewType = mRecyclerViewTypes.get(viewType);

        return recyclerViewType.getViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == getItemCount() - 1 && mOnPageEndListener != null) {
            mOnPageEndListener.onPageEnd(position + 1);
        }
        RecyclerViewDataBinder recyclerViewType = mRecyclerViewTypes.get(getItemViewType(position));
        recyclerViewType.bindDataToViewHolder(holder, mDataList.get(position), position, mTag);
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        super.onViewRecycled(holder);
      /*  int adapterPosition = holder.getAdapterPosition();
        if (adapterPosition >= 0 && adapterPosition <= mRecyclerViewTypes.size()) {
            RecyclerViewDataBinder recyclerViewType = mRecyclerViewTypes.get(getItemViewType(adapterPosition));
            if (recyclerViewType != null) {
                recyclerViewType.onViewRecycled(holder);
            }
        }*/
    }

    @Override
    public int getItemCount() {

        if (mDataList == null)
            return 0;
        else
            return mDataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mDataList.get(position).getViewType();
    }

    public interface IViewType {
        int getViewType();
    }

    public interface RecyclerViewDataBinder<G extends RecyclerView.ViewHolder, T extends IViewType> extends IViewType {
        G getViewHolder(ViewGroup parent);

        void bindDataToViewHolder(G viewHolder, T data, int position, String tag);

        void onViewRecycled(G viewHolder);

    }

    public interface RecyclerViewItem<T> extends IViewType {
        T getData();

        void setData(T model);
    }

    public interface OnPageEndListener {
        void onPageEnd(int position);
    }

    public void showFooterWithProgress() {
        if (!isFooterEnabled()) {
            LoadingViewItem loadingViewItem = new LoadingViewItem();
            loadingViewItem.setData(false);
            mDataList.add(loadingViewItem);
            notifyItemInserted(mDataList.size());
        }
    }

    public void showFooterWithError() {
        if (isFooterEnabled()) {
            int pos = mDataList.size() - 1;
            LoadingViewItem loadingViewItem = (LoadingViewItem) mDataList.get(pos);
            loadingViewItem.setData(true);
            notifyItemChanged(pos);
        }
    }

    public void hideFooter() {
        if (isFooterEnabled()) {
            int pos = mDataList.size() - 1;
            mDataList.remove(pos);
            notifyItemRemoved(pos);
        }
    }

    public boolean isFooterEnabled() {
        return mDataList != null && mDataList.size() > 0
                && mDataList.get(mDataList.size() - 1).getViewType() == ViewTypes.RecyclerViewTypes.LOADING_LIST_ITEM;
    }

}
