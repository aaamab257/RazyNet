package com.razytech.razynet.gui.porfile;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.razytech.razynet.R;
import com.razytech.razynet.baseClasses.BaseFragment;
import com.razytech.razynet.databinding.ActivityProfileFragmentBinding;
import com.razytech.razynet.gui.maintransaction.redeem.RedeemListFragment;

public class ProfileFragment extends BaseFragment implements   ProfileView {

    View view ;
    ActivityProfileFragmentBinding binding ;
    ProfileModelView modelView   ;
    MyClickHandlers handlers  ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.activity_profile_fragment, container, false);
        //  setUserVisibleHint(false);
        view = binding.getRoot();
        handlers =  new MyClickHandlers(getActivity());
        binding.setHandlers(handlers);

        inilizeVariables();
        return  view;
    }

    private void inilizeVariables() {
        modelView =  new ProfileModelView();
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
