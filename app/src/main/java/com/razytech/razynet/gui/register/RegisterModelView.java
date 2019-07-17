package com.razytech.razynet.gui.register;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.Log;

import com.razytech.razynet.R;
import com.razytech.razynet.Utils.AppConstant;
import com.razytech.razynet.Utils.StaticMethods;
import com.razytech.razynet.Utils.Validator;
import com.razytech.razynet.baseClasses.BaseViewModel;
import com.razytech.razynet.data.beans.AreaResponse;
import com.razytech.razynet.data.beans.CityResponse;
import com.razytech.razynet.data.beans.MultiPartImage;
import com.razytech.razynet.data.beans.UserResponse;
import com.razytech.razynet.data.beans.VerifyCodeResponse;
import com.razytech.razynet.data.network.ConnectionListener;
import com.razytech.razynet.data.network.ConnectionResponse;
import com.razytech.razynet.data.network.MainApi;
import com.razytech.razynet.data.network.MainApiBody;
import com.razytech.razynet.data.network.MainResponse;
import com.razytech.razynet.data.prefs.PrefUtils;

import org.json.JSONException;

import java.io.File;
import java.util.List;

import okhttp3.RequestBody;

/**
 * Created by A.Noby on 6/9/2019.
 */
 class RegisterModelView   extends BaseViewModel<RegisterView> {




 void loadCitiesData (Context context , CoordinatorLayout coordinatorLayout ,  String Token){
  boolean internetAvailable = StaticMethods.isConnectingToInternet(context);
  if (!internetAvailable) {
   view.showNoNetworkConnectionBase(coordinatorLayout,context);
   return;
  }
  view.showloadingviewBase(context);
  String token  =  AppConstant.Tokenbar  +" "+ Token;
  MainApi.Cityapi(token, new ConnectionListener<MainResponse<List<CityResponse>>>() {
   @Override
   public void onSuccess(ConnectionResponse<MainResponse<List<CityResponse>>> connectionResponse) {
    view.hideloadingviewBase();
    if (connectionResponse.data.success ) {
     view.LoadCitydata(connectionResponse.data.data);
    } else {
     view.showErrorMessageBase(coordinatorLayout,context,connectionResponse.data.message);
    }
   }
   @Override
   public void onFail(Throwable throwable) {
    view.hideloadingviewBase();
    view.showErrorMessageBase(coordinatorLayout,context,context.getString(R.string.tryagaing));
    Log.e("errorstatement",""+throwable.getMessage());
   }
  });
 }

  void loadAreasData (Context context , CoordinatorLayout coordinatorLayout  ,   String CityId,  String Token){
  boolean internetAvailable = StaticMethods.isConnectingToInternet(context);
  if (!internetAvailable) {
   view.showNoNetworkConnectionBase(coordinatorLayout,context);
   return;
  }
  view.showloadingviewBase(context);
   String token  =  AppConstant.Tokenbar  +" "+ Token;
  MainApi.Areaapi(token,CityId ,new ConnectionListener<MainResponse<List<AreaResponse>>>() {
   @Override
   public void onSuccess(ConnectionResponse<MainResponse<List<AreaResponse>>> connectionResponse) {
    view.hideloadingviewBase();
    if (connectionResponse.data.success ) {
     view.LoadAreadata(connectionResponse.data.data);
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



 void vaildatedata(Context context, CoordinatorLayout coordinatorLayout , String UserName,
                   String nid, String password ,String confpassword,int cityId  ,int AreaId
         ,File file ,  String Token) {

  boolean internetAvailable = StaticMethods.isConnectingToInternet(context);
     if (!internetAvailable) {
         view.showNoNetworkConnectionBase(coordinatorLayout,context);
         return;
     }

     if (Validator.isTextEmpty(UserName)) {
         view.showErrorMessageBase(coordinatorLayout,context, context.getString(R.string.emptyuserName));
         return;
     }
  else  if (Validator.isTextEmpty(nid)) {
   view.showErrorMessageBase(coordinatorLayout,context, context.getString(R.string.emptynid));
   return;
  }
  else  if (Validator.isTextEmpty(password)) {
   view.showErrorMessageBase(coordinatorLayout,context, context.getString(R.string.emptyPassword));
   return;
  }
  else  if (Validator.isTextEmpty(confpassword)) {
   view.showErrorMessageBase(coordinatorLayout,context, context.getString(R.string.emptyConfPassword));
   return;
  }
  else if (!Validator.validPasswordLength(password)){
   view.showErrorMessageBase(coordinatorLayout,context,context.getString(R.string.invaildPasswordLenght));
   return;
  }
  else if (!Validator.isMatch(password,confpassword)){
   view.showErrorMessageBase(coordinatorLayout,context,context.getString(R.string.Passworddonotmatch));
   return;
  }
  else  if (cityId == -1) {
   view.showErrorMessageBase(coordinatorLayout,context, context.getString(R.string.emptyCity));
   return;
  }
  else  if (AreaId == -1) {
   view.showErrorMessageBase(coordinatorLayout,context, context.getString(R.string.emptyArea));
   return;
  }

  SendRegisterData(context,coordinatorLayout,UserName,nid,password,cityId+"",AreaId+"",file ,  Token);

 }

 private void SendRegisterData(Context context, CoordinatorLayout coordinatorLayout,
                               String UserName, String nid, String password ,
                               String cityId  , String AreaId , File file ,  String Token) {

  MultiPartImage multiPartImage =null;
  try {
   multiPartImage =   MainApiBody.RegisterBoby(UserName ,cityId , AreaId ,  nid , password, file);
  } catch (JSONException e) {
   e.printStackTrace();
  }
  view.showloadingviewBase(context);
  String token  =  AppConstant.Tokenbar  +" "+ Token;
  MainApi.Registerapi(token,multiPartImage.imagereq,multiPartImage.UserNamereq,multiPartImage.CityIdreq
          ,multiPartImage.AreaIdreq,multiPartImage.IdentityNoreq,multiPartImage.Passwordreq,
          new ConnectionListener<MainResponse<UserResponse>>() {
           @Override
           public void onSuccess(ConnectionResponse<MainResponse<UserResponse>> connectionResponse) {
            view.hideloadingviewBase();
            if (connectionResponse.data.success ) {
             view.SavingData(connectionResponse.data.data);
             view.showSuccessMessageBase(context ,connectionResponse.data.message  );
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
}
