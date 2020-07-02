package com.rina.databuku;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {DataBuku.class}, version = 4, exportSchema = false)
    public abstract class AppDatabase extends RoomDatabase {

        //Untuk mengakses Database menggunakan method abstract
        public abstract DataBukuDAO dataBukuDAO();
    }

