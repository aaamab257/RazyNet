package com.razytech.razynet.gui.maintransaction.transfer;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.razytech.razynet.R;
import com.razytech.razynet.baseClasses.BaseFragment;
import com.razytech.razynet.databinding.ActivityRedeemFragmentBinding;
import com.razytech.razynet.databinding.ActivityTransferFragmentBinding;
import com.razytech.razynet.gui.homepage.HomeFragment;
import com.razytech.razynet.gui.mainpage.MainpageActivity;

import static com.razytech.razynet.Utils.AppConstant.HOME_page;
import static com.razytech.razynet.Utils.AppConstant.TRANSFERCONFIRMFINAL_page;
import static com.razytech.razynet.Utils.AppConstant.TRANSFERCONFIRM_page;
import static com.razytech.razynet.Utils.AppConstant.TRANSFERPOINTS_page;
import static com.razytech.razynet.Utils.AppConstant.TRANSFERWALLET_page;
import static com.razytech.razynet.Utils.AppConstant.TRANSFER_page;
import static com.razytech.razynet.Utils.AppConstant.TREE_page;

public class TransferFragment extends BaseFragment  implements  TransferView {


    View view ;
    ActivityTransferFragmentBinding binding ;
    TransferModelView modelView   ;
    MyClickHandlers handlers  ;
    public  String points = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.activity_transfer_fragment, container, false);
        //  setUserVisibleHint(false);
        view = binding.getRoot();
        handlers =  new MyClickHandlers(getActivity());
        binding.setHandlers(handlers);
        inilizeVariables();
        return  view;
    }

    private void inilizeVariables() {
        ((MainpageActivity)getActivity()).setViewHandling("128"  ,"3"  , true , false );
        modelView =  new TransferModelView();
        modelView.attachView(this);
        SetStepsHandlingView(TRANSFERWALLET_page);
        binding.transferwallet.btnNext.setOnClickListener((View) ->{
         modelView.setbtnNextWallet(binding.transferwallet.coorwallet ,  getActivity() ,binding.transferwallet.createAccPhoneET.getText().toString());
        });
        binding.transferpoints.btnNext.setOnClickListener((View) ->{
          modelView.setbtnPoints(binding.transferwallet.coorwallet ,  getActivity() ,binding.transferpoints.createAccPointsET.getText().toString(),true);
        });
        binding.transferpoints.btnBack.setOnClickListener((View) ->{
          modelView.setbtnPoints(binding.transferwallet.coorwallet ,  getActivity() ,binding.transferpoints.createAccPointsET.getText().toString(),false);
        });

        binding.tranferconfirm.btnConfirm.setOnClickListener((View) ->{
            modelView.setbtnConfirm(binding.transferwallet.coorwallet ,  getActivity(),true);
        });
        binding.tranferconfirm.btnBack.setOnClickListener((View) ->{
            modelView.setbtnConfirm(binding.transferwallet.coorwallet ,  getActivity(),false);
        });

        binding.transferfinalconfirm.btnHome.setOnClickListener((View) ->{
            modelView.setbtnHome(binding.transferwallet.coorwallet ,  getActivity());
        });
    }
    @Override
     public void SetStepsHandlingView(int TranFerPage){
        switch (TranFerPage) {
            case TRANSFERWALLET_page:
                //TRANSFERWALLET_page Fragment
                ((MainpageActivity)getActivity()).selectedPosition = TRANSFER_page ;
                binding.imgwallet.setImageResource(R.mipmap.choose_wallet_active);
                binding.imgpoints.setImageResource(R.mipmap.choose_point_notactive);
                binding.imgconfirm.setImageResource(R.mipmap.confirm_notactive);
                binding.linpoints.setBackgroundColor(getActivity().getResources().getColor(R.color.darkBlack));
                binding.linconfirm.setBackgroundColor(getActivity().getResources().getColor(R.color.darkBlack));
                binding.transferwallet.coorwallet.setVisibility(View.VISIBLE);
                binding.transferpoints.coorpoints.setVisibility(View.GONE);
                binding.tranferconfirm.coorconfirm.setVisibility(View.GONE);
                binding.transferfinalconfirm.coorfinalconfirm.setVisibility(View.GONE);

                break;
            case TRANSFERPOINTS_page:
                //TRANSFERPOINTS_page Fragment
                ((MainpageActivity)getActivity()).selectedPosition = TRANSFERPOINTS_page ;
                binding.imgwallet.setImageResource(R.mipmap.choose_wallet_active);
                binding.imgpoints.setImageResource(R.mipmap.choose_point_active);
                binding.imgconfirm.setImageResource(R.mipmap.confirm_notactive);
                binding.linpoints.setBackgroundColor(getActivity().getResources().getColor(R.color.darkred));
                binding.linconfirm.setBackgroundColor(getActivity().getResources().getColor(R.color.darkBlack));
                binding.transferwallet.coorwallet.setVisibility(View.GONE);
                binding.transferpoints.coorpoints.setVisibility(View.VISIBLE);
                binding.tranferconfirm.coorconfirm.setVisibility(View.GONE);
                binding.transferfinalconfirm.coorfinalconfirm.setVisibility(View.GONE);
                break;
            case TRANSFERCONFIRM_page:
                //TRANSFERCONFIRM_page Fragment
                ((MainpageActivity)getActivity()).selectedPosition = TRANSFERCONFIRM_page ;
                binding.imgwallet.setImageResource(R.mipmap.choose_wallet_active);
                binding.imgpoints.setImageResource(R.mipmap.choose_point_active);
                binding.imgconfirm.setImageResource(R.mipmap.confirm_avtive);
                binding.linpoints.setBackgroundColor(getActivity().getResources().getColor(R.color.darkred));
                binding.linconfirm.setBackgroundColor(getActivity().getResources().getColor(R.color.darkred));
                binding.transferwallet.coorwallet.setVisibility(View.GONE);
                binding.transferpoints.coorpoints.setVisibility(View.GONE);
                binding.tranferconfirm.coorconfirm.setVisibility(View.VISIBLE);
                binding.transferfinalconfirm.coorfinalconfirm.setVisibility(View.GONE);
                binding.tranferconfirm.txtpoints.setText(points);
                break;
            case TRANSFERCONFIRMFINAL_page:
                //TRANSFERCONFIRMFINAL_page Fragment
                ((MainpageActivity)getActivity()).selectedPosition = TRANSFERCONFIRMFINAL_page ;
                binding.transferwallet.coorwallet.setVisibility(View.GONE);
                binding.transferpoints.coorpoints.setVisibility(View.GONE);
                binding.tranferconfirm.coorconfirm.setVisibility(View.GONE);
                binding.transferfinalconfirm.coorfinalconfirm.setVisibility(View.VISIBLE);
                binding.transferfinalconfirm.txtpoints.setText(points);
                break;
        }
    }

    @Override
    public void SetPointValue(String Points) {
        points =  Points  ;
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
