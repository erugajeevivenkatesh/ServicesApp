package com.lnt.chthp00109.servicesapp;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

public class MyAlarm extends BroadcastReceiver {
    private static final String TAG=MyAlarm.class.getSimpleName();
    SaveLogFiles logFiles=new SaveLogFiles();
    NotificationManagerCompat notificationManagerCompat;
    //  private static final String CHANNEL_ID ="Hi" ;
        Context context;
    @Override
    public void onReceive(Context context, Intent intent) {
       // MediaPlayer mediaPlayer=MediaPlayer.create(context,Settings.System.DEFAULT_RINGTONE_URI);
        //mediaPlayer.start();
        notificationManagerCompat=NotificationManagerCompat.from(context);
        Log.d("ALARM","Alaram is started");
        logFiles.Senddata((TAG+"-->Alarm manager Start with Notification!"));
        this.context=context;
        Notifications();
    }
    public void Notifications()
    {
      //  logFiles.Senddata(TAG+"Notification window is pop up!");

        Notification notification=new NotificationCompat.Builder(context,"Nice")
                .setSmallIcon(R.mipmap.service)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setContentTitle("Reminder")
                .setContentText("Sceduled task is---> keep smile :)")
                .build();
        notificationManagerCompat.notify(1,notification);
    }
}
