package com.gorbulevsv.androidnotificationmy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final int NOTIFY_ID = 101;
    private static String CHANNEL_ID = "Канал 1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ButtonSendNotificationOnClick(View view) {
        // 1. Менеджер уведомлений
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // 2. Канал уведомлений
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel =
                    new NotificationChannel(CHANNEL_ID, "Мой канал", NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("Описание");
            channel.setLightColor(Color.RED);
            channel.enableLights(true);
            channel.enableVibration(true);
            notificationManager.createNotificationChannel(channel);
        }

        // 3. Уведомление
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentTitle("Всем привет")
                        .setContentText("Как вы поживаете?")
                        .setSound(Settings.System.DEFAULT_NOTIFICATION_URI);

        // 4. Отправить уведомление
        notificationManager.notify(NOTIFY_ID, builder.build());
    }
}