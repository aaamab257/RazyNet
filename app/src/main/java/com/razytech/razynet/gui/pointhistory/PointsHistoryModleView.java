package com.razytech.razynet.gui.pointhistory;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.Log;

import com.razytech.razynet.R;
import com.razytech.razynet.Utils.AppConstant;
import com.razytech.razynet.Utils.StaticMethods;
import com.razytech.razynet.baseClasses.BaseFragment;
import com.razytech.razynet.baseClasses.BaseViewModel;
import com.razytech.razynet.data.beans.ChildResponse;
import com.razytech.razynet.data.beans.NotificationsResponse;
import com.razytech.razynet.data.beans.PointHistoryResponse;
import com.razytech.razynet.data.beans.PointsResponse;
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
 class PointsHistoryModleView  extends BaseViewModel<PointsHistoryView> {



 void  loadingPointsData(CoordinatorLayout coordinatorLayout, Context context  , boolean isrefresh){
  boolean internetAvailable = StaticMethods.isConnectingToInternet(context);
  if (!internetAvailable) {
   view.showNoNetworkConnectionBase(coordinatorLayout,context);
   return;
  }
//  AppConstant.pointsResponses =  LoadingTestData() ;
//  view.LoadingPointsData(LoadingTestData());

  view.showloadingviewBase(context);
  String token  =  AppConstant.Tokenbar  +" "+ AppConstant.userResponse.getToken();
  MainApi.PointHistoryapi(token,
          new ConnectionListener<MainResponse<List<PointHistoryResponse>>>() {
           @Override
           public void onSuccess(ConnectionResponse<MainResponse<List<PointHistoryResponse>>> connectionResponse) {
            view.hideloadingviewBase();
            view.hide_refreshView();
            if (connectionResponse.data.success ) {
             if (connectionResponse.data.data != null) {
              AppConstant.pointsResponses = connectionResponse.data.data;
              view.LoadingPointsData(connectionResponse.data.data);
             }else
              view.show_errorView(true ,context.getString(R.string.donothavepointshistory));
            } else {
            // view.showErrorMessageBase(coordinatorLayout,context,connectionResponse.data.message);
              view.show_errorView(true , connectionResponse.data.message);
            }
           }

           @Override
           public void onFail(Throwable throwable) {
            view.hideloadingviewBase();
            view.hide_refreshView();
               if(throwable.getMessage().contains("401")){
                   ((MainpageActivity)context).logout();
               }
            view.showErrorMessageBase(coordinatorLayout,context,context.getString(R.string.tryagaing));
            Log.e("error", throwable.toString());
           }
          });
 }
}
