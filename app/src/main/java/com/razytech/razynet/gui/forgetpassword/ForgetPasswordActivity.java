package com.razytech.razynet.gui.forgetpassword;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.razytech.razynet.R;
import com.razytech.razynet.baseClasses.BaseActivity;
import com.razytech.razynet.databinding.ActivityForgetPasswordBinding;

import javax.inject.Inject;

public class ForgetPasswordActivity extends BaseActivity<ActivityForgetPasswordBinding , ForgetPasswordModelView> implements ForgetPasswordView {

    ActivityForgetPasswordBinding binding ;
    @Inject
    ForgetPasswordModelView modelView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =  getViewDataBinding() ;
        inilizeVaribles();
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_forget_password;
    }
    @Override
    public ForgetPasswordModelView getViewModel() {
        return modelView;
    }
    @Override
    public int getBindingVariable() {
        return 0;
    }

    private void inilizeVaribles() {
        modelView.attachView(this);
    }

}
