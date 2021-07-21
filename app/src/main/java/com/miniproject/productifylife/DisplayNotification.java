package com.miniproject.productifylife;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class DisplayNotification extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Intent main = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, main, 0);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, "notifyChannelId")
                .setSmallIcon(R.drawable.ic_app_icon)
                .setContentTitle("Add Task")
                .setContentText("Add Tomorrow's To-Do Task")
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setContentIntent(pendingIntent);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(123, notificationBuilder.build());
    }
}
