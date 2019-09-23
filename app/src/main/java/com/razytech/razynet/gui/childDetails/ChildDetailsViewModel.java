package com.razytech.razynet.gui.childDetails;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.Log;

import com.razytech.razynet.R;
import com.razytech.razynet.Utils.AppConstant;
import com.razytech.razynet.Utils.StaticMethods;
import com.razytech.razynet.Utils.Validator;
import com.razytech.razynet.baseClasses.BaseViewModel;
import com.razytech.razynet.data.beans.ChildResponse;
import com.razytech.razynet.data.network.ConnectionListener;
import com.razytech.razynet.data.network.ConnectionResponse;
import com.razytech.razynet.data.network.MainApi;
import com.razytech.razynet.data.network.MainApiBody;
import com.razytech.razynet.data.network.MainResponse;
import com.razytech.razynet.gui.mainpage.MainpageActivity;

import org.json.JSONException;

import java.util.List;

import okhttp3.RequestBody;

/**
 * Created by A.Noby on 7/2/2019.
 */
 class ChildDetailsViewModel extends BaseViewModel<ChildDetailsView> {

 void  loadingChildsData(CoordinatorLayout coordinatorLayout, Context context ,  String WalletId){
  boolean internetAvailable = StaticMethods.isConnectingToInternet(context);
  if (!internetAvailable) {
   view.showNoNetworkConnectionBase(coordinatorLayout,context);
   return;
  }
  RequestBody requestBody = null;
  try {
   requestBody = MainApiBody.GetChildListBoby(WalletId);
  } catch (JSONException e) {
   e.printStackTrace();
  }
  view.showloadingviewBase(context);
  String token  =  AppConstant.Tokenbar  +" "+ AppConstant.userResponse.getToken();

  MainApi.ChildernListapi(token,requestBody,
          new ConnectionListener<MainResponse<List<ChildResponse>>>() {
           @Override
           public void onSuccess(ConnectionResponse<MainResponse<List<ChildResponse>>> connectionResponse) {
            view.hideloadingviewBase();
            if (connectionResponse.data.success ) {
             view.LoadingchildData(connectionResponse.data.data);
            } else {
             view.show_errorView(true,connectionResponse.data.message);
            }
           }
           @Override
           public void onFail(Throwable throwable) {
            view.hideloadingviewBase();
               if(throwable.getMessage().contains("401")){
                   ((MainpageActivity)context).logout();
               }
            //  view.showErrorMessageBase(coordinatorLayout,context,context.getString(R.string.tryagaing));
            view.show_errorView(true,context.getString(R.string.tryagaing));
           }
          });
 }}
