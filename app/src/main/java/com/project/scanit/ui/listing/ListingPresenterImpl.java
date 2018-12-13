package com.project.scanit.ui.listing;


import com.project.scanit.database.entity.DetailEntity;
import com.project.scanit.io.manager.OfflineQRManager;

import java.util.List;

public class ListingPresenterImpl implements ListingPresenterContract.Presenter {

    private ListingPresenterContract.View mView;

    public ListingPresenterImpl(ListingPresenterContract.View view) {
        mView = view;
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void fetchListData(long value) {
        List<DetailEntity> detailList = new OfflineQRManager().getPaginatedContent(value);
        if (detailList.size() > 0) {
            mView.loadList(detailList);
        }
    }

    @Override
    public void getItemCount() {
        long count = new OfflineQRManager().getTotalCount();
        mView.setCount(count);
    }
}
