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
import com.razytech.razynet.gui.mainpage.MainpageActivity;

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
            view.hide_refreshView();
            if (connectionResponse.data.success ) {
                if (connectionResponse.data.data != null)
                   view.LoadingnotificationData(connectionResponse.data.data);
                else
                    view.show_errorView(true ,context.getString(R.string.donothavenotifications));
            } else {
          //   view.showErrorMessageBase(coordinatorLayout,context,connectionResponse.data.message);
                view.show_errorView(true ,context.getString(R.string.donothavenotifications));
            }
           }

           @Override
           public void onFail(Throwable throwable) {
            view.hideloadingviewBase();
             view.hide_refreshView();
               if(throwable.getMessage().contains("401")){
                   ((MainpageActivity)context).logout();
               }
           // view.showErrorMessageBase(coordinatorLayout,context,context.getString(R.string.tryagaing));
               view.show_errorView(true ,context.getString(R.string.tryagaing));
            Log.e("error", throwable.toString());
           }
          });
 }
}
