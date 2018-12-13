package com.project.scanit.ui.listing;


import com.project.scanit.database.entity.DetailEntity;
import com.project.scanit.ui.base.BasePresenter;
import com.project.scanit.ui.base.BaseView;

import java.util.List;

public interface ListingPresenterContract {

    public interface Presenter extends BasePresenter {
        void fetchListData(long value);

        void getItemCount();
    }

    public interface View extends BaseView {
        void loadList(List<DetailEntity> detailList);

        void setCount(long count);
    }
}
