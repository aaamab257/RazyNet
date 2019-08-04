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

        Log.e(TAG, "From: " + remoteMessage.getFrom());
        Log.e(TAG, "message: " + remoteMessage.getNotification().getBody());
      //  Log.e(TAG,"title"+remoteMessage.getData().get("title").toString());

        // Check if message contains a data payload.
//        String type = remoteMessage.getData().get("type").toString() ;
//        String id   = remoteMessage.getData().get("id").toString();
//        String name = null ;
//         if (type != "1"){
//              name = remoteMessage.getData().get("name").toString();
//         }
//        Log.e(TAG, "Message data payload: " + id+"  "+type+"  "+name);
//        if (remoteMessage.getNotification() != null) {
//            Log.e(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody()+"  "+remoteMessage.getNotification().getTitle());
//        }
//        Log.e(TAG,"From : "+remoteMessage.getFrom());
//       Log.e(TAG,"body : "+remoteMessage.getData().get("Type"));
//      //  Log.e(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody()  +"    "+remoteMessage.getNotification().getTitle());
//
        sendNotification(remoteMessage.getNotification().getBody());
    }

    private void sendNotification( String messageBody) {

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
                this);

        mBuilder.setContentTitle("New Notification");
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
            Intent resultIntent = new Intent(this, SplashActivity.class);
            resultIntent.putExtra(AppConstant.OpenNotification, "notification");

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
