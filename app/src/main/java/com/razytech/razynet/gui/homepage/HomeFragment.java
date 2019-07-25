package com.razytech.razynet.gui.homepage;

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
import com.razytech.razynet.Adapter.TopWalletAdapter;
import com.razytech.razynet.R;
import com.razytech.razynet.Utils.AppConstant;
import com.razytech.razynet.baseClasses.BaseFragment;
import com.razytech.razynet.data.beans.ChildResponse;
import com.razytech.razynet.data.beans.UserResponse;
import com.razytech.razynet.data.prefs.PrefUtils;
import com.razytech.razynet.databinding.ActivityHomeFragmentBinding;
import com.razytech.razynet.gui.mainpage.MainpageActivity;
import com.razytech.razynet.gui.register.RegisterActivity;

import java.util.List;

import static com.razytech.razynet.Utils.AppConstant.CHILDDETAILS_page;
import static com.razytech.razynet.Utils.AppConstant.ChildChilds;
import static com.razytech.razynet.Utils.AppConstant.ChildId;
import static com.razytech.razynet.Utils.AppConstant.ChildImage;
import static com.razytech.razynet.Utils.AppConstant.ChildMoved;
import static com.razytech.razynet.Utils.AppConstant.ChildName;
import static com.razytech.razynet.Utils.AppConstant.REDEEMPOINTS_page;
import static com.razytech.razynet.Utils.AppConstant.RedeemidKey;
import static com.razytech.razynet.Utils.AppConstant.RedeemnameKey;

public class HomeFragment extends BaseFragment implements   HomeView , ChildAdpater.ChildListener , TopWalletAdapter.TopWalletListener{


    View view ;
    ActivityHomeFragmentBinding binding ;
    HomeModelView modelView   ;
    ChildAdpater adpater;
    TopWalletAdapter Topadpater;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.activity_home_fragment, container, false);
        view = binding.getRoot();
        inilizeVariables();
        return  view;
    }

    private void inilizeVariables() {
        ((MainpageActivity)getActivity()).setViewHandling("" ,""  , false );
        fillData();
        modelView =  new HomeModelView();
        modelView.attachView(this);

        CheckloadingData();
    }
   void fillData(){
        binding.setLevel(AppConstant.userResponse.getLevelsCount()+"");
        binding.setPoints(AppConstant.userResponse.getBalance()+"");
        binding.setWallet(AppConstant.userResponse.getChildsCount()+"");
    }

    private void CheckloadingData() {
        if (!AppConstant.refreshhome) {
            if (AppConstant.homeResponse != null) {
                LoadWalletSystem(AppConstant.homeResponse.getTopWallets());
                LoadWallet(AppConstant.homeResponse.getTopChildrens());
            } else
                modelView.loadingHomeData(binding.coorhome, getActivity());
        }else
                modelView.loadingHomeData(binding.coorhome, getActivity());
    }

    @Override
    public void LoadWalletSystem(List<ChildResponse> topsystem ) {
        if (topsystem != null ) {
            if(!topsystem.isEmpty()) {
                showerrorlayoutTopsystem(false, "");
                Log.e("topsystem", "" + topsystem.size());
                AppConstant.topsystem = topsystem;
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                binding.recTopuser.setLayoutManager(layoutManager);
                Topadpater = new TopWalletAdapter(getActivity(), topsystem, this,false , false);
                binding.recTopuser.setAdapter(Topadpater);
            }else
                showerrorlayoutTopsystem(true, getActivity().getString(R.string.donothavechilds));
    } else {
        showerrorlayoutTopsystem(true, getActivity().getString(R.string.donothavechilds));
    }
    }

    @Override
    public void LoadWallet(List<ChildResponse> topchilds) {
        if (topchilds != null ) {
            if(!topchilds.isEmpty()) {
                showerrorlayoutchilds(false, "");
                //Log.e("topsystem",""+topchilds.size());
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
        userResponse.setToken(AppConstant.userResponse.getToken());
        AppConstant.userResponse =  userResponse  ;
        fillData();
        PrefUtils.saveUserinformation(getActivity(),userResponse,PrefUtils.User_Singin);
    }

    @Override
    public void showerrorlayoutTopsystem(boolean show ,String errortxt) {
        if (show){
            binding.errorwalletsys.setViewerror(show);
            binding.errorwalletsys.setErrortxt(errortxt);
            binding.errorwalletsys.btnTryAgain.setVisibility(View.GONE);
        }else {
            binding.errorwalletsys.setViewerror(show);
        }
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
    public void onChildClicked(ChildResponse post) {
        Bundle bundle = new Bundle();
        bundle.putString(ChildId,post.getWalletId());
        bundle.putString(ChildName,post.getName());
        bundle.putString(ChildChilds,post.getChildCounts()+"");
        bundle.putString(ChildImage,post.getImageUrl());
        bundle.putBoolean(ChildMoved,post.isMoved());
        ((MainpageActivity)getActivity()).setBundlevalue(bundle , CHILDDETAILS_page);
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
