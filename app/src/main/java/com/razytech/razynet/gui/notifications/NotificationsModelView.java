package com.razytech.razynet.gui.notifications;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.Log;

import com.razytech.razynet.R;
import com.razytech.razynet.Utils.AppConstant;
import com.razytech.razynet.Utils.StaticMethods;
import com.razytech.razynet.baseClasses.BaseViewModel;
import com.razytech.razynet.data.beans.NotificationsResponse;
import com.razytech.razynet.data.beans.RedeemResponse;
import com.razytech.razynet.data.network.ConnectionListener;
import com.razytech.razynet.data.network.ConnectionResponse;
import com.razytech.razynet.data.network.MainApi;
import com.razytech.razynet.data.network.MainResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by A.Noby on 6/11/2019.
 */
 class NotificationsModelView extends BaseViewModel<NotificationsView> {


 void  loadingNotificationData(CoordinatorLayout coordinatorLayout, Context context  , boolean isrefresh){
  boolean internetAvailable = StaticMethods.isConnectingToInternet(context);
  if (!internetAvailable) {
   view.showNoNetworkConnectionBase(coordinatorLayout,context);
   return;
  }
  view.showloadingviewBase(context);
  String token  =  AppConstant.Tokenbar  +" "+ AppConstant.userResponse.getToken();
  MainApi.Notificationsapi(token,
          new ConnectionListener<MainResponse<List<NotificationsResponse>>>() {
           @Override
           public void onSuccess(ConnectionResponse<MainResponse<List<NotificationsResponse>>> connectionResponse) {
            view.hideloadingviewBase();
            if (connectionResponse.data.success ) {
             view.LoadingnotificationData(connectionResponse.data.data);
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
