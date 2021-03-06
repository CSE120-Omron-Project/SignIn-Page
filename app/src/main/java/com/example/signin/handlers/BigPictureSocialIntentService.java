package com.example.signin.handlers;

import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.RemoteInput;
import androidx.core.app.TaskStackBuilder;

import com.example.signin.GlobalNotificationBuilder;
import com.example.signin.MockDatabase;
import com.example.signin.R;
import com.example.signin.notifications;

public class BigPictureSocialIntentService extends IntentService {

    private static final String TAG = "BigPictureService";


    public static final String ACTION_COMMENT =
            "com.example.android.signin.handlers.action.COMMENT";

    public static final String EXTRA_COMMENT =
            "com.example.android.signin.handlers.extra.COMMENT";

    public BigPictureSocialIntentService() {
        super("BigPictureSocialIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "onHandleIntent(): " + intent);

        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_COMMENT.equals(action)) {
                handleActionComment(getMessage(intent));
            }
        }
    }

    /**
     * Handles action for adding a comment from the notification.
     */
    private void handleActionComment(CharSequence comment) {
        Log.d(TAG, "handleActionComment(): " + comment);

        if (comment != null) {

            // TODO: Asynchronously save your message to Database and servers.

            /*
             * You have two options for updating your notification (this class uses approach #2):
             *
             *  1. Use a new NotificationCompatBuilder to create the Notification. This approach
             *  requires you to get *ALL* the information that existed in the previous
             *  Notification (and updates) and pass it to the builder. This is the approach used in
             *  the MainActivity.
             *
             *  2. Use the original NotificationCompatBuilder to create the Notification. This
             *  approach requires you to store a reference to the original builder. The benefit is
             *  you only need the new/updated information. In our case, the comment from the user
             *  regarding the post (which we already have here).
             *
             *  IMPORTANT NOTE: You shouldn't save/modify the resulting Notification object using
             *  its member variables and/or legacy APIs. If you want to retain anything from update
             *  to update, retain the Builder as option 2 outlines.
             */

            // Retrieves NotificationCompat.Builder used to create initial Notification
            NotificationCompat.Builder notificationCompatBuilder =
                    GlobalNotificationBuilder.getNotificationCompatBuilderInstance();

            // Recreate builder from persistent state if app process is killed
            if (notificationCompatBuilder == null) {
                // Note: New builder set globally in the method
                notificationCompatBuilder = recreateBuilderWithBigPictureStyle();
            }

            // Updates active Notification
            Notification updatedNotification = notificationCompatBuilder
                    // Adds a line and comment below content in Notification
                    .setRemoteInputHistory(new CharSequence[]{comment})
                    .build();

            // Pushes out the updated Notification
            NotificationManagerCompat notificationManagerCompat =
                    NotificationManagerCompat.from(getApplicationContext());
            notificationManagerCompat.notify(notifications.NOTIFICATION_ID, updatedNotification);
        }
    }

    /*
     * Extracts CharSequence created from the RemoteInput associated with the Notification.
     */
    private CharSequence getMessage(Intent intent) {
        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
        if (remoteInput != null) {
            return remoteInput.getCharSequence(EXTRA_COMMENT);
        }
        return null;
    }

    /*
     * This recreates the notification from the persistent state in case the app process was killed.
     * It is basically the same code for creating the Notification from MainActivity.
     */
    private NotificationCompat.Builder recreateBuilderWithBigPictureStyle() {

        // Main steps for building a BIG_PICTURE_STYLE notification (for more detailed comments on
        // building this notification, check MainActivity.java):
        //      0. Get your data
        //      1. Build the BIG_PICTURE_STYLE
        //      2. Set up main Intent for notification
        //      3. Set up RemoteInput, so users can input (keyboard and voice) from notification
        //      4. Build and issue the notification

        // 0. Get your data (everything unique per Notification)
        MockDatabase.BigPictureStyleSocialAppData bigPictureStyleSocialAppData =
                MockDatabase.getBigPictureStyleData();

        // 1. Build the BIG_PICTURE_STYLE
        NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle()
                .bigPicture(
                        BitmapFactory.decodeResource(
                                getResources(),
                                bigPictureStyleSocialAppData.getBigImage()))
                .setBigContentTitle(bigPictureStyleSocialAppData.getBigContentTitle())
                .setSummaryText(bigPictureStyleSocialAppData.getSummaryText());

        // 2. Set up main Intent for notification
        Intent mainIntent = new Intent(this, BigPictureSocialMainActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(BigPictureSocialMainActivity.class);
        stackBuilder.addNextIntent(mainIntent);

        PendingIntent mainPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        mainIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        // 3. Set up RemoteInput, so users can input (keyboard and voice) from notification
        String replyLabel = "Reply";
        RemoteInput remoteInput =
                new RemoteInput.Builder(BigPictureSocialIntentService.EXTRA_COMMENT)
                        .setLabel(replyLabel)
                        .setChoices(bigPictureStyleSocialAppData.getPossiblePostResponses())
                        .build();

        PendingIntent replyActionPendingIntent;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Intent intent = new Intent(this, BigPictureSocialIntentService.class);
            intent.setAction(BigPictureSocialIntentService.ACTION_COMMENT);
            replyActionPendingIntent = PendingIntent.getService(this, 0, intent, 0);

        } else {
            replyActionPendingIntent = mainPendingIntent;
        }

        NotificationCompat.Action replyAction =
                new NotificationCompat.Action.Builder(
                        R.drawable.omron,
                        replyLabel,
                        replyActionPendingIntent)
                        .addRemoteInput(remoteInput)
                        .build();

        // 4. Build and issue the notification
        NotificationCompat.Builder notificationCompatBuilder =
                new NotificationCompat.Builder(getApplicationContext());

        GlobalNotificationBuilder.setNotificationCompatBuilderInstance(notificationCompatBuilder);

        notificationCompatBuilder
                .setStyle(bigPictureStyle)
                .setContentTitle(bigPictureStyleSocialAppData.getContentTitle())
                .setContentText(bigPictureStyleSocialAppData.getContentText())
                .setSmallIcon(R.drawable.omron)
                .setLargeIcon(BitmapFactory.decodeResource(
                        getResources(),
                        R.drawable.omron))
                .setContentIntent(mainPendingIntent)
//                .setColor(getResources().getColor(R.color.colorPrimary))
                .setSubText(Integer.toString(1))
                .addAction(replyAction)
                .setCategory(Notification.CATEGORY_SOCIAL)
                .setPriority(Notification.PRIORITY_HIGH);
//                .setVisibility(Notification.VISIBILITY_PRIVATE);

        // If the phone is in "Do not disturb mode, the user will still be notified if
        // the sender(s) is starred as a favorite.
        for (String name : bigPictureStyleSocialAppData.getParticipants()) {
            notificationCompatBuilder.addPerson(name);
        }

        return notificationCompatBuilder;
    }
}

