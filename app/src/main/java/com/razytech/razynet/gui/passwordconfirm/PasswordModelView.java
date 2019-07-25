package com.razytech.razynet.gui.passwordconfirm;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.CoordinatorLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.razytech.razynet.R;
import com.razytech.razynet.Utils.AppConstant;
import com.razytech.razynet.Utils.StaticMethods;
import com.razytech.razynet.Utils.Validator;
import com.razytech.razynet.data.beans.UserResponse;
import com.razytech.razynet.data.network.ConnectionListener;
import com.razytech.razynet.data.network.ConnectionResponse;
import com.razytech.razynet.data.network.MainApi;
import com.razytech.razynet.data.network.MainApiBody;
import com.razytech.razynet.data.network.MainResponse;

import org.json.JSONException;

import okhttp3.RequestBody;

/**
 * Created by A.Noby on 7/22/2019.
 */
public class PasswordModelView {

    Context context ;
    PasswordView view ;
    CoordinatorLayout coordinatorLayout  ;
    Activity activity ;
    public  PasswordModelView (Context context ,  Activity activity,  PasswordView view ,  CoordinatorLayout  coordinatorLayout){
        this.context =  context ;
        this.view = view  ;
        this.coordinatorLayout =  coordinatorLayout  ;
        this.activity =  activity ;
    }

    public  void ShowAlertDialoug(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = activity.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.passwordlayout, null);
        dialogBuilder.setView(dialogView);
        AlertDialog alertDialog = dialogBuilder.create();

        Button btnverify = (Button) dialogView.findViewById(R.id.btn_verify);
        EditText password = (EditText) dialogView.findViewById(R.id.password_ET);
        alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;
        btnverify.setOnClickListener((View) ->{
            PasswordData(password.getText().toString());
            alertDialog.dismiss();
        });

        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.show();

    }

    void  PasswordData(  String password ){
        boolean internetAvailable = StaticMethods.isConnectingToInternet(context);
        if (!internetAvailable) {
            view.showNoNetworkConnectionBase(coordinatorLayout,context);
            return;
        }
        else  if (Validator.isTextEmpty(password)) {
            view.showErrorMessageBase(coordinatorLayout,context, context.getString(R.string.emptyPassword));
            return;
        }

        RequestBody requestBody = null;
        try {
            requestBody = MainApiBody.PasswordBoby(password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        view.showloadingviewBase(context);
        String token  =  AppConstant.Tokenbar  +" "+ AppConstant.userResponse.getToken();

        MainApi.ConfirmPasswordapi(token , requestBody, new ConnectionListener<MainResponse<UserResponse>>() {
                    @Override
                    public void onSuccess(ConnectionResponse<MainResponse<UserResponse>> connectionResponse) {
                        view.hideloadingviewBase();
                        if (connectionResponse.data.success ) {
                            view.showSuccessMessageBase(coordinatorLayout,context,connectionResponse.data.message);
                            view.VaildPassword( );
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
