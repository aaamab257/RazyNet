package com.razytech.razynet.gui.maintransaction.redeempoints;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.CoordinatorLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.razytech.razynet.R;
import com.razytech.razynet.Utils.AppConstant;
import com.razytech.razynet.Utils.StaticMethods;
import com.razytech.razynet.baseClasses.BaseViewModel;
import com.razytech.razynet.data.beans.RedeemPointsResponse;
import com.razytech.razynet.data.beans.RedeemResponse;
import com.razytech.razynet.data.beans.RedeemUpdateResponse;
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
 * Created by A.Noby on 6/12/2019.
 */
 class RedeemListPointsModelView   extends BaseViewModel<RedeemListPointsView> {



 void  loadingRedeemData(CoordinatorLayout coordinatorLayout, Context context,  String ServicesId){
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



    void  RedeemPoint(CoordinatorLayout coordinatorLayout, Context context, String id ,double prroductvalue  ){
        boolean internetAvailable = StaticMethods.isConnectingToInternet(context);
        if (!internetAvailable) {
            view.showNoNetworkConnectionBase(coordinatorLayout,context);
            return;
        }
        else if (prroductvalue > AppConstant.userResponse.getBalance()){
            view.showErrorMessageBase(context, context.getString(R.string.donothaveenoughpoints));
            return;
        }
        RequestBody requestBody = null;
        try {
            requestBody = MainApiBody.RedeemPointBoby(id);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        view.showloadingviewBase(context);
        String token  =  AppConstant.Tokenbar  +" "+ AppConstant.userResponse.getToken();
        MainApi.RedeemUpdateapi(token,requestBody,
                new ConnectionListener<MainResponse<RedeemUpdateResponse>>() {
                    @Override
                    public void onSuccess(ConnectionResponse<MainResponse<RedeemUpdateResponse>> connectionResponse) {
                        view.hideloadingviewBase();
                        if (connectionResponse.data.success ) {
                           // view.LoadingReddemData(connectionResponse.data.data);
                            view.showSuccessMessageBase(context  ,connectionResponse.data.message );
                            view.After_redeemProduct(connectionResponse.data.data.getUpdatedPoint());
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
