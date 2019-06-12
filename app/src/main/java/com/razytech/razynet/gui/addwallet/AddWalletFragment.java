package com.razytech.razynet.gui.addwallet;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.razytech.razynet.R;
import com.razytech.razynet.baseClasses.BaseFragment;
import com.razytech.razynet.databinding.ActivityAddWalletFragmentBinding;


public class AddWalletFragment extends BaseFragment  implements  AddWalletView {


    View view ;
    ActivityAddWalletFragmentBinding binding ;
    AddWalletModelView modelView   ;
    MyClickHandlers handlers ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.activity_add_wallet_fragment, container, false);
        //  setUserVisibleHint(false);
        view = binding.getRoot();
        handlers = new MyClickHandlers(getActivity());
        binding.setHandlers(handlers);
        inilizeVariables();
        return  view;
    }

    private void inilizeVariables() {
        modelView =  new AddWalletModelView();
        modelView.attachView(this);
    }

    public class MyClickHandlers {
        Context context;
        public MyClickHandlers(Context context) {
            this.context = context;
        }
        public void btnAdd(View view) {
        }
    }
}