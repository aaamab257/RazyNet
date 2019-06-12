package com.razytech.razynet.gui.maintransaction;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.razytech.razynet.R;
import com.razytech.razynet.baseClasses.BaseFragment;
import com.razytech.razynet.databinding.ActivityMainTransactionFragmentBinding;
import com.razytech.razynet.databinding.ActivityTreeFragmentBinding;
import com.razytech.razynet.gui.mainpage.MainpageActivity;

import static com.razytech.razynet.Utils.AppConstant.REDEEM_page;
import static com.razytech.razynet.Utils.AppConstant.TRANSFER_page;

public class MainTransactionFragment extends BaseFragment {

    View view ;
    ActivityMainTransactionFragmentBinding binding ;
    MyClickHandlers handlers  ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.activity_main_transaction_fragment, container, false);
        //  setUserVisibleHint(false);
        view = binding.getRoot();
        handlers  =  new MyClickHandlers(getActivity());
        binding.setHandlers(handlers);
        inilizeVariables();
        return  view;
    }

    private void inilizeVariables() {
        ((MainpageActivity)getActivity()).setViewHandling("128"  ,"3"  , false );
    }

    public class MyClickHandlers {
        Context context;
        public MyClickHandlers(Context context) {
            this.context = context;
        }
        public void btnRedeem(View view) {
            ((MainpageActivity)getActivity()).displayView(REDEEM_page);
        }
        public void btnTransfer(View view) {
            ((MainpageActivity)getActivity()).displayView(TRANSFER_page);
        }
    }
}
