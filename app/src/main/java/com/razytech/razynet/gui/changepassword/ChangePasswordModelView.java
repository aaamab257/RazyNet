package com.razytech.razynet.gui.changepassword;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;

import com.razytech.razynet.R;
import com.razytech.razynet.Utils.AppConstant;
import com.razytech.razynet.Utils.StaticMethods;
import com.razytech.razynet.Utils.Validator;
import com.razytech.razynet.baseClasses.BaseViewModel;
import com.razytech.razynet.data.beans.UserResponse;
import com.razytech.razynet.data.network.ConnectionListener;
import com.razytech.razynet.data.network.ConnectionResponse;
import com.razytech.razynet.data.network.MainApi;
import com.razytech.razynet.data.network.MainApiBody;
import com.razytech.razynet.data.network.MainResponse;
import com.razytech.razynet.gui.mainpage.MainpageActivity;

import org.json.JSONException;

import java.io.File;

import okhttp3.RequestBody;

/**
 * Created by A.Noby on 9/16/2019.
 */
 class ChangePasswordModelView  extends BaseViewModel<ChangePasswordView> {

 void vaildatedata(Context context, CoordinatorLayout coordinatorLayout ,
                   String oldpassword,
                   String newpassword , String confpassword) {

  boolean internetAvailable = StaticMethods.isConnectingToInternet(context);
  if (!internetAvailable) {
   view.showNoNetworkConnectionBase(coordinatorLayout,context);
   return;
  }
  else  if (Validator.isTextEmpty(oldpassword)) {
   view.showErrorMessageBase(coordinatorLayout,context, context.getString(R.string.emptyoldPassword));
   return;
  }
  else  if (Validator.isTextEmpty(newpassword)) {
   view.showErrorMessageBase(coordinatorLayout,context, context.getString(R.string.emptynewPassword));
   return;
  }
  else  if (Validator.isTextEmpty(confpassword)) {
   view.showErrorMessageBase(coordinatorLayout,context, context.getString(R.string.emptyConfPassword));
   return;
  }
  else if (!Validator.validPasswordLength(newpassword)){
   view.showErrorMessageBase(coordinatorLayout,context,context.getString(R.string.invaildPasswordLenght));
   return;
  }
  else if (!Validator.isMatch(newpassword,confpassword)){
   view.showErrorMessageBase(coordinatorLayout,context,context.getString(R.string.Passworddonotmatch));
   return;
  }
 

  SendChangePasswordData(context,coordinatorLayout,oldpassword , newpassword , confpassword);

 }

 private void SendChangePasswordData(Context context, CoordinatorLayout coordinatorLayout,
                                     String oldpassword, String newpassword, String confpassword) {


  RequestBody requestBody = null;
  try {
   requestBody = MainApiBody.ChangePasswordBoby(newpassword ,  oldpassword);
  } catch (JSONException e) {
   e.printStackTrace();
  }
  String token  =  AppConstant.Tokenbar  +" "+ AppConstant.userResponse.getToken();
  view.showloadingviewBase(context);
  MainApi.ChangePasswordpi(token,requestBody,
          new ConnectionListener<MainResponse<UserResponse>>() {
           @Override
           public void onSuccess(ConnectionResponse<MainResponse<UserResponse>> connectionResponse) {
            view.hideloadingviewBase();
            if (connectionResponse.data.success ) {

             view.showSuccessMessageBase(coordinatorLayout, context, connectionResponse.data.message);
             view.ChangePaswwordSuccessfully();

            } else {
             view.showErrorMessageBase(coordinatorLayout,context,connectionResponse.data.message);
            }
           }
           @Override
           public void onFail(Throwable throwable) {
            view.hideloadingviewBase();
            if(throwable.getMessage().contains("401")){
             ((MainpageActivity)context).logout();
            }
            view.showErrorMessageBase(coordinatorLayout,context,context.getString(R.string.tryagaing));
           }
          });
 }
}
