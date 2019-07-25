package com.razytech.razynet.gui.movepage;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.razytech.razynet.Adapter.ChildAdpater;
import com.razytech.razynet.R;
import com.razytech.razynet.Utils.AppConstant;
import com.razytech.razynet.Utils.StaticMethods;
import com.razytech.razynet.baseClasses.BaseFragment;
import com.razytech.razynet.data.beans.ChildResponse;
import com.razytech.razynet.data.prefs.PrefUtils;
import com.razytech.razynet.databinding.ActivityMovepageFragmentBinding;
import com.razytech.razynet.gui.addwallet.AddWalletFragment;
import com.razytech.razynet.gui.mainpage.MainpageActivity;
import com.razytech.razynet.gui.passwordconfirm.PasswordModelView;
import com.razytech.razynet.gui.passwordconfirm.PasswordView;


import java.util.ArrayList;
import java.util.List;

import static com.razytech.razynet.Utils.AppConstant.HOME_page;
import static com.razytech.razynet.Utils.AppConstant.UPDATEPROFILE_page;

public class MovepageFragment extends BaseFragment implements MovepageView ,  ChildAdpater.ChildListener  , PasswordView  {

    ActivityMovepageFragmentBinding binding ;
    MovepageViewModel viewModel  ;
    View view ;
    String ChildName =  "" ,ChildId =  "" , ChildImage =  "" , ChildNumber =  "";
    ChildAdpater adpater ;
    MyClickHandlers handlers  ;
    ChildResponse childResponse =  null  ;
    PasswordModelView passwordModelView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.activity_movepage_fragment, container, false);
        //  setUserVisibleHint(false);
        view = binding.getRoot();
        handlers = new MyClickHandlers(getActivity());
        binding.setHandlers(handlers);
        inilizeVariables();
        return  view;
    }

    private void inilizeVariables() {
        ((MainpageActivity)getActivity()).setViewHandling(""  ,""  ,true , false );
        viewModel  =  new MovepageViewModel() ;
        viewModel.attachView(this);
        passwordModelView =  new PasswordModelView(getActivity() , getActivity() ,this,binding.coormove );
        ChildName= getArguments().getString(AppConstant.ChildName);
        ChildId= getArguments().getString(AppConstant.ChildId);
        ChildNumber= getArguments().getString(AppConstant.ChildChilds);
        ChildImage= getArguments().getString(AppConstant.ChildImage);
        viewModel.GetChildsData(binding.coormove , getActivity()  , ChildId);
        Log.e("ChildId" , ChildId);
        filldata();
    }

    private void filldata() {
        binding.setMovename(ChildName);
        binding.setWallet(ChildNumber);
        StaticMethods.LoadImage(getActivity(), binding.imgMove,ChildImage,null);
    }

    @Override
    public void LoadingchildData(List<ChildResponse> childResponses) {
                childResponse =  null ;
                show_errorView(false, "");
                binding.recChild.setLayoutManager(new LinearLayoutManager(getActivity()));
                adpater = new ChildAdpater(getActivity(), childResponses, this, true);
                binding.recChild.setAdapter(adpater);
    }

    @Override
    public void show_errorView(boolean Isshow, String error) {
        if (Isshow){
            binding.errorLayout.setViewerror(Isshow);
            binding.errorLayout.setErrortxt(error);
            binding.errorLayout.btnTryAgain.setOnClickListener((View)->{
                viewModel.GetChildsData(binding.coormove , getActivity()  , ChildId);
            });
        }else {
            binding.errorLayout.setViewerror(Isshow);
        }
    }

    @Override
    public void After_ChildMove(double updatePoints) {
        AppConstant.refreshhome =  true ;
        ((MainpageActivity)getActivity()).UpdatePointsHandling(updatePoints+"");
        AppConstant.userResponse.setBalance(updatePoints);
        AppConstant.userResponse.setToken(AppConstant.userResponse.getToken());
        PrefUtils.saveUserinformation(getActivity(),AppConstant.userResponse,PrefUtils.User_Singin);
        ((MainpageActivity)getActivity()).displayView(HOME_page);
    }

    @Override
    public void onChildClicked(ChildResponse post) {
        childResponse = post ;
    }

    @Override
    public void VaildPassword() {
        viewModel.SetMoveAction(binding.coormove ,  getActivity() , childResponse ,  ChildId);
    }

    public class MyClickHandlers {
        Context context;
        public MyClickHandlers(Context context) {
            this.context = context;
        }

        public void btn_move(View view) {
       //  viewModel.SetMoveAction(binding.coormove ,  getActivity() , childResponse ,  ChildId);
            passwordModelView.ShowAlertDialoug();
        }
    }
}
