package com.leuenroo.notificationexample;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private String CHANNEL_ID = "channel_id";
    private int NOTIFICATION_ID = 1;
    private String CHANNEL_NAME = "Notification name";
    public int NOTIFICIATION_IMPORTANCE = NotificationManager.IMPORTANCE_DEFAULT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }


    //create notification channel for android 8 and above
    public void createNotificationChannel(){

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID,CHANNEL_NAME,NOTIFICIATION_IMPORTANCE);
            notificationChannel.setDescription("This is a notification channel");

            //register it
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
        }


    }

    public void createNotification(){

        Intent intent = new Intent(this, MessagingMainActivity.class);
        intent.putExtra("MSG","You see");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.yellow)
                .setContentTitle("Simple notification")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .setStyle(new NotificationCompat.BigTextStyle().bigText("Study hard for your capstone project"))
                .setContentIntent(pendingIntent);


        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(NOTIFICATION_ID,builder.build());

    }


    public void sendNote(View view) {
        createNotificationChannel();

        createNotification();
    }
}