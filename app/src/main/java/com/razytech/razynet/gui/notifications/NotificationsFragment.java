package com.razytech.razynet.gui.notifications;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.razytech.razynet.Adapter.NotificationsAdapter;
import com.razytech.razynet.R;
import com.razytech.razynet.Utils.AppConstant;
import com.razytech.razynet.baseClasses.BaseFragment;
import com.razytech.razynet.data.beans.NotificationsResponse;
import com.razytech.razynet.databinding.ActivityNotificationFragmentBinding;
import com.razytech.razynet.gui.mainpage.MainpageActivity;

import java.util.List;

public class NotificationsFragment extends BaseFragment implements NotificationsView ,  NotificationsAdapter.NotificationsListener {


    View view ;
    ActivityNotificationFragmentBinding binding ;
    NotificationsAdapter adapter ;
    NotificationsModelView  modelView  ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.activity_notification_fragment, container, false);
        //  setUserVisibleHint(false);
        view = binding.getRoot();

        inilizeVariables();
        return  view;
    }

    private void inilizeVariables() {
        ((MainpageActivity)getActivity()).setViewHandling("128"  ,"3"  ,false  );
        modelView =  new NotificationsModelView();
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
        if (AppConstant.notificationsResponses == null)
            modelView.loadingNotificationData(binding.coornotification,getActivity(),false);
        else {
            LoadingnotificationData(AppConstant.notificationsResponses);
        }
    }
    private void refreshdata(){
        binding.swipeRefreshLayout.setRefreshing(true);
        modelView.loadingNotificationData(binding.coornotification,getActivity(),true);
    }

    @Override
    public void LoadingnotificationData(List<NotificationsResponse> notificationsRes) {
        hide_refreshView();
        show_errorView(false,  "" );
        if (notificationsRes.size() != 0) {
            AppConstant.notificationsResponses = notificationsRes;
            binding.recNotificationlist.setLayoutManager(new LinearLayoutManager(getActivity()));
            adapter = new NotificationsAdapter(getActivity(), notificationsRes, this);
            binding.recNotificationlist.setAdapter(adapter);
        }else
            show_errorView(true ,getString(R.string.donothavenotifications));
    }

    @Override
    public void hide_refreshView() {
        if (binding.swipeRefreshLayout.isRefreshing()) {
            binding.swipeRefreshLayout.setRefreshing(false);
        }
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

    @Override
    public void onnotificationClicked(NotificationsResponse post) {

    }
}
