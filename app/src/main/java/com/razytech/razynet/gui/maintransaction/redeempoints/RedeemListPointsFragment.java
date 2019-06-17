package com.razytech.razynet.gui.maintransaction.redeempoints;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.razytech.razynet.Adapter.RedeemPointsAdapter;
import com.razytech.razynet.R;
import com.razytech.razynet.Utils.AppConstant;
import com.razytech.razynet.baseClasses.BaseFragment;
import com.razytech.razynet.data.beans.RedeemPointsResponse;
import com.razytech.razynet.databinding.ActivityRedeemListPointsBinding;
import com.razytech.razynet.gui.mainpage.MainpageActivity;

import java.util.List;

public class RedeemListPointsFragment extends BaseFragment implements RedeemListPointsView ,  RedeemPointsAdapter.RedeemListener{

    View view ;
    ActivityRedeemListPointsBinding binding ;
    RedeemListPointsModelView modelView   ;
    RedeemPointsAdapter adapter ;
   String redeem_id =  ""  , redeem_name =  "" ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.activity_redeem_list_points, container, false);
        //  setUserVisibleHint(false);
        view = binding.getRoot();
        //  binding.setHandlers(handlers);
        inilizeVariables();
        return  view;
    }

    private void inilizeVariables() {
        modelView =  new RedeemListPointsModelView();
        modelView.attachView(this);
        redeem_id= getArguments().getString(AppConstant.RedeemidKey);
        redeem_name =  getArguments().getString(AppConstant.RedeemnameKey) ;
        binding.setRedeemname(redeem_name);
        ((MainpageActivity)getActivity()).setViewHandling("128"  ,"3"  , true , false );

        binding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshdata();
            }
        });
        CheckloadingData();
    }

    private void CheckloadingData() {
        if (AppConstant.redeemPointsResponses == null)
            modelView.loadingRedeemData(binding.coorredeempoints,getActivity(),false);
        else {
            LoadingReddemData(AppConstant.redeemPointsResponses);
        }
    }
    private void refreshdata(){
        binding.swipeRefreshLayout.setRefreshing(true);
        modelView.loadingRedeemData(binding.coorredeempoints,getActivity(),true);
    }

    @Override
    public void LoadingReddemData(List<RedeemPointsResponse> redeemResponses) {
        hide_refreshView();
        AppConstant.redeemPointsResponses =  redeemResponses ;
        binding.recRedeemlist.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        adapter =  new RedeemPointsAdapter(getActivity(),redeemResponses,this);
        binding.recRedeemlist.setAdapter(adapter);
    }

    @Override
    public void hide_refreshView() {
        if (binding.swipeRefreshLayout.isRefreshing()) {
            binding.swipeRefreshLayout.setRefreshing(false);
        }
    }
    @Override
    public void onredeemClicked(RedeemPointsResponse post) {

    }
}
