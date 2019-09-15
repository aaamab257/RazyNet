package com.razytech.razynet.gui.homepage;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
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
public class HomeModelView extends BaseViewModel<HomeView> {

    private MutableLiveData<MainResponse<HomeResponse>> users;
    public LiveData<MainResponse<HomeResponse>> getUsers(CoordinatorLayout coordinatorLayout, Context context) {
        if (users == null) {
            users = new MutableLiveData<MainResponse<HomeResponse>>();
            loadingHomeData(coordinatorLayout , context);
        }
        return users;
    }



    public void  loadingHomeData(CoordinatorLayout coordinatorLayout, Context context ){
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
            if(connectionResponse.data.statusCode == 401) {
                view.logout();
            }else {
                if (connectionResponse.data.success) {
                    AppConstant.homeResponse = connectionResponse.data.data;
                    AppConstant.refreshhome = false;
                    view.LoadWalletSystem(connectionResponse.data.data.getTopWallets());
                    view.LoadWallet(connectionResponse.data.data.getTopChildrens());
                    view.UpdateUserData(connectionResponse.data.data.getWallet());
                } else {
                    view.showerrorlayoutTopsystem(true, connectionResponse.data.message);
                    view.showerrorlayoutchilds(true, connectionResponse.data.message);
                }
            }
           }
           @Override
           public void onFail(Throwable throwable) {
            view.hideloadingviewBase();
               if(throwable.getMessage().contains("401")){
                   view.logout();
               }
            //  view.showErrorMessageBase(coordinatorLayout,context,context.getString(R.string.tryagaing));
            view.showerrorlayoutTopsystem(true,context.getString(R.string.tryagaing));
            view.showerrorlayoutchilds(true,context.getString(R.string.tryagaing));
           }
          });
 }
}
