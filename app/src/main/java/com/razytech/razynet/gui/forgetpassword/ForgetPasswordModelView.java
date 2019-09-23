package com.razytech.razynet.gui.forgetpassword;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.CoordinatorLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.razytech.razynet.R;
import com.razytech.razynet.Utils.StaticMethods;
import com.razytech.razynet.Utils.Validator;
import com.razytech.razynet.baseClasses.BaseView;
import com.razytech.razynet.baseClasses.BaseViewModel;
import com.razytech.razynet.data.beans.VerifyCodeResponse;
import com.razytech.razynet.data.network.ConnectionListener;
import com.razytech.razynet.data.network.ConnectionResponse;
import com.razytech.razynet.data.network.MainApi;
import com.razytech.razynet.data.network.MainApiBody;
import com.razytech.razynet.data.network.MainResponse;
import com.razytech.razynet.gui.mainpage.MainpageActivity;

import org.json.JSONException;

import okhttp3.RequestBody;

/**
 * Created by A.Noby on 9/9/2019.
 */
 class ForgetPasswordModelView  extends BaseViewModel<ForgetPasswordView> {

    void vaildatedata(Context context, CoordinatorLayout coordinatorLayout , Activity activity , String Invitationcode) {

        boolean internetAvailable = StaticMethods.isConnectingToInternet(context);
        if (!internetAvailable) {
            view.showNoNetworkConnectionBase(coordinatorLayout,context);
            return;
        }
        if (Validator.isTextEmpty(Invitationcode)) {
            view.showErrorMessageBase(context, context.getString(R.string.emptyinvcode));
            return;
        }

        loadData(context,coordinatorLayout,activity,Invitationcode);
    }

    void loadData (Context context ,CoordinatorLayout coordinatorLayout,Activity activity  , String Code){


        RequestBody requestBody = null;
        try {
            requestBody = MainApiBody.VerifyCodeBoby(Code);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        view.showloadingviewBase(context);

        MainApi.VerifyCodeapi(requestBody, new ConnectionListener<MainResponse<VerifyCodeResponse>>() {
            @Override
            public void onSuccess(ConnectionResponse<MainResponse<VerifyCodeResponse>> connectionResponse) {
                view.hideloadingviewBase();
                if (connectionResponse.data.success ) {
//                    view.SaveVerifyData(connectionResponse.data.data);
//                } else {
//                    ShowAlertDialoug(context,activity ,connectionResponse.data.message );
                }
            }
            @Override
            public void onFail(Throwable throwable) {
                view.hideloadingviewBase();
                if(throwable.getMessage().contains("401")){
                    ((MainpageActivity)context).logout();
                }
                view.showErrorMessageBase(coordinatorLayout,context,context.getString(R.string.tryagaing));
                Log.e("errorstatement",""+throwable.getMessage());
            }
        });
    }

    void ShowAlertDialoug(Context context , Activity activity  , String message){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = activity.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.verify_alert, null);
        dialogBuilder.setView(dialogView);
        AlertDialog alertDialog = dialogBuilder.create();

        Button btncancel = (Button) dialogView.findViewById(R.id.btn_cancel);
        ImageView imgcancel = (ImageView) dialogView.findViewById(R.id.imgclose);
        TextView txtmessage = (TextView) dialogView.findViewById(R.id.txtmessage);
        txtmessage.setText(message);
        alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;
        btncancel.setOnClickListener((View) ->{
            alertDialog.dismiss();
        });
        imgcancel.setOnClickListener((View) ->{
            alertDialog.dismiss();
        });
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.show();

    }

}
