package com.razytech.razynet.gui.pointhistory;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
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
        CheckloadingData();
    }

    private void CheckloadingData() {
        if (AppConstant.pointsResponses == null)
            modelView.loadingPointsData(binding.coorpointhistory,getActivity(),false);
        else {
            LoadingPointsData(AppConstant.pointsResponses);
        }
    }

    @Override
    public void LoadingPointsData(List<PointHistoryResponse> pointsResponses) {
        show_errorView(false,  "" );
        binding.recPointshistory.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter =  new PointsAdapter(getActivity(),pointsResponses,this ,  tabPosition);
        binding.recPointshistory.setAdapter(adapter);
    //   Log.e("itemlistsize22", ""+AppConstant.pointsResponses.size());
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
                adapter.filter(BTN_All , AppConstant.pointsResponses);
                break;
            case BTN_IN:

                binding.imgall.setTextColor(getResources().getColor(R.color.gray));
                binding.txtall.setBackgroundColor(Color.TRANSPARENT);
                binding.imgin.setTextColor(getResources().getColor(R.color.white));
                binding.txtin.setBackgroundColor(getResources().getColor(R.color.darkred));
                binding.imgout.setTextColor(getResources().getColor(R.color.gray));
                binding.txtout.setBackgroundColor(Color.TRANSPARENT);
                adapter.filter(BTN_IN , AppConstant.pointsResponses);
                break;
            case BTN_OUT:
                binding.imgall.setTextColor(getResources().getColor(R.color.gray));
                binding.txtall.setBackgroundColor(Color.TRANSPARENT);
                binding.imgin.setTextColor(getResources().getColor(R.color.gray));
                binding.txtin.setBackgroundColor(Color.TRANSPARENT);
                binding.imgout.setTextColor(getResources().getColor(R.color.white));
                binding.txtout.setBackgroundColor(getResources().getColor(R.color.darkred));
                adapter.filter(BTN_OUT , AppConstant.pointsResponses);
                break;
        }
    }

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
