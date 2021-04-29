package com.example.signin.handlers;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;

import com.example.signin.R;
import com.example.signin.notifications;

public class BigPictureSocialMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_picture_main);

        // Cancel Notification
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.cancel(notifications.NOTIFICATION_ID);

        // TODO: Handle and display social post from your database
    }
}