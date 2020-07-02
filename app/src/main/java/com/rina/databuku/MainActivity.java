package com.rina.databuku;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.optionmenu, menu);
            //getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;

        }

        public boolean onOptionsItemSelected(MenuItem item) {
            if (item.getItemId() == R.id.about) {
                startActivity(new Intent(this, Profil.class));
            }
            return true;
        }

    public void inputData(View view) {
        Intent intent = new Intent(MainActivity.this, InputDataBuku .class);
        startActivity(intent);
    }

    public void lihatData(View view) {
        Intent intent = new Intent(MainActivity.this, ReadData .class);
        startActivity(intent);
    }
}
