package com.razytech.razynet.gui.maintransaction.redeem;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.razytech.razynet.Adapter.RedeemAdapter;
import com.razytech.razynet.R;
import com.razytech.razynet.Utils.AppConstant;
import com.razytech.razynet.baseClasses.BaseFragment;
import com.razytech.razynet.data.beans.RedeemResponse;
import com.razytech.razynet.data.prefs.PrefUtils;
import com.razytech.razynet.databinding.ActivityRedeemFragmentBinding;
import com.razytech.razynet.gui.mainpage.MainpageActivity;

import java.util.List;

import static com.razytech.razynet.Utils.AppConstant.REDEEMPOINTS_page;
import static com.razytech.razynet.Utils.AppConstant.REDEEM_page;
import static com.razytech.razynet.Utils.AppConstant.RedeemidKey;
import static com.razytech.razynet.Utils.AppConstant.RedeemnameKey;

public class RedeemListFragment extends BaseFragment implements  RedeemView , RedeemAdapter.RedeemListener {

    View view ;
    ActivityRedeemFragmentBinding binding ;
    RedeemModelView modelView   ;
    RedeemAdapter adapter ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.activity_redeem_fragment, container, false);
        //  setUserVisibleHint(false);
        view = binding.getRoot();
      //  binding.setHandlers(handlers);
        inilizeVariables();
        return  view;
    }

    private void inilizeVariables() {
        ((MainpageActivity)getActivity()).setViewHandling("128"  ,"3"  , true , false );
        modelView =  new RedeemModelView();
        modelView.attachView(this);
        binding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshdata();
            }
        });
        CheckloadingData();
    }

    private void CheckloadingData() {
            if (AppConstant.redeemResponses == null)
                modelView.loadingRedeemData(binding.coorredeem,getActivity(),false);
            else {
                LoadingReddemData(AppConstant.redeemResponses);
            }
    }
    private void refreshdata(){
        binding.swipeRefreshLayout.setRefreshing(true);
        modelView.loadingRedeemData(binding.coorredeem,getActivity(),true);
    }

    @Override
    public void LoadingReddemData(List<RedeemResponse> redeemResponses) {
        hide_refreshView();
        AppConstant.redeemResponses =  redeemResponses ;
        binding.recRedeemlist.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        adapter =  new RedeemAdapter(getActivity(),redeemResponses,this);
        binding.recRedeemlist.setAdapter(adapter);
    }

    @Override
    public void hide_refreshView() {
        if (binding.swipeRefreshLayout.isRefreshing()) {
            binding.swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onredeemClicked(RedeemResponse post) {
        Bundle bundle = new Bundle();
        bundle.putString(RedeemidKey,post.getId()+"");
        bundle.putString(RedeemnameKey,post.getCompanyname()+"");
        ((MainpageActivity)getActivity()).setBundlevalue(bundle , REDEEMPOINTS_page);
    }
}
