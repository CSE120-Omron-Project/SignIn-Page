package com.example.signin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.RemoteInput;
import androidx.core.app.TaskStackBuilder;
import androidx.core.content.ContextCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.signin.handlers.BigPictureSocialIntentService;
import com.example.signin.handlers.BigPictureSocialMainActivity;
import com.example.signin.handlers.BigTextIntentService;
import com.example.signin.handlers.BigTextNotifications;
import com.google.android.material.snackbar.Snackbar;

import java.text.ParseException;

public class notifications extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final int NOTIFICATION_ID = 888;
    public static final String TAG = "notifications";
    // Used for Notification Style array and switch statement for Spinner selection.
    private static final String Select_Type = "Select Notification Type";
    private static final String Urgent = "Urgent";
    private static final String Low_Risk = "Low_Risk";
    private static final String Medium = "Medium";

    // Collection of notification types to back ArrayAdapter for Spinner.
    private static final String[] NOTIFICATION_STYLES = {
            Select_Type, Urgent, Low_Risk, Medium
    };

    private static final String[] NOTIFICATION_STYLES_DESCRIPTION = {
            "Using the dropdown menu, select the severity.",
            "Displays robots that need urgent attention.",
            "Displays robots that may need care",
            "Displays robots that need medium attention."
    };

    private NotificationManagerCompat mNotificationManagerCompat;

    private int mSelectedNotification = 0;

    // RelativeLayout required for SnackBars to alert users when Notifications are disabled for app.
    private RelativeLayout mMainRelativeLayout;
    private Spinner mSpinner;
    private TextView mNotificationDetailsTextView;

    private Button robotBtn;
//    private Button testNot;

    private dbHelp notificationdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        notificationdb = dbHelp.getInstance(this);
        createNotificaitonChennel();

        mMainRelativeLayout = (RelativeLayout) findViewById(R.id.mainRelativeLayout);
        mNotificationDetailsTextView = (TextView) findViewById(R.id.notificationDetails);
        mSpinner = (Spinner) findViewById(R.id.spinner);

        mNotificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());

        // Create an ArrayAdapter using the string array and a default spinner layout.
        ArrayAdapter<CharSequence> adapter =
                new ArrayAdapter(
                        this,
                        android.R.layout.simple_spinner_item,
                        NOTIFICATION_STYLES);
        // Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner.
        mSpinner.setAdapter(adapter);
        mSpinner.setOnItemSelectedListener(this);

        robotBtn = (Button)findViewById(R.id.robots);
        onRobotBtn();

//        testNot = (Button)findViewById(R.id.testNot);
//        onTestBtn();

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.d(TAG, "onItemSelected(): position: " + position + " id: " + id);

        mSelectedNotification = position;

        mNotificationDetailsTextView.setText(
                NOTIFICATION_STYLES_DESCRIPTION[mSelectedNotification]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Required
    }

    public void onRobotBtn(){
        robotBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(notifications.this, database.class);
                        startActivity(intent);

                    }
                }
        );
    }

//    public void onTestBtn(){
//        testNot.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        Cursor res = null; //calls db object, get all parts is where the query is written
//                        res = notificationdb.getUrgent90();
//
//                        if(res.getCount() == 0){
//                            //show message
//                            showMessage("ERRORS","QUERY DID NOT WORK");
//                        }
//
//                        StringBuffer buffer = new StringBuffer();
//                        while(res.moveToNext()){
//                            buffer.append("PART: "+ res.getString( 3) + "\n");
//
//                        }
//
//                        //show message
//                        showMessage("DATA",buffer.toString());
//
//                    }
//                }
//        );
//    }

    //this is the pop up that shows up with the data
    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


    public void onClick(View view) {

        Log.d(TAG, "onClick()");

        boolean areNotificationsEnabled = mNotificationManagerCompat.areNotificationsEnabled();

//        if (!areNotificationsEnabled) {
//            // Because the user took an action to create a notification, we create a prompt to let
//            // the user re-enable notifications for this application again.
//            Snackbar snackbar = Snackbar
//                    .make(
//                            mMainRelativeLayout,
//                            "You need to enable notifications for this app",
//                            Snackbar.LENGTH_LONG)
//                    .setAction("ENABLE", new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            // Links to this app's notification settings
//                            openNotificationSettingsForApp();
//                        }
//                    });
//            snackbar.show();
//            return;
//        }

        String notificationStyle = NOTIFICATION_STYLES[mSelectedNotification];

        switch (notificationStyle) {
            case Select_Type:
                //generateBigPictureStyleNotification();
                break;

            case Urgent:
                Cursor res = null; //calls db object, get all parts is where the query is written
                res = notificationdb.getUrgent90();

                if(res.getCount() == 0){
                    //show message
                    showMessage("ERRORS","QUERY DID NOT WORK");
                }

//                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
//                    buffer.append("PART: "+ res.getString( 3) + "\n");
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "omron")
                            .setContentTitle("Urgent Robot deadlines!")
                            .setContentText("Serial Number: " + res.getString(0) + " Part: " + res.getString(3))
                            .setSmallIcon(R.drawable.omron)
                            .setLargeIcon(BitmapFactory.decodeResource(
                                    getResources(),
                                    R.drawable.omron));
//                        .setStyle(new Notification.BigPictureStyle().bigPicture())

                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

                    notificationManager.notify(100, builder.build());
                }
//                NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "omron")
//                        .setContentTitle("Urgent Robot deadlines!")
//                        .setContentText(buffer.toString())
//                        .setSmallIcon(R.drawable.omron)
//                        .setLargeIcon(BitmapFactory.decodeResource(
//                                getResources(),
//                                R.drawable.omron));
////                        .setStyle(new Notification.BigPictureStyle().bigPicture())
//
//                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
//
//                notificationManager.notify(100, builder.build());

                break;

            case Low_Risk:
                Cursor resLow = null; //calls db object, get all parts is where the query is written
                resLow = notificationdb.getLow90();

                if(resLow.getCount() == 0){
                    //show message
                    showMessage("ERRORS","QUERY DID NOT WORK");
                }

//                StringBuffer buffer = new StringBuffer();
                while(resLow.moveToNext()){
//                    buffer.append("PART: "+ res.getString( 3) + "\n");
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "omron")
                            .setContentTitle("Low Urgency Robot deadlines.")
                            .setContentText("Serial Number: " + resLow.getString(0) + " Part: " + resLow.getString(3))
                            .setSmallIcon(R.drawable.omron)
                            .setLargeIcon(BitmapFactory.decodeResource(
                                    getResources(),
                                    R.drawable.omron));
//                        .setStyle(new Notification.BigPictureStyle().bigPicture())

                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

                    notificationManager.notify(100, builder.build());
                };
                break;

            case Medium:
                Cursor resMed = null; //calls db object, get all parts is where the query is written
                resMed = notificationdb.getMedium90();

                if(resMed.getCount() == 0){
                    //show message
                    showMessage("ERRORS","QUERY DID NOT WORK");
                }

//                StringBuffer buffer = new StringBuffer();
                while(resMed.moveToNext()){
//                    buffer.append("PART: "+ res.getString( 3) + "\n");
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "omron")
                            .setContentTitle("Medium Urgency Robot deadlines~")
                            .setContentText("Serial Number: " + resMed.getString(0) + " Part: " + resMed.getString(3))
                            .setSmallIcon(R.drawable.omron)
                            .setLargeIcon(BitmapFactory.decodeResource(
                                    getResources(),
                                    R.drawable.omron));
//                        .setStyle(new Notification.BigPictureStyle().bigPicture())

                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

                    notificationManager.notify(100, builder.build());
                };
                break;

            default:
                // continue below
        }
    }


    private void createNotificaitonChennel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "OmronNotificaiton";
            String description = "A reminder notification for maitenance dates";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("omron", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }



    private void generateBigPictureStyleNotification() {

        Log.d(TAG, "generateBigPictureStyleNotification()");

        // Main steps for building a BIG_PICTURE_STYLE notification:
        //      0. Get your data
        //      1. Create/Retrieve Notification Channel for O and beyond devices (26+)
        //      2. Build the BIG_PICTURE_STYLE
        //      3. Set up main Intent for notification
        //      4. Set up RemoteInput, so users can input (keyboard and voice) from notification
        //      5. Build and issue the notification

        // 0. Get your data (everything unique per Notification).
        MockDatabase.BigPictureStyleSocialAppData bigPictureStyleSocialAppData =
                MockDatabase.getBigPictureStyleData();

        // 1. Create/Retrieve Notification Channel for O and beyond devices (26+).
        String notificationChannelId =
                NotificationUtil.createNotificationChannel(this, bigPictureStyleSocialAppData);

        // 2. Build the BIG_PICTURE_STYLE.
        NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle()
                // Provides the bitmap for the BigPicture notification.
                .bigPicture(
                        BitmapFactory.decodeResource(
                                getResources(),
                                bigPictureStyleSocialAppData.getBigImage()))
                // Overrides ContentTitle in the big form of the template.
                .setBigContentTitle(bigPictureStyleSocialAppData.getBigContentTitle())
                // Summary line after the detail section in the big form of the template.
                .setSummaryText(bigPictureStyleSocialAppData.getSummaryText());

        // 3. Set up main Intent for notification.
        Intent mainIntent = new Intent(this, BigPictureSocialMainActivity.class);

        // When creating your Intent, you need to take into account the back state, i.e., what
        // happens after your Activity launches and the user presses the back button.

        // There are two options:
        //      1. Regular activity - You're starting an Activity that's part of the application's
        //      normal workflow.

        //      2. Special activity - The user only sees this Activity if it's started from a
        //      notification. In a sense, the Activity extends the notification by providing
        //      information that would be hard to display in the notification itself.

        // Even though this sample's MainActivity doesn't link to the Activity this Notification
        // launches directly, i.e., it isn't part of the normal workflow, a social app generally
        // always links to individual posts as part of the app flow, so we will follow option 1.

        // For an example of option 2, check out the BIG_TEXT_STYLE example.

        // For more information, check out our dev article:
        // https://developer.android.com/training/notify-user/navigation.html

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        // Adds the back stack.
        stackBuilder.addParentStack(BigPictureSocialMainActivity.class);
        // Adds the Intent to the top of the stack.
        stackBuilder.addNextIntent(mainIntent);
        // Gets a PendingIntent containing the entire back stack.
        PendingIntent mainPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        mainIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        // 4. Set up RemoteInput, so users can input (keyboard and voice) from notification.

        // Note: For API <24 (M and below) we need to use an Activity, so the lock-screen presents
        // the auth challenge. For API 24+ (N and above), we use a Service (could be a
        // BroadcastReceiver), so the user can input from Notification or lock-screen (they have
        // choice to allow) without leaving the notification.

        // Create the RemoteInput.
        String replyLabel = "Reply";
        RemoteInput remoteInput =
                new RemoteInput.Builder(BigPictureSocialIntentService.EXTRA_COMMENT)
                        .setLabel(replyLabel)
                        // List of quick response choices for any wearables paired with the phone
                        .setChoices(bigPictureStyleSocialAppData.getPossiblePostResponses())
                        .build();

        // Pending intent =
        //      API <24 (M and below): activity so the lock-screen presents the auth challenge
        //      API 24+ (N and above): this should be a Service or BroadcastReceiver
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

        // 5. Build and issue the notification.

        // Because we want this to be a new notification (not updating a previous notification), we
        // create a new Builder. Later, we use the same global builder to get back the notification
        // we built here for a comment on the post.

        NotificationCompat.Builder notificationCompatBuilder =
                new NotificationCompat.Builder(getApplicationContext(), notificationChannelId);

        GlobalNotificationBuilder.setNotificationCompatBuilderInstance(notificationCompatBuilder);

        notificationCompatBuilder
                // BIG_PICTURE_STYLE sets title and content for API 16 (4.1 and after).
                .setStyle(bigPictureStyle)
                // Title for API <16 (4.0 and below) devices.
                .setContentTitle(bigPictureStyleSocialAppData.getContentTitle())
                // Content for API <24 (7.0 and below) devices.
                .setContentText(bigPictureStyleSocialAppData.getContentText())
                .setSmallIcon(R.drawable.omron)
                .setLargeIcon(BitmapFactory.decodeResource(
                        getResources(),
                        R.drawable.omron))
                .setContentIntent(mainPendingIntent)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                // Set primary color (important for Wear 2.0 Notifications).
//                .setColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary))

                // SIDE NOTE: Auto-bundling is enabled for 4 or more notifications on API 24+ (N+)
                // devices and all Wear devices. If you have more than one notification and
                // you prefer a different summary notification, set a group key and create a
                // summary notification via
                // .setGroupSummary(true)
                // .setGroup(GROUP_KEY_YOUR_NAME_HERE)

                .setSubText(Integer.toString(1))
                .addAction(replyAction)
                .setCategory(Notification.CATEGORY_REMINDER)

                // Sets priority for 25 and below. For 26 and above, 'priority' is deprecated for
                // 'importance' which is set in the NotificationChannel. The integers representing
                // 'priority' are different from 'importance', so make sure you don't mix them.
                .setPriority(bigPictureStyleSocialAppData.getPriority())

                // Sets lock-screen visibility for 25 and below. For 26 and above, lock screen
                // visibility is set in the NotificationChannel.
                .setVisibility(bigPictureStyleSocialAppData.getChannelLockscreenVisibility());

        // If the phone is in "Do not disturb mode, the user will still be notified if
        // the sender(s) is starred as a favorite.
        for (String name : bigPictureStyleSocialAppData.getParticipants()) {
            notificationCompatBuilder.addPerson(name);
        }

        Notification notification = notificationCompatBuilder.build();

        mNotificationManagerCompat.notify(NOTIFICATION_ID, notification);
    }



//
//    private void generateBigTextStyleNotification() {
//
//        Log.d(TAG, "generateBigTextStyleNotification()");
//
//        // Main steps for building a BIG_TEXT_STYLE notification:
//        //      0. Get your data
//        //      1. Create/Retrieve Notification Channel for O and beyond devices (26+)
//        //      2. Build the BIG_TEXT_STYLE
//        //      3. Set up main Intent for notification
//        //      4. Create additional Actions for the Notification
//        //      5. Build and issue the notification
//
//        // 0. Get your data (everything unique per Notification).
//        MockDatabase.BigTextStyleReminderAppData bigTextStyleReminderAppData =
//                MockDatabase.getBigTextStyleData();
//
//        // 1. Create/Retrieve Notification Channel for O and beyond devices (26+).
//        String notificationChannelId =
//                NotificationUtil.createNotificationChannel(this, bigTextStyleReminderAppData);
//
//
//        // 2. Build the BIG_TEXT_STYLE.
//        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle()
//                // Overrides ContentText in the big form of the template.
//                .bigText(bigTextStyleReminderAppData.getBigText())
//                // Overrides ContentTitle in the big form of the template.
//                .setBigContentTitle(bigTextStyleReminderAppData.getBigContentTitle())
//                // Summary line after the detail section in the big form of the template.
//                // Note: To improve readability, don't overload the user with info. If Summary Text
//                // doesn't add critical information, you should skip it.
//                .setSummaryText(bigTextStyleReminderAppData.getSummaryText());
//
//
//        // 3. Set up main Intent for notification.
//        Intent notifyIntent = new Intent(this, BigTextNotifications.class);
//
//        // When creating your Intent, you need to take into account the back state, i.e., what
//        // happens after your Activity launches and the user presses the back button.
//
//        // There are two options:
//        //      1. Regular activity - You're starting an Activity that's part of the application's
//        //      normal workflow.
//
//        //      2. Special activity - The user only sees this Activity if it's started from a
//        //      notification. In a sense, the Activity extends the notification by providing
//        //      information that would be hard to display in the notification itself.
//
//        // For the BIG_TEXT_STYLE notification, we will consider the activity launched by the main
//        // Intent as a special activity, so we will follow option 2.
//
//        // For an example of option 1, check either the MESSAGING_STYLE or BIG_PICTURE_STYLE
//        // examples.
//
//        // For more information, check out our dev article:
//        // https://developer.android.com/training/notify-user/navigation.html
//
//        // Sets the Activity to start in a new, empty task
//        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//
//        PendingIntent notifyPendingIntent =
//                PendingIntent.getActivity(
//                        this,
//                        0,
//                        notifyIntent,
//                        PendingIntent.FLAG_UPDATE_CURRENT
//                );
//
//
//        // 4. Create additional Actions (Intents) for the Notification.
//
//        // In our case, we create two additional actions: a Snooze action and a Dismiss action.
//        // Snooze Action.
//        Intent snoozeIntent = new Intent(this, BigTextIntentService.class);
//        snoozeIntent.setAction(BigTextIntentService.ACTION_SNOOZE);
//
//        PendingIntent snoozePendingIntent = PendingIntent.getService(this, 0, snoozeIntent, 0);
//        NotificationCompat.Action snoozeAction =
//                new NotificationCompat.Action.Builder(
//                        R.drawable.omron,
//                        "Snooze",
//                        snoozePendingIntent)
//                        .build();
//
//
//        // Dismiss Action.
//        Intent dismissIntent = new Intent(this, BigTextIntentService.class);
//        dismissIntent.setAction(BigTextIntentService.ACTION_DISMISS);
//
//        PendingIntent dismissPendingIntent = PendingIntent.getService(this, 0, dismissIntent, 0);
//        NotificationCompat.Action dismissAction =
//                new NotificationCompat.Action.Builder(
//                        R.drawable.omron,
//                        "Dismiss",
//                        dismissPendingIntent)
//                        .build();
//
//
//
//
//        // 5. Build and issue the notification.
//
//        // Because we want this to be a new notification (not updating a previous notification), we
//        // create a new Builder. Later, we use the same global builder to get back the notification
//        // we built here for the snooze action, that is, canceling the notification and relaunching
//        // it several seconds later.
//
//        // Notification Channel Id is ignored for Android pre O (26).
//        NotificationCompat.Builder notificationCompatBuilder =
//                new NotificationCompat.Builder(
//                        getApplicationContext(), notificationChannelId);
//
//        GlobalNotificationBuilder.setNotificationCompatBuilderInstance(notificationCompatBuilder);
//
//        Notification notification = notificationCompatBuilder
//                // BIG_TEXT_STYLE sets title and content for API 16 (4.1 and after).
//                .setStyle(bigTextStyle)
//                // Title for API <16 (4.0 and below) devices.
//                .setContentTitle(bigTextStyleReminderAppData.getContentTitle())
//                // Content for API <24 (7.0 and below) devices.
//                .setContentText(bigTextStyleReminderAppData.getContentText())
//                .setSmallIcon(R.drawable.omron)
//                .setLargeIcon(BitmapFactory.decodeResource(
//                        getResources(),
//                        R.drawable.omron))
//                .setContentIntent(notifyPendingIntent)
//                .setDefaults(NotificationCompat.DEFAULT_ALL)
//                // Set primary color (important for Wear 2.0 Notifications).
////                .setColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary))
//
//                // SIDE NOTE: Auto-bundling is enabled for 4 or more notifications on API 24+ (N+)
//                // devices and all Wear devices. If you have more than one notification and
//                // you prefer a different summary notification, set a group key and create a
//                // summary notification via
//                // .setGroupSummary(true)
//                // .setGroup(GROUP_KEY_YOUR_NAME_HERE)
//
//                .setCategory(Notification.CATEGORY_REMINDER)
//
//                // Sets priority for 25 and below. For 26 and above, 'priority' is deprecated for
//                // 'importance' which is set in the NotificationChannel. The integers representing
//                // 'priority' are different from 'importance', so make sure you don't mix them.
//                .setPriority(bigTextStyleReminderAppData.getPriority())
//
//                // Sets lock-screen visibility for 25 and below. For 26 and above, lock screen
//                // visibility is set in the NotificationChannel.
//                .setVisibility(bigTextStyleReminderAppData.getChannelLockscreenVisibility())
//
//                // Adds additional actions specified above.
//                .addAction(snoozeAction)
//                .addAction(dismissAction)
//
//                .build();
//
//
//    }



}