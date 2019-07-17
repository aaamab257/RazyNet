package com.razytech.razynet.gui.movepage;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.razytech.razynet.R;
import com.razytech.razynet.Utils.AppConstant;
import com.razytech.razynet.baseClasses.BaseFragment;
import com.razytech.razynet.data.beans.ChildResponse;
import com.razytech.razynet.databinding.ActivityMovepageFragmentBinding;
import com.razytech.razynet.gui.mainpage.MainpageActivity;


import java.util.ArrayList;
import java.util.List;

public class MovepageFragment extends BaseFragment implements MovepageView  {

    ActivityMovepageFragmentBinding binding ;
    MovepageViewModel viewModel  ;
    View view ;
    List<ChildResponse>childResponses;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.activity_movepage_fragment, container, false);
        //  setUserVisibleHint(false);
        view = binding.getRoot();
        inilizeVariables();
        return  view;
    }

    private void inilizeVariables() {
        viewModel  =  new MovepageViewModel() ;
        viewModel.attachView(this);
        ((MainpageActivity)getActivity()).setViewHandling(""  ,""  ,false  );
        CheckloadingData();
    }

    private void CheckloadingData() {
        childResponses =  new ArrayList<>() ;
        if (AppConstant.childResponses != null){
            for (int i=0 ;  i<AppConstant.childResponses.size() ;  i++){
                if (AppConstant.childResponses.get(i).isMoved())
                    childResponses.add(AppConstant.childResponses.get(i)) ;
            }

        }
    }
}
