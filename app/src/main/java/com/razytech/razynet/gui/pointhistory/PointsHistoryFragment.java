package com.razytech.razynet.gui.pointhistory;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.razytech.razynet.Adapter.PointsAdapter;
import com.razytech.razynet.R;
import com.razytech.razynet.Utils.AppConstant;
import com.razytech.razynet.baseClasses.BaseFragment;
import com.razytech.razynet.data.beans.PointHistoryResponse;
import com.razytech.razynet.databinding.ActivityPointsHistoryFragmentBinding;
import com.razytech.razynet.gui.mainpage.MainpageActivity;
import java.util.List;
import static com.razytech.razynet.Utils.AppConstant.BTN_All;
import static com.razytech.razynet.Utils.AppConstant.BTN_IN;
import static com.razytech.razynet.Utils.AppConstant.BTN_OUT;
import static com.razytech.razynet.Utils.AppConstant.UPDATE_CHILD;
import static com.razytech.razynet.Utils.AppConstant.UPDATE_POINTS;

public class PointsHistoryFragment extends BaseFragment  implements  PointsHistoryView ,  PointsAdapter.PointsListener{


    View view ;
    ActivityPointsHistoryFragmentBinding binding ;
    PointsHistoryModleView  modelView   ;
    MyClickHandlers handlers  ;
    PointsAdapter adapter ;
    int tabPosition  = BTN_All ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.activity_points_history_fragment, container, false);
        view = binding.getRoot();
        handlers =  new MyClickHandlers(getActivity());
        binding.setHandlers(handlers);
        inilizeVariables();
        return  view;
    }

    private void inilizeVariables() {
        ((MainpageActivity)getActivity()).setViewHandling(""  ,""  ,false  );
        modelView =  new PointsHistoryModleView();
        modelView.attachView(this);
        binding.setPointsnumber(AppConstant.userResponse.getBalance()+"");
        binding.setWalletnumber(AppConstant.userResponse.getChildsCount()+"");
        binding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshdata();
            }
        });
        CheckloadingData();
    }

    private void CheckloadingData() {
        if (AppConstant.pointsResponses == null)
            modelView.loadingPointsData(binding.coorpointhistory,getActivity(),false);
        else {
            LoadingPointsData(AppConstant.pointsResponses);
        }
    }

    private void refreshdata(){
        binding.swipeRefreshLayout.setRefreshing(true);
        modelView.loadingPointsData(binding.coorpointhistory,getActivity(),true);
    }

    @Override
    public void LoadingPointsData(List<PointHistoryResponse> pointsResponses) {
        hide_refreshView();
        show_errorView(false,  "" );
        if (pointsResponses.size() != 0) {
            binding.recPointshistory.setLayoutManager(new LinearLayoutManager(getActivity()));
            adapter = new PointsAdapter(getActivity(), pointsResponses, this, tabPosition);
            binding.recPointshistory.setAdapter(adapter);
            HandlingTopBar(BTN_All );
        }else
            show_errorView(true ,getString(R.string.donothavepointshistory));
    }

    @Override
    public void show_errorView(boolean Isshow, String error) {
        if (Isshow){
            binding.errorLayoutView.setViewerror(Isshow);
            binding.errorLayoutView.setErrortxt(error);
            binding.errorLayoutView.btnTryAgain.setOnClickListener((View)->{
                CheckloadingData();
            });
        }else {
            binding.errorLayoutView.setViewerror(Isshow);
        }
    }



    void HandlingTopBar(int TabPosition){
        switch (TabPosition) {
            case BTN_All:
                binding.imgall.setTextColor(getResources().getColor(R.color.white));
                binding.txtall.setBackgroundColor(getResources().getColor(R.color.darkred));
                binding.imgin.setTextColor(getResources().getColor(R.color.gray));
                binding.txtin.setBackgroundColor(Color.TRANSPARENT);
                binding.imgout.setTextColor(getResources().getColor(R.color.gray));
                binding.txtout.setBackgroundColor(Color.TRANSPARENT);
               // LoadingPointsData(AppConstant.pointsResponses);
                if (adapter != null)
                adapter.filter(BTN_All , AppConstant.pointsResponses);
                break;
            case BTN_IN:

                binding.imgall.setTextColor(getResources().getColor(R.color.gray));
                binding.txtall.setBackgroundColor(Color.TRANSPARENT);
                binding.imgin.setTextColor(getResources().getColor(R.color.white));
                binding.txtin.setBackgroundColor(getResources().getColor(R.color.darkred));
                binding.imgout.setTextColor(getResources().getColor(R.color.gray));
                binding.txtout.setBackgroundColor(Color.TRANSPARENT);
                if (adapter != null)
                adapter.filter(BTN_IN , AppConstant.pointsResponses);
                break;
            case BTN_OUT:
                binding.imgall.setTextColor(getResources().getColor(R.color.gray));
                binding.txtall.setBackgroundColor(Color.TRANSPARENT);
                binding.imgin.setTextColor(getResources().getColor(R.color.gray));
                binding.txtin.setBackgroundColor(Color.TRANSPARENT);
                binding.imgout.setTextColor(getResources().getColor(R.color.white));
                binding.txtout.setBackgroundColor(getResources().getColor(R.color.darkred));
                if (adapter != null)
                adapter.filter(BTN_OUT , AppConstant.pointsResponses);
                break;
        }
    }

    @Override
    public void hide_refreshView() {
        if (binding.swipeRefreshLayout.isRefreshing()) {
            binding.swipeRefreshLayout.setRefreshing(false);
        }
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
    public void onItemClicked(PointHistoryResponse post) {

    }

    public class MyClickHandlers {
        Context context;
        public MyClickHandlers(Context context) {
            this.context = context;
        }

        public void btnall(View view) {
            HandlingTopBar(BTN_All);
            tabPosition =  BTN_All;
        }
        public void btnin(View view) {
            HandlingTopBar(BTN_IN);
            tabPosition =  BTN_IN;
        }
        public void btnout(View view) {
            HandlingTopBar(BTN_OUT);
            tabPosition =  BTN_OUT;
        }
    }
}
