package com.rina.databuku;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class DetailData extends AppCompatActivity {

    private EditText getKodebuku, getJudulbuku, getPengarang, getPenerbit, getJumlahhalaman, getTahunterbit, getJenisbuku;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data);

        getKodebuku = findViewById(R.id.myKdbuku);
        getJudulbuku = findViewById(R.id.myJudulbuku);
        getPengarang = findViewById(R.id.myPengarang);
        getPenerbit = findViewById(R.id.myPenerbit);
        getJumlahhalaman = findViewById(R.id.myHalaman);
        getTahunterbit = findViewById(R.id.myThnterbit);
        getJenisbuku = findViewById(R.id.myJenisbuku);

        getDetailData();
    }

    private void getDetailData() {
        DataBuku dataBuku = (DataBuku) getIntent().getSerializableExtra("detail");

        if (dataBuku != null) {
            getKodebuku.setText(dataBuku.getKodeBuku());
            getJudulbuku.setText(dataBuku.getJudulBuku());
            getPengarang.setText(dataBuku.getPengarang());
            getPenerbit.setText(dataBuku.getPenerbit());
            getJumlahhalaman.setText(dataBuku.getJumlahHalaman());
            getTahunterbit.setText((dataBuku.getTahunTerbit()));
            getJenisbuku.setText(dataBuku.getJenisBuku());
        }
    }
}
