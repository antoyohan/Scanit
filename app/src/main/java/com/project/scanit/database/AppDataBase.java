package com.project.scanit.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

import com.project.scanit.application.ScanitApplication;
import com.project.scanit.constants.Constants;
import com.project.scanit.database.dao.DetailDao;
import com.project.scanit.database.entity.DetailEntity;

@Database(entities = {DetailEntity.class}, version = Constants.DB_VERSION)
public abstract class AppDataBase extends RoomDatabase {

    private static AppDataBase INSTANCE;

    public abstract DetailDao detailDao();

    public static AppDataBase getAppDatabase() {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(ScanitApplication.getInstance(), AppDataBase.class, Constants.DB_NAME)
                            // allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            .allowMainThreadQueries()
                            .build();
        }

        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}