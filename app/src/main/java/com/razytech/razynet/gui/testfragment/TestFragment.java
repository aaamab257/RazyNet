package com.razytech.razynet.gui.testfragment;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.razytech.razynet.Adapter.ChildAdpater;
import com.razytech.razynet.Adapter.TopWalletAdapter;
import com.razytech.razynet.R;
import com.razytech.razynet.Utils.AppConstant;
import com.razytech.razynet.Utils.IntentUtiles;
import com.razytech.razynet.Utils.StaticMethods;
import com.razytech.razynet.baseClasses.BaseFragment;
import com.razytech.razynet.data.beans.ChildResponse;
import com.razytech.razynet.data.beans.UserResponse;
import com.razytech.razynet.data.prefs.PrefUtils;
import com.razytech.razynet.databinding.ActivityTestFragmentBinding;
import com.razytech.razynet.gui.homepage.HomeModelView;
import com.razytech.razynet.gui.homepage.HomeView;
import com.razytech.razynet.gui.loginpage.LoginActivity;

import java.util.List;



public class TestFragment extends BaseFragment implements HomeView, ChildAdpater.ChildListener , TopWalletAdapter.TopWalletListener{


        View view ;
        ActivityTestFragmentBinding binding ;
        HomeModelView modelView   ;
        ChildAdpater adpater;
        TopWalletAdapter Topadpater;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.activity_test_fragment, container, false);
        view = binding.getRoot();
        inilizeVariables();
        return  view;
    }

    private void inilizeVariables() {

//        modelView =  new HomeModelView();
//        modelView.attachView(this);
//        modelView.loadingHomeData(binding.coorhome, getActivity());

        HomeModelView model = ViewModelProviders.of(getActivity()).get(HomeModelView.class);
        model.getUsers(binding.coorhome, getActivity()).observe(this, users -> {
            // update UI
        });
    }


    @Override
    public void LoadWalletSystem(List<ChildResponse> topsystem ) {
//        if (topsystem != null ) {
//        if(!topsystem.isEmpty()) {
//            showerrorlayoutTopsystem(false, "");
//            Log.e("topsystem", "" + topsystem.size());
//            AppConstant.topsystem = topsystem;
//            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
//            binding.recTopuser.setLayoutManager(layoutManager);
//            Topadpater = new TopWalletAdapter(getActivity(), topsystem, this,false , false);
//            binding.recTopuser.setAdapter(Topadpater);
//        }else
//            showerrorlayoutTopsystem(true, getActivity().getString(R.string.donothavechilds));
//    } else {
//        showerrorlayoutTopsystem(true, getActivity().getString(R.string.donothavechilds));
//    }
}

    @Override
    public void LoadWallet(List<ChildResponse> topchilds) {
        if (topchilds != null ) {
            if(!topchilds.isEmpty()) {
                showerrorlayoutchilds(false, "");

                AppConstant.topchilds = topchilds;
                binding.recTopwallet.setLayoutManager(new LinearLayoutManager(getActivity()));
                adpater = new ChildAdpater(getActivity(), topchilds, this, true, false);
                binding.recTopwallet.setAdapter(adpater);
            }else
                showerrorlayoutchilds(true, getActivity().getString(R.string.donothavechilds));
        } else {
            showerrorlayoutchilds(true, getActivity().getString(R.string.donothavechilds));
        }
    }

    @Override
    public void UpdateUserData(UserResponse userResponse) {
//        userResponse.setToken(AppConstant.userResponse.getToken());
//        AppConstant.userResponse =  userResponse  ;
//
//        PrefUtils.saveUserinformation(getActivity(),userResponse,PrefUtils.User_Singin);
    }

    @Override
    public void showerrorlayoutTopsystem(boolean show ,String errortxt) {
//        if (show){
//            binding.errorwalletsys.setViewerror(show);
//            binding.errorwalletsys.setErrortxt(errortxt);
//            binding.errorwalletsys.btnTryAgain.setVisibility(View.GONE);
//        }else {
//            binding.errorwalletsys.setViewerror(show);
//        }
    }

    @Override
    public void showerrorlayoutchilds(boolean show ,String errortxt) {
        if (show){
            binding.errorwallet.setViewerror(show);
            binding.errorwallet.setErrortxt(errortxt);
            binding.errorwallet.btnTryAgain.setVisibility(View.GONE);
        }else {
            binding.errorwallet.setViewerror(show);
        }
    }

    @Override
    public void logout() {
        StaticMethods.ClearChash();
        PrefUtils.SignOut_User(getActivity());
        AppConstant.userResponse = null ;
        IntentUtiles.openActivityInNewStack(getActivity(), LoginActivity.class);
    }



    @Override
    public void onChildClicked(ChildResponse post) {
//        Bundle bundle = new Bundle();
//        bundle.putString(ChildId,post.getWalletId());
//        bundle.putString(ChildName,post.getName());
//        bundle.putString(ChildChilds,post.getChildCounts()+"");
//        bundle.putString(ChildImage,post.getImageUrl());
//        bundle.putBoolean(ChildMoved,post.isMoved());
//        ((MainpageActivity)getActivity()).setBundlevalue(bundle , CHILDDETAILS_page);
    }

    @Override
    public void onWalletClicked(ChildResponse post) {

    }

    public class MyClickHandlers {
        Context context;
        public MyClickHandlers(Context context) {
            this.context = context;
        }
        public void Radiomale(View view) {
        }
    }


}
