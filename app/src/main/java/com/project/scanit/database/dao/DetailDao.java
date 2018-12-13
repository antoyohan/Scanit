package com.project.scanit.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.project.scanit.database.entity.DetailEntity;

import java.util.List;

@Dao
public interface DetailDao {

    @Insert
    void insert(DetailEntity info);

    @Delete
    void delete(DetailEntity info);

    @Update
    void update(DetailEntity info);

    @Query("SELECT * FROM DETAIL_TABLE where timestamp > :lastValue ORDER BY timestamp DESC LIMIT 10")
    List<DetailEntity> getPaginatedData(long lastValue);

    @Query("SELECT COUNT(*) FROM DETAIL_TABLE")
    Long getCount();
}
