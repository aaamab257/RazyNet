package com.razytech.razynet.gui.loginpage;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.Log;

import com.razytech.razynet.R;
import com.razytech.razynet.Utils.AppConstant;
import com.razytech.razynet.Utils.StaticMethods;
import com.razytech.razynet.Utils.Validator;
import com.razytech.razynet.baseClasses.BaseViewModel;
import com.razytech.razynet.data.beans.HomeResponse;
import com.razytech.razynet.data.beans.UserResponse;
import com.razytech.razynet.data.network.ConnectionListener;
import com.razytech.razynet.data.network.ConnectionResponse;
import com.razytech.razynet.data.network.MainApi;
import com.razytech.razynet.data.network.MainApiBody;
import com.razytech.razynet.data.network.MainResponse;

import org.json.JSONException;

import okhttp3.RequestBody;

/**
 * Created by A.Noby on 7/22/2019.
 */
 class LoginModelView  extends BaseViewModel<LoginView> {

 void  loginData( Context context ,  CoordinatorLayout coordinatorLayout,String phone  , String password ){
  boolean internetAvailable = StaticMethods.isConnectingToInternet(context);
  if (!internetAvailable) {
   view.showNoNetworkConnectionBase(coordinatorLayout,context);
   return;
  }

  else  if (Validator.isTextEmpty(phone)) {
   view.showErrorMessageBase(coordinatorLayout,context, context.getString(R.string.emptynumberphone));
   return;
  }
  else  if (Validator.isTextEmpty(password)) {
   view.showErrorMessageBase(coordinatorLayout,context, context.getString(R.string.emptyPassword));
   return;
  }

  RequestBody requestBody = null;
  try {
   requestBody = MainApiBody.LoginBoby(phone ,  password);
  } catch (JSONException e) {
   e.printStackTrace();
  }

  view.showloadingviewBase(context);
  MainApi.Loginapi(requestBody,
          new ConnectionListener<MainResponse<UserResponse>>() {
           @Override
           public void onSuccess(ConnectionResponse<MainResponse<UserResponse>> connectionResponse) {
            view.hideloadingviewBase();
            if (connectionResponse.data.success ) {
             if (connectionResponse.data.data !=  null) {
              view.showSuccessMessageBase(coordinatorLayout, context, connectionResponse.data.message);
              view.SavingData(connectionResponse.data.data);

             }else
              view.showErrorMessageBase(coordinatorLayout,context,connectionResponse.data.message);
            } else {
             view.showErrorMessageBase(coordinatorLayout,context,connectionResponse.data.message);
            }
           }
           @Override
           public void onFail(Throwable throwable) {
            view.hideloadingviewBase();
            view.showErrorMessageBase(coordinatorLayout,context,context.getString(R.string.tryagaing));
           }
          });
 }


}
