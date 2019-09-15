package com.razytech.razynet.gui.verificationcode;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.razytech.razynet.R;
import com.razytech.razynet.Utils.AppConstant;
import com.razytech.razynet.Utils.IntentUtiles;
import com.razytech.razynet.baseClasses.BaseActivity;
import com.razytech.razynet.data.beans.VerifyCodeResponse;
import com.razytech.razynet.databinding.ActivityVerifyCodeBinding;
import com.razytech.razynet.gui.loginpage.LoginActivity;
import com.razytech.razynet.gui.mainpage.MainpageActivity;
import com.razytech.razynet.gui.register.RegisterActivity;
import com.razytech.razynet.gui.splash.SplashActivity;

import javax.inject.Inject;

public class VerifyCodeActivity extends BaseActivity<ActivityVerifyCodeBinding  , VerifyCodeViewModel>  implements  VerifyCodeView {


   @Inject
   VerifyCodeViewModel viewModel  ;
    MyClickHandlers handlers   ;
    ActivityVerifyCodeBinding  binding  ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding   = getViewDataBinding();
       handlers =  new MyClickHandlers(this);
       binding.setHandlers(handlers);
       inilizeVaribles();
    }

    private void inilizeVaribles() {
        viewModel.attachView(this);

    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_verify_code;
    }
    @Override
    public VerifyCodeViewModel getViewModel() {
        return viewModel;
    }
    @Override
    public int getBindingVariable() {
        return 0;
    }

    @Override
    public void OpenRegister() {
        Bundle bundle  =  new Bundle();
        bundle.putString(AppConstant.TokenKey,"");
        bundle.putString(AppConstant.phoneKey,"");
        IntentUtiles.openActivityWithBundleInNewStack(VerifyCodeActivity.this, RegisterActivity.class ,  bundle);
    }

    @Override
    public void SaveVerifyData(VerifyCodeResponse response) {
        Bundle bundle  =  new Bundle();
        bundle.putString(AppConstant.TokenKey,response.getToken());
        bundle.putString(AppConstant.phoneKey,response.getPhone());
        IntentUtiles.openActivityWithBundleInNewStack(VerifyCodeActivity.this, RegisterActivity.class ,  bundle);
    }

    public class MyClickHandlers {
        Context context;
        public MyClickHandlers(Context context) {
            this.context = context;
        }
        public void btnEnterCode(View view) {
           viewModel.vaildatedata(VerifyCodeActivity.this  ,  binding.coorverify  ,VerifyCodeActivity.this  , binding.loginCodeET.getText().toString());
        }
        public void btnloginpage(View view) {
            IntentUtiles.openActivityInNewStack(VerifyCodeActivity.this, LoginActivity.class );
        }
    }
}
