package com.razytech.razynet.gui.remainingpage;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.Log;

import com.razytech.razynet.R;
import com.razytech.razynet.Utils.AppConstant;
import com.razytech.razynet.Utils.StaticMethods;
import com.razytech.razynet.Utils.Validator;
import com.razytech.razynet.baseClasses.BaseViewModel;
import com.razytech.razynet.data.beans.MultiPartNid;
import com.razytech.razynet.data.beans.RemainingResponse;
import com.razytech.razynet.data.beans.UploadNidResponse;
import com.razytech.razynet.data.network.ConnectionListener;
import com.razytech.razynet.data.network.ConnectionResponse;
import com.razytech.razynet.data.network.MainApi;
import com.razytech.razynet.data.network.MainApiBody;
import com.razytech.razynet.data.network.MainResponse;

import org.json.JSONException;

import java.io.File;

/**
 * Created by A.Noby on 6/19/2019.
 */
 class RemainingModelView  extends BaseViewModel<RemainingView> {


 void  loadingRemainingData(CoordinatorLayout coordinatorLayout, Context context  ){
  boolean internetAvailable = StaticMethods.isConnectingToInternet(context);
  if (!internetAvailable) {
   view.showNoNetworkConnectionBase(coordinatorLayout,context);
   return;
  }

   view.showloadingviewBase(context);
   String token  =  AppConstant.Tokenbar  +" "+ AppConstant.userResponse.getToken();
    MainApi.Remainingapi(token, new ConnectionListener<MainResponse<RemainingResponse>>() {
           @Override
           public void onSuccess(ConnectionResponse<MainResponse<RemainingResponse>> connectionResponse) {
            view.hideloadingviewBase();
            if (connectionResponse.data.success ) {
                Log.e("data.success",""+connectionResponse.data.data.getDays());
             view.SetRemainingData(connectionResponse.data.data, connectionResponse.data.message);
            } else {
             view.showErrorMessageBase(coordinatorLayout,context,connectionResponse.data.message);
           //  view.show_errorView(true ,  connectionResponse.data.message);
            }
           }

           @Override
           public void onFail(Throwable throwable) {
            view.hideloadingviewBase();
          //  view.showErrorMessageBase(coordinatorLayout,context,context.getString(R.string.tryagaing));
            //Log.e("error", throwable.toString());
           view.show_errorView(true , context.getString(R.string.tryagaing));

           }
          });
 }

    void  ValidateSendData( Context context
            ,CoordinatorLayout coordinatorLayout,String NIDNumber  , File file  ) {

        boolean internetAvailable = StaticMethods.isConnectingToInternet(context);
        if (!internetAvailable) {
            view.showNoNetworkConnectionBase(coordinatorLayout,context);
            return;
        }

        if (Validator.isTextEmpty(NIDNumber)) {
            view.showErrorMessageBase(coordinatorLayout,context, context.getString(R.string.emptynid));
            return;
        }

      else  if (file == null) {
            view.showErrorMessageBase(coordinatorLayout,context, context.getString(R.string.emptyImage));
            return;
        }

         UploadNIdData( coordinatorLayout,  context, NIDNumber  ,  file);
    }

    private void UploadNIdData(CoordinatorLayout coordinatorLayout, Context context, String nidNumber, File file) {

        MultiPartNid multiPartImage =null;
        try {
            multiPartImage =   MainApiBody.UploadNidBoby(nidNumber , file);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        view.showloadingviewBase(context);
        String token  =  AppConstant.Tokenbar  +" "+ AppConstant.userResponse.getToken();
        MainApi.UploadNidImageapi(token,multiPartImage.imagereq,multiPartImage.NIDNumberreq,
                new ConnectionListener<MainResponse<UploadNidResponse>>() {
                    @Override
                    public void onSuccess(ConnectionResponse<MainResponse<UploadNidResponse>> connectionResponse) {
                        view.hideloadingviewBase();
                        if (connectionResponse.data.success ) {
                            view.UpdateUserStatus(connectionResponse.data.message);
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
