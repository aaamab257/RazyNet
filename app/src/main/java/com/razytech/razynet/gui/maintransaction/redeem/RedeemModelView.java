package com.razytech.razynet.gui.maintransaction.redeem;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;

import com.razytech.razynet.R;
import com.razytech.razynet.Utils.StaticMethods;
import com.razytech.razynet.baseClasses.BaseViewModel;
import com.razytech.razynet.data.beans.RedeemResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by A.Noby on 6/11/2019.
 */
 class RedeemModelView  extends BaseViewModel<RedeemView> {

  List<RedeemResponse> LoadingTestData(){

   List<RedeemResponse> redeemResponses  =  new ArrayList<>();
   RedeemResponse response =  new RedeemResponse();
   response.setId("1");
   response.setCompanyname("Masary");
   redeemResponses.add(response);
   response =  new RedeemResponse();
   response.setId("2");
   response.setCompanyname("careem");
   redeemResponses.add(response);
   response =  new RedeemResponse();
   response.setId("3");
   response.setCompanyname("Other");
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
