package com.razytech.razynet.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.support.multidex.MultiDex;

import com.razytech.razynet.Utils.NetworkStatusw;
import com.razytech.razynet.data.di.DaggerAppComponent;

import java.util.Locale;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by A.Noby on 4/7/2019.
 */
public class RazyNetApp extends Application implements HasActivityInjector

{

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

//    @Inject
//    CalligraphyConfig mCalligraphyConfig;

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }
    private static Context appContext;
    private static RazyNetApp mInstance;


    @Override
    public void onCreate() {
        super.onCreate();

        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);

        appContext = this;
        setLocale();
        mInstance = this;
    }

    public static synchronized RazyNetApp getInstance() {
        return mInstance;
    }

    public void setConnectivityListener(NetworkStatusw.ConnectivityReceiverListener listener) {
        NetworkStatusw.connectivityReceiverListener = listener;
    }
    public static void setLocale() {
        //. Locale locale = new Locale("ar");
         Locale locale = Locale.US;
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getAppContext().getResources().updateConfiguration(config,
                getAppContext().getResources().getDisplayMetrics());
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        MultiDex.install(this);
    }
    public static Context getAppContext() {
        return appContext;
    }
}

