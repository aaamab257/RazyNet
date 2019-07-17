package com.razytech.razynet.gui.homepage;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.Log;

import com.razytech.razynet.R;
import com.razytech.razynet.Utils.AppConstant;
import com.razytech.razynet.Utils.StaticMethods;
import com.razytech.razynet.baseClasses.BaseViewModel;
import com.razytech.razynet.data.beans.ChildResponse;
import com.razytech.razynet.data.beans.HomeResponse;
import com.razytech.razynet.data.beans.MultiPartImage;
import com.razytech.razynet.data.beans.UserResponse;
import com.razytech.razynet.data.network.ConnectionListener;
import com.razytech.razynet.data.network.ConnectionResponse;
import com.razytech.razynet.data.network.MainApi;
import com.razytech.razynet.data.network.MainApiBody;
import com.razytech.razynet.data.network.MainResponse;

import org.json.JSONException;

import java.util.List;

import okhttp3.RequestBody;

/**
 * Created by A.Noby on 6/10/2019.
 */
 class HomeModelView extends BaseViewModel<HomeView> {

 void  loadingHomeData(CoordinatorLayout coordinatorLayout, Context context ){
  boolean internetAvailable = StaticMethods.isConnectingToInternet(context);
  if (!internetAvailable) {
   view.showNoNetworkConnectionBase(coordinatorLayout,context);
   return;
  }

  view.showloadingviewBase(context);
  String token  =  AppConstant.Tokenbar  +" "+ AppConstant.userResponse.getToken();
  Log.e("getToken",""+token);
  MainApi.Homeapi(token,
          new ConnectionListener<MainResponse<HomeResponse>>() {
           @Override
           public void onSuccess(ConnectionResponse<MainResponse<HomeResponse>> connectionResponse) {
            view.hideloadingviewBase();
            if (connectionResponse.data.success ) {
             AppConstant.homeResponse = connectionResponse.data.data;
             view.LoadWalletSystem(connectionResponse.data.data.getTopWallets());
             view.LoadWallet(connectionResponse.data.data.getTopChildrens());
             view.UpdateUserData(connectionResponse.data.data.getWallet());
            } else {
             view.showerrorlayoutTopsystem(true,connectionResponse.data.message);
             view.showerrorlayoutchilds(true,connectionResponse.data.message);
            }
           }
           @Override
           public void onFail(Throwable throwable) {
            view.hideloadingviewBase();
            //  view.showErrorMessageBase(coordinatorLayout,context,context.getString(R.string.tryagaing));
            view.showerrorlayoutTopsystem(true,context.getString(R.string.tryagaing));
            view.showerrorlayoutchilds(true,context.getString(R.string.tryagaing));
           }
          });
 }
}
