package com.example.signin;

import androidx.core.app.NotificationCompat;

public class GlobalNotificationBuilder {

    private static NotificationCompat.Builder sGlobalNotificationCompatBuilder = null;

    /*
     * Empty constructor - We don't initialize builder because we rely on a null state to let us
     * know the Application's process was killed.
     */
    private GlobalNotificationBuilder() { }

    public static void setNotificationCompatBuilderInstance (NotificationCompat.Builder builder) {
        sGlobalNotificationCompatBuilder = builder;
    }

    public static NotificationCompat.Builder getNotificationCompatBuilderInstance() {
        return sGlobalNotificationCompatBuilder;
    }
}
