package com.razytech.razynet.gui.changepassword;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.razytech.razynet.R;
import com.razytech.razynet.Utils.dialogutil.AppDialog;
import com.razytech.razynet.Utils.dialogutil.DialogUtil;
import com.razytech.razynet.baseClasses.BaseFragment;
import com.razytech.razynet.databinding.ActivityAddWalletFragmentBinding;
import com.razytech.razynet.databinding.ActivityChangePasswordFragmentBinding;
import com.razytech.razynet.gui.addwallet.AddWalletFragment;
import com.razytech.razynet.gui.mainpage.MainpageActivity;
import com.razytech.razynet.gui.passwordconfirm.PasswordModelView;
import com.razytech.razynet.gui.porfile.ProfileFragment;

import static com.razytech.razynet.Utils.AppConstant.CHANGEPASSWORD_page;
import static com.razytech.razynet.Utils.AppConstant.PROFILE_page;
import static com.razytech.razynet.Utils.AppConstant.UPDATEPROFILE_page;

public class ChangePasswordFragment extends BaseFragment implements ChangePasswordView{


    View view ;
    ActivityChangePasswordFragmentBinding binding ;
    MyClickHandlers handlers ;
    ChangePasswordModelView passwordModelView ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.activity_change_password_fragment, container, false);
        view = binding.getRoot();
        hideKeyboard();
      handlers = new MyClickHandlers(getActivity());
        binding.setHandlers(handlers);
        inilizeVariables();
        return  view;
    }

    private void inilizeVariables() {
        passwordModelView =  new ChangePasswordModelView();
        passwordModelView.attachView(this);
    }

    @Override
    public void ChangePaswwordSuccessfully() {
        ((MainpageActivity)getActivity()).displayView(PROFILE_page);

    }


    public class MyClickHandlers {
        Context context;
        public MyClickHandlers(Context context) {
            this.context = context;
        }
        public void btn_submit(View view) {
            passwordModelView.vaildatedata(getActivity() , binding.coorchange , binding.oldpasswordET.getText().toString(), binding.newpasswordET.getText().toString(), binding.confpasswordET.getText().toString());
        }
    }


}
