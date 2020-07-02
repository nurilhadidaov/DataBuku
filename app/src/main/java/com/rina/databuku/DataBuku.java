package com.rina.databuku;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "tblDatabuku")

public class DataBuku implements Serializable {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "kode_buku")
    private
    String kodeBuku;

    @ColumnInfo(name = "judul_buku")
    private
    String judulBuku;

    @ColumnInfo(name = "pengarang")
    private
    String pengarang;

    @ColumnInfo(name = "penerbit")
    private
    String penerbit;

    @ColumnInfo(name = "jumlah_halaman")
    private
    String jumlahHalaman;

    @ColumnInfo(name = "tahun_terbit")
    private
    String tahunTerbit;

    @ColumnInfo(name = "jenis_buku")
    private
    String jenisBuku;

    @NonNull
    public String getKodeBuku() {
        return kodeBuku;
    }

    public void setKodeBuku(@NonNull String kodeBuku) {
        this.kodeBuku = kodeBuku;
    }

    public String getJudulBuku() {
        return judulBuku;
    }

    public void setJudulBuku(String judulBuku) { this.judulBuku = judulBuku; }

    public String getPengarang() {
        return pengarang;
    }

    public void setPengarang(String pengarang) {
        this.pengarang = pengarang;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }

    public String getJumlahHalaman() { return jumlahHalaman; }

    public void setJumlahHalaman(String jumlahHalaman) {
        this.jumlahHalaman = jumlahHalaman;
    }

    public String getTahunTerbit() {
        return tahunTerbit;
    }

    public void setTahunTerbit(String tahunTerbit) {
        this.tahunTerbit = tahunTerbit;
    }

    public String getJenisBuku() {
        return jenisBuku;
    }

    public void setJenisBuku(String jenisBuku) {
        this.jenisBuku = jenisBuku;
    }
}
