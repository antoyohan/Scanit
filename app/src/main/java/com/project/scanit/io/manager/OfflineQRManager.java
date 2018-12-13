package com.project.scanit.io.manager;


import com.project.scanit.database.AppDataBase;
import com.project.scanit.database.entity.DetailEntity;

import java.util.List;

public class OfflineQRManager {

    public OfflineQRManager() {
    }

    public void insertToDataBase(DetailEntity info) {
        AppDataBase.getAppDatabase().detailDao().insert(info);
    }

    public List<DetailEntity> getPaginatedContent(long lastValue) {
        return AppDataBase.getAppDatabase().detailDao().getPaginatedData(lastValue);
    }

    public Long getTotalCount() {
        return AppDataBase.getAppDatabase().detailDao().getCount();
    }
}
