package com.example.signin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

//import com.example.signin.handlers.BigTextIntentService;
import com.example.signin.handlers.BigTextNotifications;
import com.google.android.material.snackbar.Snackbar;

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


    //button for data
    private Button robotBtn;






    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        mMainRelativeLayout = (RelativeLayout) findViewById(R.id.mainRelativeLayout);
        mNotificationDetailsTextView = (TextView) findViewById(R.id.notificationDetails);
        mSpinner = (Spinner) findViewById(R.id.spinner);



        //handles robot button on the notification page
        robotBtn = (Button)findViewById(R.id.robots);


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


        robotBtnHandler();



    }

    //handles when u click the robot button
    public void robotBtnHandler(){
        robotBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(notifications.this,database.class);
                startActivity(intent);

            }
        });

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


    public void onClickRobot(View v){



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
                generateBigTextStyleNotification();
                break;

            case Urgent:
                generateBigTextStyleNotification();
                break;

            case Low_Risk:
                generateBigTextStyleNotification();
                break;

            case Medium:
                generateBigTextStyleNotification();
                break;

            default:
                // continue below
        }
    }

    private void generateBigTextStyleNotification() {

        Log.d(TAG, "generateBigTextStyleNotification()");

        // Main steps for building a BIG_TEXT_STYLE notification:
        //      0. Get your data
        //      1. Create/Retrieve Notification Channel for O and beyond devices (26+)
        //      2. Build the BIG_TEXT_STYLE
        //      3. Set up main Intent for notification
        //      4. Create additional Actions for the Notification
        //      5. Build and issue the notification

        // 0. Get your data (everything unique per Notification).
        MockDatabase.BigTextStyleReminderAppData bigTextStyleReminderAppData =
                MockDatabase.getBigTextStyleData();

        // 1. Create/Retrieve Notification Channel for O and beyond devices (26+).
        String notificationChannelId =
                NotificationUtil.createNotificationChannel(this, bigTextStyleReminderAppData);


        // 2. Build the BIG_TEXT_STYLE.
        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle()
                // Overrides ContentText in the big form of the template.
                .bigText(bigTextStyleReminderAppData.getBigText())
                // Overrides ContentTitle in the big form of the template.
                .setBigContentTitle(bigTextStyleReminderAppData.getBigContentTitle())
                // Summary line after the detail section in the big form of the template.
                // Note: To improve readability, don't overload the user with info. If Summary Text
                // doesn't add critical information, you should skip it.
                .setSummaryText(bigTextStyleReminderAppData.getSummaryText());


        // 3. Set up main Intent for notification.
        Intent notifyIntent = new Intent(this, BigTextNotifications.class);

        // When creating your Intent, you need to take into account the back state, i.e., what
        // happens after your Activity launches and the user presses the back button.

        // There are two options:
        //      1. Regular activity - You're starting an Activity that's part of the application's
        //      normal workflow.

        //      2. Special activity - The user only sees this Activity if it's started from a
        //      notification. In a sense, the Activity extends the notification by providing
        //      information that would be hard to display in the notification itself.

        // For the BIG_TEXT_STYLE notification, we will consider the activity launched by the main
        // Intent as a special activity, so we will follow option 2.

        // For an example of option 1, check either the MESSAGING_STYLE or BIG_PICTURE_STYLE
        // examples.

        // For more information, check out our dev article:
        // https://developer.android.com/training/notify-user/navigation.html

        // Sets the Activity to start in a new, empty task
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent notifyPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        notifyIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );


        // 4. Create additional Actions (Intents) for the Notification.

        // In our case, we create two additional actions: a Snooze action and a Dismiss action.
        // Snooze Action.
//        Intent snoozeIntent = new Intent(this, BigTextIntentService.class);
//        snoozeIntent.setAction(BigTextIntentService.ACTION_SNOOZE);
//
//        PendingIntent snoozePendingIntent = PendingIntent.getService(this, 0, snoozeIntent, 0);
//        NotificationCompat.Action snoozeAction =
//                new NotificationCompat.Action.Builder(
//                        R.drawable.ic_alarm_white_48dp,
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
//                        R.drawable.ic_cancel_white_48dp,
//                        "Dismiss",
//                        dismissPendingIntent)
//                        .build();




        // 5. Build and issue the notification.

        // Because we want this to be a new notification (not updating a previous notification), we
        // create a new Builder. Later, we use the same global builder to get back the notification
        // we built here for the snooze action, that is, canceling the notification and relaunching
        // it several seconds later.

        // Notification Channel Id is ignored for Android pre O (26).
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
//                .setSmallIcon(R.drawable.ic_launcher)
//                .setLargeIcon(BitmapFactory.decodeResource(
//                        getResources(),
//                        R.drawable.ic_alarm_white_48dp))
//                .setContentIntent(notifyPendingIntent)
//                .setDefaults(NotificationCompat.DEFAULT_ALL)
//                // Set primary color (important for Wear 2.0 Notifications).
//                .setColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary))
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


    }
}