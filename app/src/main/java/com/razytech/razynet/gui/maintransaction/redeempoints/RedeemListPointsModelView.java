package com.razytech.razynet.gui.maintransaction.redeempoints;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;

import com.razytech.razynet.Utils.StaticMethods;
import com.razytech.razynet.baseClasses.BaseViewModel;
import com.razytech.razynet.data.beans.RedeemPointsResponse;
import com.razytech.razynet.data.beans.RedeemResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by A.Noby on 6/12/2019.
 */
 class RedeemListPointsModelView   extends BaseViewModel<RedeemListPointsView> {


  List<RedeemPointsResponse> LoadingTestData(){

  List<RedeemPointsResponse> redeemResponses  =  new ArrayList<>();
  RedeemPointsResponse response =  new RedeemPointsResponse();
  response.setId("1");
  response.setPoints("200");
  redeemResponses.add(response);
  response =  new RedeemPointsResponse();
  response.setId("2");
  response.setPoints("1100");
  redeemResponses.add(response);
  response =  new RedeemPointsResponse();
  response.setId("3");
  response.setPoints("100");
  redeemResponses.add(response);
  return redeemResponses ;
 }

 void  loadingRedeemData(CoordinatorLayout coordinatorLayout, Context context  , boolean isrefresh){
  boolean internetAvailable = StaticMethods.isConnectingToInternet(context);
  if (!internetAvailable) {
   view.showNoNetworkConnectionBase(coordinatorLayout,context);
   return;
  }
  view.LoadingReddemData(LoadingTestData());
 }

 }
