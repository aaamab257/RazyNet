package com.razytech.razynet.gui.walletpage;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.Log;

import com.razytech.razynet.R;
import com.razytech.razynet.Utils.AppConstant;
import com.razytech.razynet.Utils.StaticMethods;
import com.razytech.razynet.baseClasses.BaseViewModel;
import com.razytech.razynet.data.beans.ChildResponse;
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
  class WalletModelView   extends BaseViewModel<WalletView> {




  void  loadingChildsData(CoordinatorLayout coordinatorLayout, Context context ){
    boolean internetAvailable = StaticMethods.isConnectingToInternet(context);
    if (!internetAvailable) {
      view.showNoNetworkConnectionBase(coordinatorLayout,context);
      return;
    }
      RequestBody requestBody = null;
      try {
          requestBody = MainApiBody.GetChildListBoby("");
      } catch (JSONException e) {
          e.printStackTrace();
      }
    view.showloadingviewBase(context);
    String token  =  AppConstant.Tokenbar  +" "+ AppConstant.userResponse.getToken();
    MainApi.ChildernListapi(token,requestBody,
            new ConnectionListener<MainResponse<List<ChildResponse>>>() {
              @Override
              public void onSuccess(ConnectionResponse<MainResponse<List<ChildResponse>>> connectionResponse) {
                view.hideloadingviewBase();
                if (connectionResponse.data.success ) {
                  view.LoadingchildData(connectionResponse.data.data);
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
