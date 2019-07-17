package com.razytech.razynet.gui.childDetails;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.razytech.razynet.R;
import com.razytech.razynet.Utils.AppConstant;
import com.razytech.razynet.baseClasses.BaseFragment;
import com.razytech.razynet.databinding.ActivityChildDetailsFragmentBinding;
import com.razytech.razynet.databinding.ActivityUpdateProfileFragmentBinding;
import com.razytech.razynet.gui.mainpage.MainpageActivity;

public class ChildDetailsFragment extends BaseFragment implements  ChildDetailsView {

   View view ;
   ActivityChildDetailsFragmentBinding  binding  ;
   ChildDetailsViewModel viewModel ;
   MyClickHandlers handlers  ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.activity_child_details_fragment, container, false);
        view = binding.getRoot();
        handlers =  new MyClickHandlers(getActivity());
        binding.setHandlers(handlers);
        inilizeVariables();
        return  view;
    }

    private void inilizeVariables() {
        ((MainpageActivity)getActivity()).setViewHandling(AppConstant.userResponse.getBalance()+""  ,AppConstant.userResponse.getChildsCount()+"" , true , false );

        viewModel =  new ChildDetailsViewModel();
        viewModel.attachView(this);
    }

    public class MyClickHandlers {
        Context context;
        public MyClickHandlers(Context context) {
            this.context = context;
        }
        public void btn_move(View view) {
        }
    }

}
