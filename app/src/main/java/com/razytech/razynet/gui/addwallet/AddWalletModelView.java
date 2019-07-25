package com.razytech.razynet.gui.addwallet;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.CoordinatorLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.razytech.razynet.R;
import com.razytech.razynet.Utils.AppConstant;
import com.razytech.razynet.Utils.StaticMethods;
import com.razytech.razynet.Utils.Validator;
import com.razytech.razynet.baseClasses.BaseViewModel;
import com.razytech.razynet.data.beans.InviteResponse;
import com.razytech.razynet.data.beans.MultiPartImage;
import com.razytech.razynet.data.beans.UserResponse;
import com.razytech.razynet.data.network.ConnectionListener;
import com.razytech.razynet.data.network.ConnectionResponse;
import com.razytech.razynet.data.network.MainApi;
import com.razytech.razynet.data.network.MainApiBody;
import com.razytech.razynet.data.network.MainResponse;

import org.json.JSONException;

import okhttp3.RequestBody;

/**
 * Created by A.Noby on 6/11/2019.
 */
 class AddWalletModelView  extends BaseViewModel<AddWalletView> {

 void vaildatedata(Context context, CoordinatorLayout coordinatorLayout ,Activity activity , String phonenumber){
  boolean internetAvailable = StaticMethods.isConnectingToInternet(context);
  if (!internetAvailable) {
   view.showNoNetworkConnectionBase(coordinatorLayout,context);
   return;
  }

 else  if (Validator.isTextEmpty(phonenumber)) {
   view.showErrorMessageBase(coordinatorLayout,context, context.getString(R.string.emptynumberphone));
   return;
  }
  else  if (!Validator.validMobileNumber(phonenumber)) {
   view.showErrorMessageBase(coordinatorLayout,context, context.getString(R.string.invaildnumberphone));
   return;
  }
  senddata(context ,  coordinatorLayout , activity,phonenumber);
 }

 void senddata(Context context, CoordinatorLayout coordinatorLayout ,Activity  activity, String phonenumber) {

  RequestBody requestBody =null;
  try {
   requestBody =   MainApiBody.InviteBoby(phonenumber);
  } catch (JSONException e) {
   e.printStackTrace();
  }
  view.showloadingviewBase(context);
  String token  =  AppConstant.Tokenbar  +" "+ AppConstant.userResponse.getToken();
  MainApi.AddWalletapi(token,requestBody,
          new ConnectionListener<MainResponse<InviteResponse>>() {
           @Override
           public void onSuccess(ConnectionResponse<MainResponse<InviteResponse>> connectionResponse) {
            view.hideloadingviewBase();
            if (connectionResponse.data.success ) {
             ShowAlertDialoug(context ,  activity ,connectionResponse.data.data.getActivationCode() , connectionResponse.data.data.getBalance());
            } else {
             view.showErrorMessageBase(coordinatorLayout,context,connectionResponse.data.message);
            }
           }

           @Override
           public void onFail(Throwable throwable) {
            view.hideloadingviewBase();
            view.showErrorMessageBase(coordinatorLayout,context,context.getString(R.string.tryagaing));
            Log.e("error", throwable.toString());
           }
          });
 }


 void ShowAlertDialoug(Context context , Activity activity  , String message,  double balance){
  AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
  LayoutInflater inflater = activity.getLayoutInflater();
  View dialogView = inflater.inflate(R.layout.success_alert, null);
  dialogBuilder.setView(dialogView);
  AlertDialog alertDialog = dialogBuilder.create();

  Button btnok = (Button) dialogView.findViewById(R.id.btn_ok);
  TextView txtmessage = (TextView) dialogView.findViewById(R.id.txtverifycode);
  txtmessage.setText(message);
  alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;
  btnok.setOnClickListener((View) ->{
   view.SuccessData(message ,balance);
   alertDialog.dismiss();
  });
  alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
  alertDialog.setCancelable(false);
  alertDialog.show();

 }
}
