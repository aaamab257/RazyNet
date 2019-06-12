package com.razytech.razynet.Utils;

import android.Manifest;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.razytech.razynet.R;
import com.razytech.razynet.gui.splash.SplashActivity;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by A.Noby on 4/7/2019.
 */
public class StaticMethods {

    public static final int REQUEST_LOCATION = 199;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99,REQUEST_CAMERA = 90;

    public static boolean isConnectingToInternet(Context _context) {
        ConnectivityManager connectivity = (ConnectivityManager) _context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
        }
        return false;
    }

    public static void NOConnecting(CoordinatorLayout _context, Context context) {
        Snackbar snackbar = Snackbar
                .make(_context, context.getString(R.string.noconnection), Snackbar.LENGTH_LONG);
        int color;
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        color = Color.RED;
        textView.setTextColor(color);
        textView.setTextSize(18);
        textView.setGravity(Gravity.CENTER);
        snackbar.show();
    }

    public static void ShowSnake(CoordinatorLayout _context, Context context, String message) {
        Snackbar snackbar = Snackbar
                .make(_context,message, Snackbar.LENGTH_LONG);
        int color;
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        color = Color.WHITE;
        textView.setTextColor(color);
        textView.setTextSize(18);
        textView.setGravity(Gravity.CENTER);
        snackbar.show();
    }
    public int getDeviceLangIndx(String lang) {
        if (lang.equals("العربية")) {
            return 2;
        }
        if (lang.equals("English")) {
            return 1;
        }
        return 0;
    }

    public static int getSpinLangIndx(String lang) {
        if (lang.equals("ar")) {
            return 2;
        }
        if (lang.equals("en")) {
            return 1;
        }
        return 0;
    }

    //   public static String getGenderstatus(int indx, Context context) {
//        switch (indx) {
//            case 1:
//                return context.getString(R.string.male);
//            case 2:
//                return context.getString(R.string.female);
//            default:
//                return context.getString(R.string.male);
//        }
    // }

    public static void setLocale(String lang, Context con) {
        Log.e("languagesssc", lang);
        Locale myLocale = new Locale(lang);
        Resources res = con.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent mStartActivity = new Intent(con, SplashActivity.class);
        int mPendingIntentId = 123456;
        PendingIntent mPendingIntent = PendingIntent.getActivity(con, mPendingIntentId,    mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager mgr = (AlarmManager)con.getSystemService(Context.ALARM_SERVICE);
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
        System.exit(0);
    }
    // @SuppressLint("WrongConstant")
//    public static boolean getGPSStatus(Context context) {
//        //   return ((LocationManager) context.getSystemService(LOCATION)).isProviderEnabled("gps");
//        LocationManager locationManager = (LocationManager)
//                context.getSystemService(Context.LOCATION_SERVICE);
//        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
//    }
//    public static boolean getGPSStatus(Context context) {
//        //   return ((LocationManager) context.getSystemService(LOCATION)).isProviderEnabled("gps");
//        LocationManager locationManager = (LocationManager)
//                context.getSystemService(Context.LOCATION_SERVICE);
//        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
//    }

//    @SuppressLint("WrongConstant")
//    public static boolean getGPSStatus(Context context) {
//        return ((LocationManager) context.getSystemService(Param.LOCATION)).isProviderEnabled("gps");
//    }


    public static boolean getGPSStatus(Context context) {
        int locationMode = 0;
        String locationProviders;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            try {
                locationMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);

            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
                return false;
            }

            return locationMode != Settings.Secure.LOCATION_MODE_OFF;

        }else{
            locationProviders = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            return !TextUtils.isEmpty(locationProviders);
        }
    }




    public  static boolean  checkLocationPermission
            (Context context, Activity activity) {
        if (ContextCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
            } else {
                ActivityCompat.requestPermissions(activity,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    public static boolean checkCameraPermission(Context context, Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context,
                    Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
            else if (ContextCompat.checkSelfPermission(context,
                    Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
            else  if (ContextCompat.checkSelfPermission(context,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
            else
                return  true ;
        }else{
            return  true  ;
        }
    }
    public static Bitmap loadBitmapFromView(View v) {

        if (v.getMeasuredHeight() <= 0) {
            v.measure(90, WindowManager.LayoutParams.WRAP_CONTENT);
            BitmapFactory.Options thumbOpts = new BitmapFactory.Options();
            thumbOpts.inSampleSize = 4;
            Bitmap b = Bitmap.createBitmap(v.getMeasuredWidth(), v.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
            Canvas c = new Canvas(b);
            v.layout(0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());
            v.draw(c);
            return b;
        }
        return null;
    }



    public  static   void LoadImage(Context context , CircleImageView imageView, String Url, ProgressBar progressBar) {

        try{
            if(progressBar != null)
                progressBar.setVisibility(View.VISIBLE);
            Glide.with(context)
                    .load(Url)
                    .asBitmap()
                    .dontTransform()
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            if(progressBar != null)
                                progressBar.setVisibility(View.GONE);

                            imageView.setVisibility(View.VISIBLE);
                            final float scale = context.getResources().getDisplayMetrics().density;
                            int pixels = (int) (50 * scale + 0.5f);
                            Bitmap bitmap = Bitmap.createScaledBitmap(resource, pixels, pixels, true);
                            imageView.setImageBitmap(bitmap);
                        }
                        @Override
                        public void onLoadFailed(Exception e, Drawable errorDrawable) {
                            if(progressBar != null)
                                progressBar.setVisibility(View.GONE);
                            imageView.setVisibility(View.VISIBLE);
                            imageView.setImageResource(R.mipmap.avatar);
                        }
                    });
        }catch (Exception e){}
    }


    public  static   void LoadImage(Context context , ImageView imageView, String Url, ProgressBar progressBar) {
        try {
            if (progressBar != null)
                progressBar.setVisibility(View.VISIBLE);
            if (Url != null) {
                Glide.with(context)
                        .load(Url)
                        .asBitmap()
                        .dontTransform()
                        .into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                if (progressBar != null)
                                    progressBar.setVisibility(View.GONE);

                                imageView.setVisibility(View.VISIBLE);
                                final float scale = context.getResources().getDisplayMetrics().density;
                                int pixels = (int) (50 * scale + 0.5f);
                                Bitmap bitmap = Bitmap.createScaledBitmap(resource, pixels, pixels, true);
                                imageView.setImageBitmap(bitmap);
                            }

                            @Override
                            public void onLoadFailed(Exception e, Drawable errorDrawable) {
                                if (progressBar != null)
                                    progressBar.setVisibility(View.GONE);
                                imageView.setVisibility(View.VISIBLE);
                                imageView.setImageResource(R.mipmap.avatar);
                            }
                        });
            }
        }catch (Exception e){}
    }

    public static String getFloatFromString(String str) {
        Pattern pattern = Pattern.compile("\\d+(?:\\.\\d+)?"); // Match int or float
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            return matcher.group();
        } else {
            return null;
        }
    }

    public static void ClearChash() {
//        AppConstant.findResponses =  null  ;
//        AppConstant.offersResponses =  null  ;
//        AppConstant.categoryResponses =  null  ;
//        AppConstant.myShopsResponses =  null  ;
    }
}
