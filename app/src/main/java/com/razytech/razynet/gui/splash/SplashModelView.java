package com.razytech.razynet.gui.splash;

import android.content.Context;

import com.razytech.razynet.baseClasses.BaseViewModel;
import com.razytech.razynet.data.prefs.PrefUtils;

/**
 * Created by A.Noby on 4/7/2019.
 */
 class SplashModelView   extends BaseViewModel<SplashView> {

 void activityFinishedSplashTimer (Context context ){

  if (PrefUtils.getUserformation(context)){
   view.openHomeActivity();
  }else{
   view.openVerifyPage();
  }
 }


 void activityStarted() {
  view.startSplashViewTimer();
 }


 void activityStopped() {
  view.stopSplashViewTimer();
 }
}
