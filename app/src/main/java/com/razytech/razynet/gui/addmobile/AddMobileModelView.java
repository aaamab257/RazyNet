package com.razytech.razynet.gui.addmobile;

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
import com.razytech.razynet.Utils.Validator;
import com.razytech.razynet.baseClasses.BaseViewModel;
import com.razytech.razynet.gui.passwordconfirm.PasswordView;

/**
 * Created by A.Noby on 10/15/2019.
 */
public class AddMobileModelView  extends BaseViewModel<AddMobileView> {


    Context context ;
    AddMobileView view ;
    CoordinatorLayout coordinatorLayout  ;
    Activity activity ;
    public  AddMobileModelView (Context context ,  Activity activity,  AddMobileView view ,  CoordinatorLayout  coordinatorLayout){
        this.context =  context ;
        this.view = view  ;
        this.coordinatorLayout =  coordinatorLayout  ;
        this.activity =  activity ;
    }

    public  void ShowAlertDialoug(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = activity.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.mobilelayout, null);
        dialogBuilder.setView(dialogView);
        AlertDialog alertDialog = dialogBuilder.create();

        Button btnverify = (Button) dialogView.findViewById(R.id.btn_verify);
        EditText phonenumber = (EditText) dialogView.findViewById(R.id.phone_ET);
        EditText confirmphonenumber = (EditText) dialogView.findViewById(R.id.phone_ET_confirm);
        alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;
        btnverify.setOnClickListener((View) ->{
              ValidatePhoneNumber(phonenumber.getText().toString() ,  confirmphonenumber.getText().toString());
             alertDialog.dismiss();
        });

        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.show();

    }

    private  void  ValidatePhoneNumber(String PhoneNumber  , String ConfirmPhone){
        if (Validator.isTextEmpty(PhoneNumber)) {
            view.showErrorMessageBase(coordinatorLayout,context, context.getString(R.string.emptynumberphone));
            return;
        }
        else  if (!Validator.validMobileNumber(PhoneNumber)) {
            view.showErrorMessageBase(coordinatorLayout,context, context.getString(R.string.invaildnumberphone));
            return;
        }
        else  if (Validator.isTextEmpty(PhoneNumber)) {
            view.showErrorMessageBase(coordinatorLayout,context, context.getString(R.string.emptyconfnumberphone));
            return;
        }

        else if (!Validator.isMatch(PhoneNumber,ConfirmPhone)){
            view.showErrorMessageBase(coordinatorLayout,context,context.getString(R.string.Phonedonotmatch));
            return;
        }
        view.VaildMobile(PhoneNumber);

    }

}
