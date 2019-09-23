package com.razytech.razynet.gui.walletpage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.razytech.razynet.Adapter.ChildAdpater;
import com.razytech.razynet.R;
import com.razytech.razynet.Utils.AppConstant;
import com.razytech.razynet.baseClasses.BaseFragment;
import com.razytech.razynet.data.beans.ChildResponse;
import com.razytech.razynet.databinding.ActivityRedeemFragmentBinding;
import com.razytech.razynet.databinding.ActivityWalletFragmentBinding;
import com.razytech.razynet.gui.mainpage.MainpageActivity;

import java.util.List;

import static com.razytech.razynet.Utils.AppConstant.UPDATE_CHILD;
import static com.razytech.razynet.Utils.AppConstant.UPDATE_POINTS;

public class WalletFragment extends BaseFragment implements WalletView  ,  ChildAdpater.ChildListener {

    View view ;
    ActivityWalletFragmentBinding binding ;
    WalletModelView modelView   ;
    MyClickHandlers handlers  ;
    ChildAdpater adpater ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.activity_wallet_fragment, container, false);
        //  setUserVisibleHint(false);
        view = binding.getRoot();
        handlers =  new MyClickHandlers(getActivity());
        binding.setHandlers(handlers);
        inilizeVariables();
        return  view;
    }

    private void inilizeVariables() {
        ((MainpageActivity)getActivity()).setViewHandling(""  ,""  ,false  );
        modelView =  new WalletModelView();
        modelView.attachView(this);
        binding.setPointsnumber(AppConstant.userResponse.getBalance()+"");
        binding.setWalletnumber(AppConstant.userResponse.getChildsCount()+"");
//        binding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                refreshdata();
//            }
//        });
        CheckloadingData();
    }

    private void CheckloadingData() {
        if (AppConstant.childResponses == null)
            modelView.loadingChildsData(binding.coorwallet,getActivity());
        else {
            LoadingchildData(AppConstant.childResponses);
        }
    }
    private void refreshdata(){
     //   binding.swipeRefreshLayout.setRefreshing(true);
        modelView.loadingChildsData(binding.coorwallet,getActivity());
    }

    @Override
    public void LoadingchildData(List<ChildResponse> childRespo) {
        //hide_refreshView();
        AppConstant.childResponses =  childRespo ;
        binding.recUserdatalist.setLayoutManager(new LinearLayoutManager(getActivity()));
        adpater =  new ChildAdpater(getActivity(),childRespo,this  ,  true ,false);
        binding.recUserdatalist.setAdapter(adpater);
    }

    @Override
    public void hide_refreshView() {
//        if (binding.swipeRefreshLayout.isRefreshing()) {
//            binding.swipeRefreshLayout.setRefreshing(false);
//        }
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            getActivity().registerReceiver(netSwitchReceiver, new IntentFilter(AppConstant.ActionString));
        }
        catch (Exception e){ }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            getActivity().unregisterReceiver(netSwitchReceiver);
        }
        catch (Exception e){ }
    }
    @Override
    public void onPause() {
        super.onPause();
        try {
            getActivity().unregisterReceiver(netSwitchReceiver);
        }
        catch (Exception e){ }
    }


    BroadcastReceiver netSwitchReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            binding.setPointsnumber(intent.getExtras().getString(UPDATE_POINTS));
            if (intent.hasExtra(UPDATE_CHILD)){
                binding.setWalletnumber(intent.getExtras().getString(UPDATE_CHILD));
            }
        }
    };

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
