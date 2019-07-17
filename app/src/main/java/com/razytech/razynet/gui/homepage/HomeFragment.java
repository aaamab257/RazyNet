package com.razytech.razynet.gui.homepage;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.razytech.razynet.Adapter.ChildAdpater;
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

public class HomeFragment extends BaseFragment implements   HomeView , ChildAdpater.ChildListener{


    View view ;
    ActivityHomeFragmentBinding binding ;
    HomeModelView modelView   ;
    ChildAdpater adpater;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.activity_home_fragment, container, false);
      //  setUserVisibleHint(false);
        view = binding.getRoot();
        inilizeVariables();
        return  view;
    }

    private void inilizeVariables() {
//        AppConstant.userResponse.setBalance(1000.00);
//        AppConstant.userResponse.setToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1lIjoiMDExMjg4MTg0NTciLCJzdWIiOiIwMTEyODgxODQ1NyIsImp0aSI6ImQ2N2YwMDllLWY0N2YtNGMzOS04MWIyLTY2NzNmMjAwODM3MiIsImh0dHA6Ly9zY2hlbWFzLnhtbHNvYXAub3JnL3dzLzIwMDUvMDUvaWRlbnRpdHkvY2xhaW1zL25hbWVpZGVudGlmaWVyIjoiNTU2NTU5NDAtMjBlZS00MTBlLThlNTAtODkxNWUwNjcwOTEyIiwiZXhwIjoxNTY1NDM4NzMxLCJpc3MiOiJSYXpOZXQuY29tIiwiYXVkIjoiUmF6TmV0LmNvbSJ9.4zQEif5GgTB1uax7EC_czfJGKSgNx2qB-WV_mfiMNJ4");
        ((MainpageActivity)getActivity()).setViewHandling("" ,""  , false );
        binding.setLevel(AppConstant.userResponse.getLevelsCount()+"");
        binding.setPoints(AppConstant.userResponse.getBalance()+"");
        binding.setWallet(AppConstant.userResponse.getChildsCount()+"");
        modelView =  new HomeModelView();
        modelView.attachView(this);

        CheckloadingData();
    }

    private void CheckloadingData() {
       if (AppConstant.homeResponse != null){
           LoadWalletSystem(AppConstant.homeResponse.getTopWallets());
           LoadWallet(AppConstant.homeResponse.getTopChildrens());
       }else{
           modelView.loadingHomeData(binding.coorhome  ,  getActivity());
       }
    }

    @Override
    public void LoadWalletSystem(List<ChildResponse> topsystem ) {
        if (topsystem != null && !topsystem.isEmpty()) {
        showerrorlayoutTopsystem(false,  "" );
        AppConstant.topsystem =  topsystem ;
        binding.recTopuser.setLayoutManager(new LinearLayoutManager(getActivity()));
        adpater =  new ChildAdpater(getActivity(),AppConstant.topsystem,this ,  true , false);
        binding.recTopuser.setAdapter(adpater);
    } else {
        showerrorlayoutTopsystem(true, getActivity().getString(R.string.donothavechilds));
    }
    }

    @Override
    public void LoadWallet(List<ChildResponse> topchilds) {
        if (topchilds != null  && !topchilds.isEmpty()) {

            showerrorlayoutTopsystem(false, "");
            AppConstant.topchilds = topchilds;
            binding.recTopwallet.setLayoutManager(new LinearLayoutManager(getActivity()));
            adpater = new ChildAdpater(getActivity(), AppConstant.topchilds, this, false);
            binding.recTopwallet.setAdapter(adpater);
        } else {
            showerrorlayoutchilds(true, getActivity().getString(R.string.donothavechilds));
        }
    }

    @Override
    public void UpdateUserData(UserResponse userResponse) {
        userResponse.setToken(AppConstant.userResponse.getToken());
        AppConstant.userResponse =  userResponse  ;
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
