package com.rina.databuku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;

public class ReadData extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private AppDatabase database;
    private ArrayList<DataBuku> daftarDatabuku;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_data);
        getSupportActionBar().setTitle("Daftar Data Buku");

        //Inisialisasi ArrayList
        daftarDatabuku = new ArrayList<>();

        //Inisialisasi RoomDatabase
        database = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "dbDatabuku").allowMainThreadQueries().build();

        /*
         * Mengambil data Mahasiswa dari Database
         * lalu memasukannya ke kedalam ArrayList (daftarMahasiswa)
         */
        daftarDatabuku.addAll(Arrays.asList(database.dataBukuDAO().readDatabuku()));

        recyclerView = findViewById(R.id.dataItem);

        //Agar ukuran RecyclerView tidak berubah
        recyclerView.setHasFixedSize(true);

        //Menentukan bagaimana item pada RecyclerView akan tampil
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Mamasang adapter pada RecyclerView
        adapter = new RecyclerBukuAdapter(daftarDatabuku, ReadData.this);
        recyclerView.setAdapter(adapter);
    }
    }

