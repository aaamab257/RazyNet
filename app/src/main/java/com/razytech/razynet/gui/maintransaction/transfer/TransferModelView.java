package com.razytech.razynet.gui.maintransaction.transfer;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;

import com.razytech.razynet.R;
import com.razytech.razynet.Utils.StaticMethods;
import com.razytech.razynet.Utils.Validator;
import com.razytech.razynet.baseClasses.BaseViewModel;
import com.razytech.razynet.gui.homepage.HomeFragment;
import com.razytech.razynet.gui.mainpage.MainpageActivity;

import static com.razytech.razynet.Utils.AppConstant.HOME_page;
import static com.razytech.razynet.Utils.AppConstant.TRANSFERCONFIRMFINAL_page;
import static com.razytech.razynet.Utils.AppConstant.TRANSFERCONFIRM_page;
import static com.razytech.razynet.Utils.AppConstant.TRANSFERPOINTS_page;
import static com.razytech.razynet.Utils.AppConstant.TRANSFERWALLET_page;

/**
 * Created by A.Noby on 6/11/2019.
 */
 class TransferModelView  extends BaseViewModel<TransferView> {

     String numberPhone =  ""  , points =  "" ;


     void setbtnNextWallet(CoordinatorLayout coordinatorLayout  , Context context  , String phoneNumber){

        if (Validator.isTextEmpty(phoneNumber)) {
       view.showErrorMessageBase(context, context.getString(R.string.emptynumberphone));
       return;
      }
      else  if (!Validator.validMobileNumber(phoneNumber)) {
       view.showErrorMessageBase(context, context.getString(R.string.invaildnumberphone));
       return;
      }
         numberPhone  = phoneNumber  ;
      view.SetStepsHandlingView(TRANSFERPOINTS_page);
     }

 void setbtnPoints(CoordinatorLayout coordinatorLayout  , Context context  , String Points ,  boolean isNext){
  if (isNext) {
   if (Validator.isTextEmpty(Points)) {
    view.showErrorMessageBase(context, context.getString(R.string.emptynumberphone));
    return;
   } else if (Integer.parseInt(Points) > 128) {
    view.showErrorMessageBase(context, context.getString(R.string.donothaveenoughpoints));
    return;
   }
     points  = Points  ;
      view.SetPointValue(points);
      view.SetStepsHandlingView(TRANSFERCONFIRM_page);
  }else {
   view.SetStepsHandlingView(TRANSFERWALLET_page);
  }
 }
 void setbtnConfirm(CoordinatorLayout coordinatorLayout  , Context context   ,  boolean isNext){
  boolean internetAvailable = StaticMethods.isConnectingToInternet(context);
  if (isNext) {
      view.SetPointValue(points);
      view.SetStepsHandlingView(TRANSFERCONFIRMFINAL_page);
  }else {
   view.SetStepsHandlingView(TRANSFERPOINTS_page);
  }
 }

 void setbtnHome(CoordinatorLayout coordinatorLayout  , Context context  ){
      boolean internetAvailable = StaticMethods.isConnectingToInternet(context);
        ((MainpageActivity)context).displayView(HOME_page);
    }

 }
