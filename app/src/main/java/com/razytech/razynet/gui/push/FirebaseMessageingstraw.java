package com.razytech.razynet.gui.push;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.razytech.razynet.R;
import com.razytech.razynet.Utils.AppConstant;
import com.razytech.razynet.gui.mainpage.MainpageActivity;
import com.razytech.razynet.gui.splash.SplashActivity;


public class FirebaseMessageingstraw extends FirebaseMessagingService {

    private static final String TAG="FIREBASE";
    private static int notificationIdentifier = 101;


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

      //  Log.e(TAG, "From: " + remoteMessage.getFrom());
     //   Log.e(TAG, "message: " + remoteMessage.getNotification().getBody());
    try {
        Log.e(TAG, "title " + remoteMessage.getData().get("title").toString());
        Log.e(TAG, "body " + remoteMessage.getData().get("body").toString());
        Log.e(TAG, "type " + remoteMessage.getData().get("type").toString());

        // Check if message contains a data payload.
        String type = remoteMessage.getData().get("type").toString();
        String points = null, childCount = "";
        if (type != "2") {
            points = remoteMessage.getData().get("points").toString();
        } else if (type != "3") {
            points = remoteMessage.getData().get("points").toString();
            childCount = remoteMessage.getData().get("childCount").toString();

        }


        sendNotification(remoteMessage.getData().get("title").toString(), remoteMessage.getData().get("body").toString(), remoteMessage.getData().get("type").toString(), points,childCount);
    }catch (Exception e){}

    }

    private void sendNotification(String messageTitle, String messageBody ,String Type ,String points,String ChildCount) {

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
                this);

        mBuilder.setContentTitle(messageTitle);
        mBuilder.setContentText(messageBody);
        mBuilder.setStyle(new NotificationCompat.BigTextStyle()
                .bigText(messageBody));
        mBuilder.setTicker("");
        mBuilder.setSmallIcon(R.mipmap.ic_launcher);
        mBuilder.setAutoCancel(true);
        long pattern[] = {0, 800, 200, 300, 400};
        mBuilder.setVibrate(pattern);
        Uri alarmSound = RingtoneManager
                .getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        mBuilder.setSound(alarmSound);
try {
          //  Log.e("pushnotification","1  "+type);
            Intent resultIntent = new Intent(this, MainpageActivity.class);
            resultIntent.putExtra(AppConstant.OpenNotification, "notification");
            if (Type.equalsIgnoreCase("2")){
                Intent intnt = new Intent(AppConstant.ActionString);
                intnt.putExtra(AppConstant.UPDATE_POINTS,points);
                sendBroadcast(intnt);
            }
            else if(Type.equalsIgnoreCase("3")){
                Intent intnt = new Intent(AppConstant.ActionString);
                intnt.putExtra(AppConstant.UPDATE_POINTS,points);
                intnt.putExtra(AppConstant.UPDATE_CHILD,ChildCount);
                sendBroadcast(intnt);
                }
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
            stackBuilder.addParentStack(MainpageActivity.class);
            stackBuilder.addNextIntent(resultIntent);
            PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            mBuilder.setContentIntent(resultPendingIntent);
            NotificationManager mNotificationManager = (NotificationManager) this
                    .getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.notify(notificationIdentifier + 1,
                    mBuilder.build());

}catch (Exception e)
{
    e.printStackTrace();
}
    }
}
