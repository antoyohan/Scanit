package com.project.scanit.utils.events;


import com.project.scanit.database.entity.DetailEntity;

public class QrItemClick {

    private DetailEntity mDetailEntity;

    public QrItemClick(DetailEntity detailEntity) {
        mDetailEntity = detailEntity;
    }

    public DetailEntity getDetailEntity() {
        return mDetailEntity;
    }
}
