package com.razytech.razynet.gui.maintransaction.transfer;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.Log;

import com.razytech.razynet.R;
import com.razytech.razynet.Utils.AppConstant;
import com.razytech.razynet.Utils.StaticMethods;
import com.razytech.razynet.Utils.Validator;
import com.razytech.razynet.baseClasses.BaseViewModel;
import com.razytech.razynet.data.beans.ChildResponse;
import com.razytech.razynet.data.beans.RedeemResponse;
import com.razytech.razynet.data.beans.TransferResponse;
import com.razytech.razynet.data.network.ConnectionListener;
import com.razytech.razynet.data.network.ConnectionResponse;
import com.razytech.razynet.data.network.MainApi;
import com.razytech.razynet.data.network.MainApiBody;
import com.razytech.razynet.data.network.MainResponse;
import com.razytech.razynet.gui.homepage.HomeFragment;
import com.razytech.razynet.gui.mainpage.MainpageActivity;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import okhttp3.RequestBody;

import static com.razytech.razynet.Utils.AppConstant.HOME_page;
import static com.razytech.razynet.Utils.AppConstant.TRANSFERCONFIRMFINAL_page;
import static com.razytech.razynet.Utils.AppConstant.TRANSFERCONFIRM_page;
import static com.razytech.razynet.Utils.AppConstant.TRANSFERPOINTS_page;
import static com.razytech.razynet.Utils.AppConstant.TRANSFERWALLET_page;

/**
 * Created by A.Noby on 6/11/2019.
 */
 class TransferModelView  extends BaseViewModel<TransferView> {

     String numberPhone =  ""  , points =  "" ;


     void setbtnNextWallet(CoordinatorLayout coordinatorLayout  , Context context  , String phoneNumber){

        if (Validator.isTextEmpty(phoneNumber)) {
       view.showErrorMessageBase(context, context.getString(R.string.emptychooisewallet));
       return;
      }
      else  if (!Validator.validMobileNumber(phoneNumber)) {
       view.showErrorMessageBase(context, context.getString(R.string.invaildnumberphone));
       return;
      }
         numberPhone  = phoneNumber  ;
         Log.e("numberPhone",numberPhone);
         view.SetStepsHandlingView(TRANSFERPOINTS_page);
     }

 void setbtnPoints(CoordinatorLayout coordinatorLayout  , Context context  , String Points ,  boolean isNext){
  if (isNext) {
   if (Validator.isTextEmpty(Points)) {
    view.showErrorMessageBase(context, context.getString(R.string.emptypoints));
    return;
   }else if (Double.parseDouble(Points) == 0) {
       view.showErrorMessageBase(context, context.getString(R.string.donothaveenoughpoints));
       return;
   } else if (Double.parseDouble(Points) > AppConstant.userResponse.getBalance()) {
    view.showErrorMessageBase(context, context.getString(R.string.donothaveenoughpoints));
    return;
   }
      points  = Points  ;
      view.SetPointValue(points);
      view.SetStepsHandlingView(TRANSFERCONFIRM_page);
  }else {
   view.SetStepsHandlingView(TRANSFERWALLET_page);
  }
 }
 void setbtnConfirm(CoordinatorLayout coordinatorLayout  , Context context   ,
                    boolean isNext){
  boolean internetAvailable = StaticMethods.isConnectingToInternet(context);
     if (!internetAvailable) {
         view.showNoNetworkConnectionBase(coordinatorLayout,context);
         return;
     }
     if (isNext) {
       SetTransferPoints(coordinatorLayout, context , numberPhone , points);
     }else {
      view.SetStepsHandlingView(TRANSFERPOINTS_page);
         }
 }

 void setbtnHome(CoordinatorLayout coordinatorLayout  , Context context  ){
         AppConstant.refreshhome =  true ;
        ((MainpageActivity)context).displayView(HOME_page);
    }
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
                            AppConstant.childResponses =  connectionResponse.data.data ;
                            view.LoadingchildData(connectionResponse.data.data);
                        } else {
                            view.show_Nochild(true,connectionResponse.data.message);
                        }
                    }
                    @Override
                    public void onFail(Throwable throwable) {
                        view.hideloadingviewBase();
                        if(throwable.getMessage().contains("401")){
                            ((MainpageActivity)context).logout();
                        }
                      //  view.showErrorMessageBase(coordinatorLayout,context,context.getString(R.string.tryagaing));
                        view.show_errorView(true,context.getString(R.string.tryagaing));
                    }
                });
    }


    void  GetChildsData(CoordinatorLayout coordinatorLayout, Context context ,  String PhoneNumber){
        boolean internetAvailable = StaticMethods.isConnectingToInternet(context);
        if (!internetAvailable) {
            view.showNoNetworkConnectionBase(coordinatorLayout,context);
            return;
        }
          if (Validator.isTextEmpty(PhoneNumber)) {
            view.showErrorMessageBase(coordinatorLayout,context, context.getString(R.string.emptynumberphone));
            return;
        }
        else  if (!Validator.validMobileNumber(PhoneNumber)) {
            view.showErrorMessageBase(coordinatorLayout,context, context.getString(R.string.invaildnumberphone));
            return;
        }

        RequestBody requestBody = null;
        try {
            requestBody = MainApiBody.GetChildBoby(PhoneNumber);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        view.showloadingviewBase(context);
        String token  =  AppConstant.Tokenbar  +" "+ AppConstant.userResponse.getToken();
        MainApi.GetChildapi(token,requestBody,
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
                        if(throwable.getMessage().contains("401")){
                            ((MainpageActivity)context).logout();
                        }
                        view.showErrorMessageBase(coordinatorLayout,context,context.getString(R.string.tryagaing));
                        Log.e("error", throwable.toString());
                    }
                });
    }



    void  SetTransferPoints(CoordinatorLayout coordinatorLayout, Context context ,  String PhoneNumber,  String Points){

        RequestBody requestBody = null;
        try {
            requestBody = MainApiBody.TransferBoby(PhoneNumber , Points);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        view.showloadingviewBase(context);
        String token  =  AppConstant.Tokenbar  +" "+ AppConstant.userResponse.getToken();
        MainApi.Transferapi(token,requestBody,
                new ConnectionListener<MainResponse<TransferResponse>>() {
                    @Override
                    public void onSuccess(ConnectionResponse<MainResponse<TransferResponse>> connectionResponse) {
                        view.hideloadingviewBase();
                        if (connectionResponse.data.success ) {
                            view.UpdatePoints(connectionResponse.data.data.getUpdatedBalance());
                            view.SetPointValue(points);
                            view.SetStepsHandlingView(TRANSFERCONFIRMFINAL_page);
                        } else {
                            view.showErrorMessageBase(context,connectionResponse.data.message);
                        }
                    }
                    @Override
                    public void onFail(Throwable throwable) {
                        view.hideloadingviewBase();
                        if(throwable.getMessage().contains("401")){
                            ((MainpageActivity)context).logout();
                        }
                        view.showErrorMessageBase(coordinatorLayout,context,context.getString(R.string.tryagaing));
                    }
                });
    }



}
