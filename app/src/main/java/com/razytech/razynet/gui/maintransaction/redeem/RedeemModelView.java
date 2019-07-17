package com.razytech.razynet.gui.maintransaction.redeem;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.Log;

import com.razytech.razynet.R;
import com.razytech.razynet.Utils.AppConstant;
import com.razytech.razynet.Utils.StaticMethods;
import com.razytech.razynet.baseClasses.BaseViewModel;
import com.razytech.razynet.data.beans.InviteResponse;
import com.razytech.razynet.data.beans.RedeemResponse;
import com.razytech.razynet.data.network.ConnectionListener;
import com.razytech.razynet.data.network.ConnectionResponse;
import com.razytech.razynet.data.network.MainApi;
import com.razytech.razynet.data.network.MainApiBody;
import com.razytech.razynet.data.network.MainResponse;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import okhttp3.RequestBody;

/**
 * Created by A.Noby on 6/11/2019.
 */
 class RedeemModelView  extends BaseViewModel<RedeemView> {

 void  loadingRedeemData(CoordinatorLayout coordinatorLayout, Context context  , boolean isrefresh){
       boolean internetAvailable = StaticMethods.isConnectingToInternet(context);
         if (!internetAvailable) {
             view.showNoNetworkConnectionBase(coordinatorLayout,context);
            return;
             }

         view.showloadingviewBase(context);
         String token  =  AppConstant.Tokenbar  +" "+ AppConstant.userResponse.getToken();
          MainApi.Redeemapi(token,
          new ConnectionListener<MainResponse<List<RedeemResponse>>>() {
           @Override
           public void onSuccess(ConnectionResponse<MainResponse<List<RedeemResponse>>> connectionResponse) {
            view.hideloadingviewBase();
            if (connectionResponse.data.success ) {
             view.LoadingReddemData(connectionResponse.data.data);
            } else {
           //  view.showErrorMessageBase(coordinatorLayout,context,connectionResponse.data.message);
               view.show_errorView(true ,  connectionResponse.data.message);
            }
           }

           @Override
           public void onFail(Throwable throwable) {
            view.hideloadingviewBase();
           // view.showErrorMessageBase(coordinatorLayout,context,context.getString(R.string.tryagaing));
            //Log.e("error", throwable.toString());
               view.show_errorView(true , context.getString(R.string.tryagaing));

           }
          });
 }
}
