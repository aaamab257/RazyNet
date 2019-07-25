package com.razytech.razynet.gui.movepage;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.Log;

import com.razytech.razynet.R;
import com.razytech.razynet.Utils.AppConstant;
import com.razytech.razynet.Utils.StaticMethods;
import com.razytech.razynet.Utils.Validator;
import com.razytech.razynet.baseClasses.BaseViewModel;
import com.razytech.razynet.data.beans.ChildResponse;
import com.razytech.razynet.data.beans.HomeResponse;
import com.razytech.razynet.data.beans.TransferResponse;
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
 * Created by A.Noby on 7/8/2019.
 */
 class MovepageViewModel  extends BaseViewModel<MovepageView> {

 void  GetChildsData(CoordinatorLayout coordinatorLayout, Context context , String ChildId){
  boolean internetAvailable = StaticMethods.isConnectingToInternet(context);
  if (!internetAvailable) {
   view.showNoNetworkConnectionBase(coordinatorLayout,context);
   return;
  }

  RequestBody requestBody = null;
  try {
   requestBody = MainApiBody.GetAvailableBoby(ChildId);
  } catch (JSONException e) {
   e.printStackTrace();
  }
  view.showloadingviewBase(context);
  String token  =  AppConstant.Tokenbar  +" "+ AppConstant.userResponse.getToken();
  MainApi.AvailableChildapi(token,requestBody,
          new ConnectionListener<MainResponse<List<ChildResponse>>>() {
           @Override
           public void onSuccess(ConnectionResponse<MainResponse<List<ChildResponse>>> connectionResponse) {
            view.hideloadingviewBase();
            if (connectionResponse.data.success ) {
                if (connectionResponse.data.data != null) {
                    if (!connectionResponse.data.data.isEmpty()) {
                        view.LoadingchildData(connectionResponse.data.data);
                    }else
                        view.show_errorView(true , connectionResponse.data.message );
                }else
                    view.show_errorView(true , connectionResponse.data.message );
            } else {
            // view.showErrorMessageBase(coordinatorLayout,context,connectionResponse.data.message);
              view.show_errorView(true , connectionResponse.data.message );
            }
           }
           @Override
           public void onFail(Throwable throwable) {
            view.hideloadingviewBase();
           // view.showErrorMessageBase(coordinatorLayout,context,context.getString(R.string.tryagaing));
               view.show_errorView(true , context.getString(R.string.tryagaing));
            Log.e("error", throwable.toString());
           }
          });
 }


    void  SetMoveAction(CoordinatorLayout coordinatorLayout, Context context  ,  ChildResponse newWallet  , String CurrentWallet){
        boolean internetAvailable = StaticMethods.isConnectingToInternet(context);
        if (!internetAvailable) {
            view.showNoNetworkConnectionBase(coordinatorLayout,context);
            return;
        }

       else  if (newWallet == null) {
            view.showErrorMessageBase(context, context.getString(R.string.emptychooisewallet));
            return;
        }

        RequestBody requestBody = null;
        try {
            requestBody = MainApiBody.MoveBoby(CurrentWallet ,  newWallet.getWalletId());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        view.showloadingviewBase(context);
        String token  =  AppConstant.Tokenbar  +" "+ AppConstant.userResponse.getToken();

        MainApi.Moveapi(token, requestBody,
                new ConnectionListener<MainResponse<TransferResponse>>() {
                    @Override
                    public void onSuccess(ConnectionResponse<MainResponse<TransferResponse>> connectionResponse) {
                        view.hideloadingviewBase();
                        if (connectionResponse.data.success ) {
                           view.showSuccessMessageBase(context ,connectionResponse.data.message  );
                           view.After_ChildMove(connectionResponse.data.data.getUpdatedBalance());
                        } else {
                            view.showErrorMessageBase(coordinatorLayout,context,connectionResponse.data.message);
                        }
                    }
                    @Override
                    public void onFail(Throwable throwable) {
                        view.hideloadingviewBase();
                        view.showErrorMessageBase(coordinatorLayout,context,context.getString(R.string.tryagaing));
                    }
                });
    }

}
