package com.rina.databuku;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.ArrayList;

public class RecyclerBukuAdapter extends RecyclerView.Adapter<RecyclerBukuAdapter.ViewHolder> {

    private ArrayList<DataBuku> daftarDatabuku;
    private AppDatabase appDatabase;
    private Context context;

    public RecyclerBukuAdapter(ArrayList<DataBuku> daftarDatabuku, Context context){

    this.daftarDatabuku = daftarDatabuku;
    this.context = context;
    appDatabase = Room.databaseBuilder(
            context.getApplicationContext(),
            AppDatabase.class, "dbDatabuku").
            allowMainThreadQueries().build();
    }
    class ViewHolder extends RecyclerView.ViewHolder{

    private TextView kodeBuku, judulBuku;
    private CardView item;

    ViewHolder(View itemView) {
        super(itemView);
        kodeBuku = itemView.findViewById(R.id.kdbuku);
        judulBuku = itemView.findViewById(R.id.judulbuku);
        item = itemView.findViewById(R.id.cvMain);
    }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inisialisasi Layout Item untuk RecyclerView
        View v =  LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_layout, parent, false);
        return new ViewHolder(v);
    }
    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        //Deklarasi Variable untuk mendapatkan data dari Database melalui Array
        final String getKodebuku = daftarDatabuku.get(position).getKodeBuku();
        final String getJudulbuku = daftarDatabuku.get(position).getJudulBuku();

        //Menampilkan data berdasarkan posisi Item dari RecyclerView
        holder.kodeBuku.setText(getKodebuku);
        holder.judulBuku.setText(getJudulbuku);

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBuku dataBuku = appDatabase.dataBukuDAO()
                        .selectDetailDatabuku(daftarDatabuku.get(position).getKodeBuku());
                context.startActivity(new Intent(context, DetailData.class).putExtra("detail", dataBuku));
            }
        });

        holder.item.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                CharSequence[] menuPilihan = {"Edit", "Delete"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(v.getContext())
                        .setTitle("Pilih Aksi")
                        .setItems(menuPilihan, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:
                                        /*
                                         Menjalankan Perintah Edit Data
                                         Menggunakan Bundle untuk mengambil data yang akan Diedit
                                         */
                                        onEditData(position, context);
                                        break;

                                    case 1:
                                        onDeleteData(position);
                                        break;
                                }
                            }
                        });
                dialog.create();
                dialog.show();
                return true;
            }
        });
    }
    private void onDeleteData(int position){
        appDatabase.dataBukuDAO().deleteDatabuku(daftarDatabuku.get(position));
        daftarDatabuku.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, daftarDatabuku.size());
        Toast.makeText(context, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show();
    }

    //Mengirim Data yang akan diedit dari ArrayList berdasarkan posisi item pada RecyclerView
    private void onEditData(int position, Context context){
        context.startActivity(new Intent(context, EditData.class).putExtra("data", daftarDatabuku.get(position)));
        ((Activity)context).finish();
    }

    @Override
    public int getItemCount() {
        //Menghitung data / ukuran dari Array
        return daftarDatabuku.size();
    }
    }
