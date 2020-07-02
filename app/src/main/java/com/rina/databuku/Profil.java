package com.rina.databuku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Profil extends AppCompatActivity {


    private static final String LOG_TAG = Profil.class.getSimpleName();
    // Constants for the notification actions buttons.
    private static final String ACTION_UPDATE_NOTIFICATION = "com.selmashaf.spponline.ACTION_UPDATE_NOTIFICATION";
    // Notification channel ID.
    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
    // Notification ID.
    private static final int NOTIFICATION_ID = 0;

    private final String COLOR_KEY = "color";

    private TextView mShowTextView;
    private int mColor;

    private SharedPreferences mPreferences;
    private String sharedPrefFile =
            "com.selmashaf.spponline";
    private Button btnReset;
    private NotificationManager mNotifyManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        createNotificationChannel();


        mPreferences = getSharedPreferences(
                sharedPrefFile, MODE_PRIVATE);

        mShowTextView = findViewById(R.id.profilkita);
        mColor = mPreferences.getInt(COLOR_KEY, mColor);
        mShowTextView.setBackgroundColor(mColor);

        btnReset = findViewById(R.id.resetbtn);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendNotification();
            }
        });
    }

        public void sendNotification () {

            NotificationCompat.Builder notifyBuilder = getNotificationBuilder();
            mNotifyManager.notify(NOTIFICATION_ID, notifyBuilder.build());
        }



        public void createNotificationChannel () {
            mNotifyManager = (NotificationManager)
                    getSystemService(NOTIFICATION_SERVICE);
            if (android.os.Build.VERSION.SDK_INT >=
                    android.os.Build.VERSION_CODES.O) {
                // Create a NotificationChannel
                NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID,
                        "Mascot Notification", NotificationManager
                        .IMPORTANCE_HIGH);
                notificationChannel.enableLights(true);
                notificationChannel.setLightColor(Color.RED);
                notificationChannel.enableVibration(true);
                notificationChannel.setDescription("Notification from Mascot");
                mNotifyManager.createNotificationChannel(notificationChannel);
            }
        }


        private NotificationCompat.Builder getNotificationBuilder () {
            NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID)
                    .setContentTitle("Notif!")
                    .setContentText("Your background successfully removed")
                    .setSmallIcon(R.drawable.fix);

            Toast.makeText(this, "Reset Background", Toast.LENGTH_SHORT).show();

            return notifyBuilder;
        }




    public void changeBackground(View view) {
        int color = ((ColorDrawable) view.getBackground()).getColor();
        mShowTextView.setBackgroundColor(color);
        mColor = color;
    }

    public void reset(View view) {
        mColor = ContextCompat.getColor(this, R.color.default_background);
        mShowTextView.setBackgroundColor(mColor);

        // Clear preferences
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.clear();
        preferencesEditor.apply();

    }
}
