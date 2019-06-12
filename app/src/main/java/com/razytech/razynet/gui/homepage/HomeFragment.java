package com.razytech.razynet.gui.homepage;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.razytech.razynet.R;
import com.razytech.razynet.baseClasses.BaseFragment;
import com.razytech.razynet.databinding.ActivityHomeFragmentBinding;
import com.razytech.razynet.gui.mainpage.MainpageActivity;

public class HomeFragment extends BaseFragment implements   HomeView{


    View view ;
    ActivityHomeFragmentBinding binding ;
    HomeModelView modelView   ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.activity_home_fragment, container, false);
      //  setUserVisibleHint(false);
        view = binding.getRoot();
        inilizeVariables();
        return  view;
    }

    private void inilizeVariables() {
        ((MainpageActivity)getActivity()).setViewHandling("128"  ,"3"  , false );
        binding.setLevel("2");
        binding.setPoints("128");
        binding.setWallet("3");
        modelView =  new HomeModelView();
        modelView.attachView(this);
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
