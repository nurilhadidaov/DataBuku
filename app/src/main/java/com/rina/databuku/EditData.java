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

public class EditData extends AppCompatActivity {

    private EditText Judul, Penerbit, Pengarang, Jumhal, tahunTerbit, jenisBuku;
    private AppDatabase database;
    private Button buttonSimpan;
    private DataBuku dataBuku;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);

        Judul = findViewById(R.id.judulbuku);
        Penerbit = findViewById(R.id.penerbit);
        Pengarang = findViewById(R.id.pengarang);
        Jumhal = findViewById(R.id.jmlhalaman);
        tahunTerbit = findViewById(R.id.thnterbit);
        jenisBuku = findViewById(R.id.jenisbk);
        buttonSimpan = findViewById(R.id.buttonSimpan);

        database = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "dbDatabuku").build();

        getDataDataBuku();

        buttonSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dataBuku.setKodeBuku(kodeBuku.getText().toString());
                dataBuku.setJudulBuku(Judul.getText().toString());
                dataBuku.setPengarang(Pengarang.getText().toString());
                dataBuku.setPenerbit(Penerbit.getText().toString());
                dataBuku.setJumlahHalaman(Jumhal.getText().toString());
                dataBuku.setTahunTerbit(tahunTerbit.getText().toString());
                dataBuku.setJenisBuku(jenisBuku.getText().toString());
                updateData(dataBuku);
            }
        });
    }
    private void getDataDataBuku(){
        //Mendapatkan data dari Intent
        dataBuku = (DataBuku) getIntent().getSerializableExtra("data");

      //  kodeBuku.setText(dataBuku.getKodeBuku());
        Judul.setText(dataBuku.getJudulBuku());
        Pengarang.setText(dataBuku.getPengarang());
        Penerbit.setText(dataBuku.getPenerbit());
        Jumhal.setText(dataBuku.getJumlahHalaman());
        tahunTerbit.setText(dataBuku.getTahunTerbit());
       jenisBuku.setText(dataBuku.getJenisBuku());
    }
    @SuppressLint("StaticFieldLeak")
    private void updateData(final DataBuku dataBuku){
        new AsyncTask<Void, Void, Integer>() {
            @Override
            protected Integer doInBackground(Void... voids) {
                //Menjalankan proses insert data
                return database.dataBukuDAO().updateDatabuku(dataBuku);
            }

            @Override
            protected void onPostExecute(Integer status) {
                //Menandakan bahwa data berhasil disimpan
                Toast.makeText(EditData.this, "Data Berhasil Diubah", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(EditData.this, ReadData.class));
                finish();
            }
        }.execute();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(EditData.this, ReadData.class));
        finish();
    }
}
