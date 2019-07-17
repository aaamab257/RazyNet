package com.razytech.razynet.gui.splash;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.razytech.razynet.R;
import com.razytech.razynet.Utils.IntentUtiles;
import com.razytech.razynet.baseClasses.BaseActivity;
import com.razytech.razynet.databinding.ActivitySplashBinding;
import com.razytech.razynet.gui.mainpage.MainpageActivity;
import com.razytech.razynet.gui.remainingpage.RemainingActivity;
import com.razytech.razynet.gui.verificationcode.VerifyCodeActivity;

import javax.inject.Inject;

public class SplashActivity extends BaseActivity<ActivitySplashBinding ,   SplashModelView> implements SplashView {

 
    @Inject SplashModelView modelView ;
    ActivitySplashBinding binding ;
    private Handler handler;
    private Runnable runnable;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = getViewDataBinding();
        inilizeVaribles();
    }
    private void inilizeVaribles() {
        modelView.attachView(this);
        initializeObjects();
    }
    private void initializeObjects() {

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                modelView.activityFinishedSplashTimer(SplashActivity.this);
            }
        };
    }
    @Override
    protected void onStart() {
        super.onStart();
        modelView.activityStarted();
    }
    @Override
    protected void onStop() {
        super.onStop();
        modelView.activityStopped();
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }
    @Override
    public SplashModelView getViewModel() {
        return modelView;
    }
    @Override
    public int getBindingVariable() {
        return 0;
    }
    @Override
    public void openVerifyPage() {
        IntentUtiles.openActivityInNewStack(SplashActivity.this, VerifyCodeActivity.class);
    }

    @Override
    public void openRemainPage() {
        IntentUtiles.openActivityInNewStack(SplashActivity.this, RemainingActivity.class);
    }

    @Override
    public void openHomeActivity() {
        IntentUtiles.openActivityInNewStack(SplashActivity.this, MainpageActivity.class);
    }
    @Override
    public void startSplashViewTimer() {
        handler.postDelayed(runnable, 3000);
    }

    @Override
    public void stopSplashViewTimer() {
        if (handler != null && runnable != null) {
            handler.removeCallbacks(runnable);
        }
    }
}
