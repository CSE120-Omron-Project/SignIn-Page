package com.example.signin.handlers;

import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;
import android.graphics.BitmapFactory;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import com.example.signin.GlobalNotificationBuilder;
import com.example.signin.MockDatabase;
import com.example.signin.R;
import com.example.signin.notifications;

import java.util.concurrent.TimeUnit;

public class BigTextIntentService extends IntentService {
    private static final String TAG = "BigTextService";


    public static final String ACTION_DISMISS =
            "com.example.signin.handlers.action.DISMISS";
    public static final String ACTION_SNOOZE =
            "com.example.signin.handlers.action.SNOOZE";

    private static final long SNOOZE_TIME = TimeUnit.SECONDS.toMillis(5);

    public BigTextIntentService() { super("BigTextIntentService"); }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "onHandleIntent(): " + intent);

        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_DISMISS.equals(action)) {
                handleActionDismiss();
            } else if (ACTION_SNOOZE.equals(action)) {
                handleActionSnooze();
            }
        }
    }


    /**
     * Handles action Dismiss in the provided background thread.
     */
    private void handleActionDismiss() {
        Log.d(TAG, "handleActionDismiss()");

        NotificationManagerCompat notificationManagerCompat =
                NotificationManagerCompat.from(getApplicationContext());
        notificationManagerCompat.cancel(notifications.NOTIFICATION_ID);
    }

    /**
     * Handles action Snooze in the provided background thread.
     */
    private void handleActionSnooze() {
        Log.d(TAG, "handleActionSnooze()");

        // You could use NotificationManager.getActiveNotifications() if you are targeting SDK 23
        // and above, but we are targeting devices with lower SDK API numbers, so we saved the
        // builder globally and get the notification back to recreate it later.

        NotificationCompat.Builder notificationCompatBuilder =
                GlobalNotificationBuilder.getNotificationCompatBuilderInstance();

        // Recreate builder from persistent state if app process is killed
//        if (notificationCompatBuilder == null) {
//            // Note: New builder set globally in the method
//            notificationCompatBuilder = recreateBuilderWithBigTextStyle();
//        }

        Notification notification;
        notification = notificationCompatBuilder.build();


        if (notification != null) {
            NotificationManagerCompat notificationManagerCompat =
                    NotificationManagerCompat.from(getApplicationContext());

            notificationManagerCompat.cancel(notifications.NOTIFICATION_ID);

            try {
                Thread.sleep(SNOOZE_TIME);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            notificationManagerCompat.notify(notifications.NOTIFICATION_ID, notification);
        }

    }


    /*
     * This recreates the notification from the persistent state in case the app process was killed.
     * It is basically the same code for creating the Notification from MainActivity.
     */
    private NotificationCompat.Builder recreateBuilderWithBigTextStyle() {

        // Main steps for building a BIG_TEXT_STYLE notification (for more detailed comments on
        // building this notification, check MainActivity.java)::
        //      0. Get your data
        //      1. Build the BIG_TEXT_STYLE
        //      2. Set up main Intent for notification
        //      3. Create additional Actions for the Notification
        //      4. Build and issue the notification

        // 0. Get your data (everything unique per Notification).
        MockDatabase.BigTextStyleReminderAppData bigTextStyleReminderAppData =
                MockDatabase.getBigTextStyleData();

        // 1. Retrieve Notification Channel for O and beyond devices (26+). We don't need to create
        //    the NotificationChannel, since it was created the first time this Notification was
        //    created.
        String notificationChannelId = bigTextStyleReminderAppData.getChannelId();

        // 2. Build the BIG_TEXT_STYLE.
        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle()
                .bigText(bigTextStyleReminderAppData.getBigText())
                .setBigContentTitle(bigTextStyleReminderAppData.getBigContentTitle())
                .setSummaryText(bigTextStyleReminderAppData.getSummaryText());


        // 3. Set up main Intent for notification
        Intent notifyIntent = new Intent(this, BigTextNotifications.class);
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent notifyPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        notifyIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );


        // 4. Create additional Actions (Intents) for the Notification

//        // Snooze Action
        Intent snoozeIntent = new Intent(this, BigTextIntentService.class);
        snoozeIntent.setAction(BigTextIntentService.ACTION_SNOOZE);

        PendingIntent snoozePendingIntent = PendingIntent.getService(this, 0, snoozeIntent, 0);
        NotificationCompat.Action snoozeAction =
                new NotificationCompat.Action.Builder(
                        R.drawable.omron,
                        "Snooze",
                        snoozePendingIntent)
                        .build();


//        // Dismiss Action
        Intent dismissIntent = new Intent(this, BigTextIntentService.class);
        dismissIntent.setAction(BigTextIntentService.ACTION_DISMISS);
//
        PendingIntent dismissPendingIntent = PendingIntent.getService(this, 0, dismissIntent, 0);
        NotificationCompat.Action dismissAction =
                new NotificationCompat.Action.Builder(
                        R.drawable.omron,
                        "Dismiss",
                        dismissPendingIntent)
                        .build();
//
//
//        // 5. Build and issue the notification.
//
        // Notification Channel Id is ignored for Android pre O (26).
        NotificationCompat.Builder notificationCompatBuilder =
                new NotificationCompat.Builder(
                        getApplicationContext(), notificationChannelId);

        GlobalNotificationBuilder.setNotificationCompatBuilderInstance(notificationCompatBuilder);

        notificationCompatBuilder
                .setStyle(bigTextStyle)
                .setContentTitle(bigTextStyleReminderAppData.getContentTitle())
                .setContentText(bigTextStyleReminderAppData.getContentText())
                .setLargeIcon(BitmapFactory.decodeResource(
                        getResources(),
                        R.drawable.omron))
                .setContentIntent(notifyPendingIntent)
//                .setColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary))
                .setCategory(Notification.CATEGORY_REMINDER)
                .setPriority(bigTextStyleReminderAppData.getPriority())
                .setVisibility(bigTextStyleReminderAppData.getChannelLockscreenVisibility())
                .addAction(snoozeAction)
                .addAction(dismissAction);



//
        return notificationCompatBuilder;
//    }

    }
}


