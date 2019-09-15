package com.razytech.razynet.gui.loginpage;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.razytech.razynet.R;
import com.razytech.razynet.Utils.AppConstant;
import com.razytech.razynet.Utils.IntentUtiles;
import com.razytech.razynet.baseClasses.BaseActivity;
import com.razytech.razynet.data.beans.UserResponse;
import com.razytech.razynet.data.prefs.PrefUtils;
import com.razytech.razynet.databinding.ActivityLoginBinding;
import com.razytech.razynet.gui.forgetpassword.ForgetPasswordActivity;
import com.razytech.razynet.gui.mainpage.MainpageActivity;
import com.razytech.razynet.gui.register.RegisterActivity;
import com.razytech.razynet.gui.verificationcode.VerifyCodeActivity;

import javax.inject.Inject;

public class LoginActivity extends BaseActivity<ActivityLoginBinding ,  LoginModelView > implements LoginView{

    @Inject  LoginModelView modelView ;
    ActivityLoginBinding binding ;
    MyClickHandlers handlers ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideKeyboard();
        binding   = getViewDataBinding();
        handlers =  new MyClickHandlers(this);
        binding.setHandlers(handlers);
        inilizeVaribles();
    }

    private void inilizeVaribles() {
      modelView.attachView(this);
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }
    @Override
    public LoginModelView getViewModel() {
        return modelView;
    }
    @Override
    public int getBindingVariable() {
        return 0;
    }

    @Override
    public void SavingData(UserResponse userResponse) {
        AppConstant.userResponse =  userResponse  ;
        PrefUtils.saveUserinformation(LoginActivity.this,userResponse,PrefUtils.User_Singin);
        OpenMainPage();
    }

    @Override
    public void OpenMainPage() {
        IntentUtiles.openActivityInNewStack(LoginActivity.this, MainpageActivity.class);
    }

    public class MyClickHandlers {
        Context context;
        public MyClickHandlers(Context context) {
            this.context = context;
        }
        public void btnLogin(View view) {
            modelView.loginData(LoginActivity.this  ,  binding.coorlogin   , binding.createAccPhoneET.getText().toString() ,binding.createAccPasswordET.getText().toString());
        }
        public void btnforget(View view) {
            IntentUtiles.openActivity(LoginActivity.this, ForgetPasswordActivity.class);
        }
        public void btninvitation(View view) {
            IntentUtiles.openActivityInNewStack(LoginActivity.this, VerifyCodeActivity.class);
        }
    }
}
