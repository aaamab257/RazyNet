package com.razytech.razynet.gui.porfile;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.razytech.razynet.R;
import com.razytech.razynet.Utils.AppConstant;
import com.razytech.razynet.Utils.IntentUtiles;
import com.razytech.razynet.Utils.StaticMethods;
import com.razytech.razynet.Utils.dialogutil.AppDialog;
import com.razytech.razynet.Utils.dialogutil.DialogUtil;
import com.razytech.razynet.Utils.takeimage.TakeImageUtiles;
import com.razytech.razynet.baseClasses.BaseFragment;
import com.razytech.razynet.data.prefs.PrefUtils;
import com.razytech.razynet.databinding.ActivityProfileFragmentBinding;
import com.razytech.razynet.gui.loginpage.LoginActivity;
import com.razytech.razynet.gui.mainpage.MainpageActivity;

import java.io.File;

import static com.razytech.razynet.Utils.AppConstant.CHANGEPASSWORD_page;
import static com.razytech.razynet.Utils.AppConstant.UPDATEPROFILE_page;

public class ProfileFragment extends BaseFragment implements   ProfileView {

    View view ;
    ActivityProfileFragmentBinding binding ;
    ProfileModelView modelView   ;
    MyClickHandlers handlers  ;
    boolean isFILE = false ,isPhoto = false;
    TakeImageUtiles imageUtiles;
    String image = "";
    File filePath =null;
    byte[] array =null  ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.activity_profile_fragment, container, false);
        view = binding.getRoot();
        handlers =  new MyClickHandlers(getActivity());
        binding.setHandlers(handlers);

        inilizeVariables();
        return  view;
    }

    private void inilizeVariables() {
        ((MainpageActivity)getActivity()).setViewHandling(""  ,""  , true , false );
        modelView =  new ProfileModelView();
        modelView.attachView(this);

        filldata();
    }

    private void filldata() {
        binding.setUser(AppConstant.userResponse);
         StaticMethods.LoadImage(getActivity(), binding.createAccImg,AppConstant.userResponse.getIdImageUrl(),null);
        ((MainpageActivity)getActivity()).UpdatePointsHandling(AppConstant.userResponse.getBalance()+"");
    }



    public class MyClickHandlers {
        Context context;
        public MyClickHandlers(Context context) {
            this.context = context;
        }


        public void btn_UpdateProfile(View view) {
            ((MainpageActivity)getActivity()).displayView(UPDATEPROFILE_page);
        }

        public void btn_logout(View view) {
            DialogUtil.showTwoActionDialog(getActivity(),R.string.logout,R.string.areyousureexit,
                    DialogUtil.ImageInfoIcon,new AppDialog.RightButtonClickListener() {
                        @Override
                        public void onRightButtonClick(android.support.v7.app.AlertDialog alertDialog) {
                            logout();
                        }
                    }, null);
        }
        public void btn_changepassw(View view) {
            ((MainpageActivity)getActivity()).displayView(CHANGEPASSWORD_page);
        }
    }

    private  void logout (){
        StaticMethods.ClearChash();
        PrefUtils.SignOut_User(getActivity());
        AppConstant.userResponse = null ;
        IntentUtiles.openActivityInNewStack(getActivity(), LoginActivity.class);
    }

}
