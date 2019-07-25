package com.razytech.razynet.gui.addwallet;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.razytech.razynet.R;
import com.razytech.razynet.Utils.AppConstant;
import com.razytech.razynet.baseClasses.BaseFragment;
import com.razytech.razynet.data.prefs.PrefUtils;
import com.razytech.razynet.databinding.ActivityAddWalletFragmentBinding;
import com.razytech.razynet.gui.mainpage.MainpageActivity;
import com.razytech.razynet.gui.passwordconfirm.PasswordModelView;
import com.razytech.razynet.gui.passwordconfirm.PasswordView;

import static com.razytech.razynet.Utils.AppConstant.HOME_page;


public class AddWalletFragment extends BaseFragment  implements  AddWalletView , PasswordView {


    View view ;
    ActivityAddWalletFragmentBinding binding ;
    AddWalletModelView modelView   ;
    MyClickHandlers handlers ;
    boolean PasswordVaild = false  ;
    PasswordModelView passwordModelView ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.activity_add_wallet_fragment, container, false);
        view = binding.getRoot();
        handlers = new MyClickHandlers(getActivity());
        binding.setHandlers(handlers);
        inilizeVariables();
        return  view;
    }
    private void inilizeVariables() {
        modelView =  new AddWalletModelView();
        modelView.attachView(this);
        passwordModelView =  new PasswordModelView(getActivity() , getActivity() ,this,binding.cooraddwallet   );
    }
    @Override
    public void SuccessData(String VerificationCode , double updatepoints) {
        ((MainpageActivity)getActivity()).UpdatePointsHandling(updatepoints + "");
        AppConstant.userResponse.setBalance(updatepoints);
        AppConstant.userResponse.setToken(AppConstant.userResponse.getToken());
        PrefUtils.saveUserinformation(getActivity(),AppConstant.userResponse,PrefUtils.User_Singin);
        ((MainpageActivity)getActivity()).displayView(HOME_page);
        AppConstant.refreshhome =  true ;
    }

    @Override
    public void VaildPassword() {
        PasswordVaild =  true ;
    }

    public class MyClickHandlers {
        Context context;
        public MyClickHandlers(Context context) {
            this.context = context;
        }
        public void btnAdd(View view) {
            if (PasswordVaild)
              modelView.vaildatedata(getActivity() ,  binding.cooraddwallet  ,  getActivity(),binding.createAccPhoneET.getText().toString());
            else
              passwordModelView.ShowAlertDialoug();
        }
    }
}
