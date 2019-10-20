package com.razytech.razynet.gui.push;

import android.app.NotificationChannel;
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
        String points = "0", childCount = "0";
        if (type.equals(""+AppConstant.UPDATEPOINT_notification) ) {
            points = remoteMessage.getData().get("points").toString();
        } else if (type.equals( ""+AppConstant.UPDATECHILE_notification)) {
            Log.e(TAG, "points " + remoteMessage.getData().get("points").toString());
            points = remoteMessage.getData().get("points").toString();
            Log.e(TAG, "childCount " + remoteMessage.getData().get("childCount").toString());
            childCount = remoteMessage.getData().get("childCount").toString();

        }


        showNotification(remoteMessage.getData().get("title").toString(), remoteMessage.getData().get("body").toString(), remoteMessage.getData().get("type").toString(), points,childCount);
    }catch (Exception e){}

    }


    public void showNotification( String title, String body,String Type ,String points,String ChildCount) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        int notificationId = 1;
        String channelId = "channel-01";
        String channelName = "Channel Name";
        int importance = NotificationManager.IMPORTANCE_HIGH;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(
                    channelId, channelName, importance);
            notificationManager.createNotificationChannel(mChannel);
        }
        Uri alarmSound = RingtoneManager
                .getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        long pattern[] = {0, 800, 200, 300, 400};

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.mipmap.ic_launcher)

                .setContentTitle(title)
                .setContentText(body)
                .setSound(alarmSound)
                .setAutoCancel(true)
                .setVibrate(pattern);

        Intent resultIntent = new Intent(this, MainpageActivity.class);
        resultIntent.putExtra(AppConstant.OpenNotification, "notification");
        if (Type.equalsIgnoreCase(AppConstant.UPDATEPOINT_notification+"")){
            Intent intnt = new Intent(AppConstant.ActionString);
            intnt.putExtra(AppConstant.UPDATE_POINTS,points);
            sendBroadcast(intnt);
        }
        else if(Type.equalsIgnoreCase(AppConstant.UPDATECHILE_notification+"")){
            Intent intnt = new Intent(AppConstant.ActionString);
            intnt.putExtra(AppConstant.UPDATE_POINTS,points);
            intnt.putExtra(AppConstant.UPDATE_CHILD,ChildCount);
            sendBroadcast(intnt);
        }
        else if (Type.equalsIgnoreCase(AppConstant.STARWALLET_notification+"")){
            AppConstant.refreshhome =  true ;
            AppConstant.userResponse.setStarWallet(true);
            Intent intnt = new Intent(AppConstant.ActionString);
            intnt.putExtra(AppConstant.StarWallet,"");
            sendBroadcast(intnt);
        }

        else if (Type.equalsIgnoreCase(AppConstant.CANADDWALLET_notification+"")){
            AppConstant.refreshhome =  true ;
            Log.e("here","Maincanadd");
            Intent intnt = new Intent(AppConstant.ActionString);
            intnt.putExtra(AppConstant.CanaddWallet,"");
            sendBroadcast(intnt);
        }
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainpageActivity.class);
        stackBuilder.addNextIntent(resultIntent);

        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
                0,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
        mBuilder.setContentIntent(resultPendingIntent);

        notificationManager.notify(notificationId, mBuilder.build());
    }

    private void sendNotification(String messageTitle, String messageBody ,String Type ,String points,String ChildCount) {
 /// o
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
