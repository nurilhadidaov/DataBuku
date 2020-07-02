package com.rina.databuku;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface DataBukuDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertDatabuku(DataBuku dataBuku);

    @Query("SELECT * FROM tblDatabuku")
    DataBuku[] readDatabuku();

    @Update
    int updateDatabuku(DataBuku dataBuku);

    @Delete
    void deleteDatabuku(DataBuku dataBuku);

    @Query("SELECT * FROM tblDatabuku WHERE kode_buku = :kdbuku LIMIT 1")
    DataBuku selectDetailDatabuku(String kdbuku);
}
