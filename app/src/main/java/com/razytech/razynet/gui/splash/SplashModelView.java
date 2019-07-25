package com.razytech.razynet.gui.splash;

import android.content.Context;

import com.razytech.razynet.Utils.AppConstant;
import com.razytech.razynet.baseClasses.BaseViewModel;
import com.razytech.razynet.data.prefs.PrefUtils;

/**
 * Created by A.Noby on 4/7/2019.
 */
 class SplashModelView   extends BaseViewModel<SplashView> {

 void activityFinishedSplashTimer (Context context ){

  if (PrefUtils.getUserformation(context)){
   if (AppConstant.userResponse.isApproved())
    view.openHomeActivity();
   else
    view.openRemainPage();
  }else{
   int status =  PrefUtils.User_Status(context);
   if (status ==  PrefUtils.User_Verify)
     view.openVerifyPage();
   else if (status ==  PrefUtils.User_Singout)
     view.openLoginPage();
  }
 }


 void activityStarted() {
  view.startSplashViewTimer();
 }


 void activityStopped() {
  view.stopSplashViewTimer();
 }
}
