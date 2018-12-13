package com.project.scanit.ui.listing;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.project.scanit.R;
import com.project.scanit.application.ScanitApplication;
import com.project.scanit.constants.Constants;
import com.project.scanit.database.entity.DetailEntity;
import com.project.scanit.ui.HomeActivity;
import com.project.scanit.ui.base.BaseFragment;
import com.project.scanit.ui.base.BaseRecyclerAdapter;
import com.project.scanit.ui.model.DetailModel;
import com.project.scanit.ui.model.ToolbarSettings;
import com.project.scanit.ui.rvcomponents.viewitem.QrListViewItem;
import com.project.scanit.utils.events.QrItemClick;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeListingFragment extends BaseFragment implements ListingPresenterContract.View {

    @Inject
    ListingPresenterContract.Presenter mPresenter;

    @BindView(R.id.rv_qr_list)
    RecyclerView mQrList;

    //id of the last content
    private long mLastValue = 0;
    private List<BaseRecyclerAdapter.IViewType> mRecyclerItems = new ArrayList<>();
    private HomeListAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private long mTotalCount;

    @Override
    public void showProgressIndicator() {

    }

    @Override
    public void hideProgressIndicator() {

    }

    @Override
    public void displayApiError(Error error) {

    }

    @Override
    public void loadList(List<DetailEntity> detailList) {
        mLastValue = detailList.get(detailList.size() - 1).getId();
        hidePaginationProgress();
        clearRecyclerViewItems();
        for (DetailEntity entity : detailList) {
            QrListViewItem item = new QrListViewItem();
            item.setData(entity);
            mRecyclerItems.add(item);
        }
        mAdapter.notifyDataSetChanged();
    }

    private void clearRecyclerViewItems() {
        if (mRecyclerItems != null) {
            mRecyclerItems.clear();
        }
    }


    @Override
    public void setCount(long count) {
        mTotalCount = count;
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initViews(View view) {
        mAdapter = new HomeListAdapter(mRecyclerItems);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mQrList.setLayoutManager(mLayoutManager);
        mQrList.setHasFixedSize(true);
        mQrList.setAdapter(mAdapter);
        mQrList.addOnScrollListener(mOnScrollListener);

        mPresenter.getItemCount();
        mPresenter.fetchListData(mLastValue);
    }

    @Override
    protected void initDagger() {
        ListingComponent daggerComponent = DaggerListingComponent.builder().
                applicationComponent(((ScanitApplication) getActivity().getApplication()).
                        getApplicationComponent()).listingModule(new ListingModule(this)).build();
        daggerComponent.inject(this);
    }

    @Override
    protected void handleEvents(Object object) {
        if (object instanceof QrItemClick) {
         handleItemClick(((QrItemClick)object).getDetailEntity());
        }
    }

    private void handleItemClick(DetailEntity object) {
        DetailModel detailModel = generateDetailModel(object);
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.BUNDLE_KEYS.DETAIL_MODEL, detailModel);
        bundle.putBoolean(Constants.BUNDLE_KEYS.SHOW_SAVE_LAYOUT, false);
        ((HomeActivity)getActivity()).launchDetailScreen(bundle);
    }

    private DetailModel generateDetailModel(DetailEntity object) {
        DetailModel model = new DetailModel();
        model.setText(object.getTitle());
        model.setFormat(object.getFormat());
        model.setTextTime(object.getTime());
        return model;
    }

    @Override
    public ToolbarSettings getToolBarSetting() {
        return new ToolbarSettings(getString(R.string.scan_it), true);
    }

    @OnClick(R.id.rl_scan)
    void onScanButtonClick() {
        ((HomeActivity)getActivity()).launchScanningScreen(null);
    }

    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            boolean isFooterNotEnabled = mAdapter != null && !mAdapter.isFooterEnabled();
            if (isFooterNotEnabled && dy > 0) {
                boolean isPaginationConditionValid = false;
                if (mRecyclerItems != null) {
                    isPaginationConditionValid = mLayoutManager.findLastVisibleItemPosition() + 2 == mRecyclerItems.size();
                }
                if (isPaginationConditionValid) {
                    Log.d(TAG, "pagination true " + mTotalCount + " recycler size " + mRecyclerItems.size());
                    if  (mTotalCount > mRecyclerItems.size()) {
                        fetchNextPageData();
                    }
                }
            }
        }
    };

    private void fetchNextPageData() {
        showPaginationProgress();
        mPresenter.fetchListData(mLastValue);
    }

    protected void showPaginationProgress() {
        if (mAdapter != null) {
            mAdapter.showFooterWithProgress();
        }
    }

    protected void hidePaginationProgress() {
        if (mAdapter != null) {
            mAdapter.hideFooter();
        }
    }
}
