package com.razytech.razynet.gui.maintransaction.redeempoints;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.Log;

import com.razytech.razynet.R;
import com.razytech.razynet.Utils.AppConstant;
import com.razytech.razynet.Utils.StaticMethods;
import com.razytech.razynet.baseClasses.BaseViewModel;
import com.razytech.razynet.data.beans.RedeemPointsResponse;
import com.razytech.razynet.data.beans.RedeemResponse;
import com.razytech.razynet.data.network.ConnectionListener;
import com.razytech.razynet.data.network.ConnectionResponse;
import com.razytech.razynet.data.network.MainApi;
import com.razytech.razynet.data.network.MainResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by A.Noby on 6/12/2019.
 */
 class RedeemListPointsModelView   extends BaseViewModel<RedeemListPointsView> {



 void  loadingRedeemData(CoordinatorLayout coordinatorLayout, Context context  , boolean isrefresh ,  String ServicesId){
  boolean internetAvailable = StaticMethods.isConnectingToInternet(context);
  if (!internetAvailable) {
   view.showNoNetworkConnectionBase(coordinatorLayout,context);
   return;
  }

  view.showloadingviewBase(context);
  String token  =  AppConstant.Tokenbar  +" "+ AppConstant.userResponse.getToken();
  MainApi.RedeemPointsapi(token,ServicesId,
          new ConnectionListener<MainResponse<List<RedeemPointsResponse>>>() {
           @Override
           public void onSuccess(ConnectionResponse<MainResponse<List<RedeemPointsResponse>>> connectionResponse) {
            view.hideloadingviewBase();
            if (connectionResponse.data.success ) {
             view.LoadingReddemData(connectionResponse.data.data);
            } else {
            // view.showErrorMessageBase(coordinatorLayout,context,connectionResponse.data.message);
                view.show_errorView(true ,  connectionResponse.data.message);

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
