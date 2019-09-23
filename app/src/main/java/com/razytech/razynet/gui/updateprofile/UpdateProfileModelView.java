package com.razytech.razynet.gui.updateprofile;

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
import com.razytech.razynet.data.beans.MultiPartUpdate;
import com.razytech.razynet.data.beans.UserResponse;
import com.razytech.razynet.data.network.ConnectionListener;
import com.razytech.razynet.data.network.ConnectionResponse;
import com.razytech.razynet.data.network.MainApi;
import com.razytech.razynet.data.network.MainApiBody;
import com.razytech.razynet.data.network.MainResponse;
import com.razytech.razynet.gui.mainpage.MainpageActivity;

import org.json.JSONException;

import java.io.File;
import java.util.List;

/**
 * Created by A.Noby on 6/11/2019.
 */
 class UpdateProfileModelView extends BaseViewModel<UpdateProfileView> {

 void loadCitiesData (Context context , CoordinatorLayout coordinatorLayout ,
                      String Token){
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
    if(throwable.getMessage().contains("401")){
     ((MainpageActivity)context).logout();
    }
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
    if(throwable.getMessage().contains("401")){
     ((MainpageActivity)context).logout();
    }
    view.showErrorMessageBase(coordinatorLayout,context,context.getString(R.string.tryagaing));
   }
  });
 }


 void vaildatedata(Context context, CoordinatorLayout coordinatorLayout ,
                   String UserName, int cityId  , int AreaId
         , File file ) {

  boolean internetAvailable = StaticMethods.isConnectingToInternet(context);
  if (!internetAvailable) {
   view.showNoNetworkConnectionBase(coordinatorLayout,context);
   return;
  }

  if (Validator.isTextEmpty(UserName)) {
   view.showErrorMessageBase(coordinatorLayout,context, context.getString(R.string.emptyuserName));
   return;
  }
  else  if (cityId != -1 && AreaId == -1) {
   view.showErrorMessageBase(coordinatorLayout,context, context.getString(R.string.emptyArea));
   return;
  }
  else  if (cityId == -1 && AreaId == -1) {
   cityId =  Integer.parseInt(AppConstant.userResponse.getCityId());
   AreaId =  Integer.parseInt(AppConstant.userResponse.getAreaId());
  }


  SendRegisterData(context,coordinatorLayout,UserName,cityId+"",AreaId+"",file );

 }

 private void SendRegisterData(Context context, CoordinatorLayout coordinatorLayout, String userName, String cityId, String AreaId, File file) {


  MultiPartUpdate multiPartImage =null;
  try {
   multiPartImage =   MainApiBody.UpdateBoby(userName ,cityId , AreaId , file);
  } catch (JSONException e) {
   e.printStackTrace();
  }
  view.showloadingviewBase(context);
  String token  =  AppConstant.Tokenbar  +" "+ AppConstant.userResponse.getToken();
  MainApi.Updateapi(token,multiPartImage.imagereq,multiPartImage.Namereq,multiPartImage.CityIdreq
          ,multiPartImage.AreaIdreq,
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
            if(throwable.getMessage().contains("401")){
             ((MainpageActivity)context).logout();
            }
            view.showErrorMessageBase(coordinatorLayout,context,context.getString(R.string.tryagaing));
            Log.e("error", throwable.toString());
           }
          });

 }

}
