package com.rina.databuku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class InputDataBuku extends AppCompatActivity implements View.OnClickListener{

    private EditText kodeBuku, Judul, Penerbit, Pengarang, Jumhal, tahunTerbit, jenisBuku;
    private AppDatabase database;
    private Button buttonSimpan, buttonLihat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data_buku);

        kodeBuku = findViewById(R.id.kdbuku);
        Judul = findViewById(R.id.judulbuku);
        Penerbit = findViewById(R.id.penerbit);
        Pengarang = findViewById(R.id.pengarang);
        Jumhal = findViewById(R.id.jmlhalaman);
        tahunTerbit = findViewById(R.id.thnterbit);
        jenisBuku = findViewById(R.id.jenisbk);
        buttonSimpan = findViewById(R.id.save);
        buttonSimpan.setOnClickListener(this);
        buttonLihat = findViewById(R.id.show);
        buttonLihat.setOnClickListener(this);

        database = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                "dbDatabuku").build();
    }
    @SuppressLint("StaticFieldLeak")
    private void insertData(final DataBuku dataBuku) {
        new AsyncTask<Void, Void, Long>() {
            @Override
            protected Long doInBackground(Void... voids) {
                //Menjalankan proses insert data
                return database.dataBukuDAO().insertDatabuku(dataBuku);
            }

            @Override
            protected void onPostExecute(Long status) {
                //Menandakan bahwa data berhasil disimpan
                Toast.makeText(InputDataBuku.this, "Status Row " + status, Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save:

                //Mengecek Data NIM dan Nama
                if (Judul.getText().toString().isEmpty() || Pengarang.getText().toString().isEmpty()) {
                    Toast.makeText(InputDataBuku.this, "Judul dan Pengarang tidak boleh kosong", Toast.LENGTH_SHORT).show();
                } else {
                    //Membuat Instance/Objek Dari Class Entity databuku
                    DataBuku data = new DataBuku();

                    //Memasukan data yang diinputkan user pada database
                    data.setKodeBuku(kodeBuku.getText().toString());
                    data.setJudulBuku(Judul.getText().toString());
                    data.setPengarang(Pengarang.getText().toString());
                    data.setPenerbit(Penerbit.getText().toString());
                    data.setJumlahHalaman(Jumhal.getText().toString());
                    data.setTahunTerbit(tahunTerbit.getText().toString());
                    data.setJenisBuku(jenisBuku.getText().toString());
                    insertData(data);

                    //Mengembalikan EditText menjadi seperti semula\
                    kodeBuku.setText("");
                    Judul.setText("");
                    Pengarang.setText("");
                    Penerbit.setText("");
                    Jumhal.setText("");
                    tahunTerbit.setText("");
                    jenisBuku.setText("");

                }
                break;

            case R.id.show:
                startActivity(new Intent(InputDataBuku.this, ReadData.class));
                break;
        }
    }
}