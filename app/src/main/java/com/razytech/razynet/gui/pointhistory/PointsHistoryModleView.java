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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by A.Noby on 6/11/2019.
 */
 class PointsHistoryModleView  extends BaseViewModel<PointsHistoryView> {

 List<PointsResponse> LoadingTestData(){

  List<PointsResponse> redeemResponses  =  new ArrayList<>();
  PointsResponse response =  new PointsResponse();
  response.setId("1");
  response.setTitle("points that add to you ");
  response.setPoints("10");
  response.setAvapoints("128");
  response.setDate("18/6/2019");
  response.setIsin(true);
  redeemResponses.add(response);

  response =  new PointsResponse();
  response.setId("2");
  response.setTitle("Points deducted from your points");
  response.setPoints("20");
  response.setAvapoints("128");
  response.setDate("13/6/2019");
  response.setIsin(false);
  redeemResponses.add(response);

  response =  new PointsResponse();
  response.setId("3");
  response.setTitle("points that add to you ");
  response.setPoints("30");
  response.setAvapoints("128");
  response.setDate("13/6/2019");
  response.setIsin(true);
  redeemResponses.add(response);

  response =  new PointsResponse();
  response.setId("4");
  response.setTitle("Points deducted from your points");
  response.setPoints("40");
  response.setAvapoints("128");
  response.setDate("18/6/2019");
  response.setIsin(false);
  redeemResponses.add(response);

  response =  new PointsResponse();
  response.setId("5");
  response.setTitle("points that add to you ");
  response.setPoints("50");
  response.setAvapoints("128");
  response.setDate("12/6/2019");
  response.setIsin(true);
  redeemResponses.add(response);

  response =  new PointsResponse();
  response.setId("6");
  response.setTitle("Points deducted from your points");
  response.setPoints("60");
  response.setAvapoints("128");
  response.setDate("12/6/2019");
  response.setIsin(false);
  redeemResponses.add(response);

  return redeemResponses ;
 }

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
            if (connectionResponse.data.success ) {
             AppConstant.pointsResponses =  connectionResponse.data.data;
             view.LoadingPointsData(connectionResponse.data.data);
            } else {
            // view.showErrorMessageBase(coordinatorLayout,context,connectionResponse.data.message);
              view.show_errorView(true , connectionResponse.data.message);
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
