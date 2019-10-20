package com.razytech.razynet.gui.maintransaction.transfer;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.razytech.razynet.Adapter.ChildAdpater;
import com.razytech.razynet.R;
import com.razytech.razynet.Utils.AppConstant;
import com.razytech.razynet.Utils.DecimalDigitsInputFilter;
import com.razytech.razynet.baseClasses.BaseFragment;
import com.razytech.razynet.data.beans.ChildResponse;
import com.razytech.razynet.data.prefs.PrefUtils;
import com.razytech.razynet.databinding.ActivityRedeemFragmentBinding;
import com.razytech.razynet.databinding.ActivityTransferFragmentBinding;
import com.razytech.razynet.gui.homepage.HomeFragment;
import com.razytech.razynet.gui.mainpage.MainpageActivity;
import com.razytech.razynet.gui.passwordconfirm.PasswordModelView;
import com.razytech.razynet.gui.passwordconfirm.PasswordView;

import java.util.List;

import static com.razytech.razynet.Utils.AppConstant.HOME_page;
import static com.razytech.razynet.Utils.AppConstant.TRANSFERCONFIRMFINAL_page;
import static com.razytech.razynet.Utils.AppConstant.TRANSFERCONFIRM_page;
import static com.razytech.razynet.Utils.AppConstant.TRANSFERPOINTS_page;
import static com.razytech.razynet.Utils.AppConstant.TRANSFERWALLET_page;
import static com.razytech.razynet.Utils.AppConstant.TRANSFER_page;
import static com.razytech.razynet.Utils.AppConstant.TREE_page;

public class TransferFragment extends BaseFragment  implements  TransferView
        ,ChildAdpater.ChildListener , PasswordView {


    View view;
    ActivityTransferFragmentBinding binding;
    TransferModelView modelView;
    MyClickHandlers handlers;
    public String points = "";
    ChildAdpater adpater;
    ChildResponse childRespon = null  ;
    String phonenumber =  ""  ;
    PasswordModelView passwordModelView  ;

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
        ((MainpageActivity)getActivity()).setViewHandling(AppConstant.userResponse.getBalance()+""  ,AppConstant.userResponse.getChildsCount()+"" , true , false );
        modelView =  new TransferModelView();
        modelView.attachView(this);
        binding.transferpoints.createAccPointsET.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(5,2)});
        SetStepsHandlingView(TRANSFERWALLET_page);
        passwordModelView =  new PasswordModelView(getActivity() , getActivity() ,this,binding.coortransfer);
        binding.transferwallet.btnNext.setOnClickListener((View) ->{
            hideKey();
            if (childRespon != null)
                phonenumber = childRespon.getMobileNo();
//          else
//                phonenumber = binding.transferwallet.createAccPhoneET.getText().toString();
         modelView.setbtnNextWallet(binding.transferwallet.coorwallet ,  getActivity() ,phonenumber);
        });
        binding.transferwallet.imgSearch.setOnClickListener((View) ->{
            hideKey();
            modelView.GetChildsData(binding.transferwallet.coorwallet ,  getActivity() ,binding.transferwallet.createAccPhoneET.getText().toString());
        });
        binding.transferpoints.btnNext.setOnClickListener((View) ->{
            hideKey();
          modelView.setbtnPoints(binding.transferwallet.coorwallet ,  getActivity() ,binding.transferpoints.createAccPointsET.getText().toString(),true);
        });
        binding.transferpoints.btnBack.setOnClickListener((View) ->{
            hideKey();
          modelView.setbtnPoints(binding.transferwallet.coorwallet ,  getActivity() ,binding.transferpoints.createAccPointsET.getText().toString(),false);
        });

        binding.tranferconfirm.btnConfirm.setOnClickListener((View) ->{
            hideKey();
            passwordModelView.ShowAlertDialoug();
        });
        binding.tranferconfirm.btnBack.setOnClickListener((View) ->{
            hideKey();
            modelView.setbtnConfirm(binding.transferwallet.coorwallet ,  getActivity(),false);
        });

        binding.transferfinalconfirm.btnHome.setOnClickListener((View) ->{
            hideKey();
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
                CheckloadingData();
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

    private void CheckloadingData() {
        if (AppConstant.childResponses == null)
            modelView.loadingChildsData(binding.coortransfer,getActivity());
        else {
            LoadingchildData(AppConstant.childResponses);
        }
    }

    @Override
    public void SetPointValue(String Points) {
        points =  Points  ;
    }

    @Override
    public void LoadingchildData(List<ChildResponse> childRespo) {
        show_errorView(false,  "" );
        childRespon = null ;
        show_Nochild(false,  "" );
        binding.transferwallet.rectransfer.setLayoutManager(new LinearLayoutManager(getActivity()));
        adpater =  new ChildAdpater(getActivity(),childRespo,this ,  false);
        binding.transferwallet.rectransfer.setAdapter(adpater);

    }
    
    void hideKey(){
        //getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
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
    public void show_Nochild(boolean Isshow, String error) {

            binding.transferwallet.setNochild(Isshow);
            binding.transferwallet.nochildView.setErrortxt(error);
            binding.errorLayoutView.btnTryAgain.setOnClickListener((View)->{
                CheckloadingData();
            });

    }

    @Override
    public void UpdatePoints(double Points) {
       // points =  Points+"" ;
        ((MainpageActivity)getActivity()).UpdatePointsHandling(Points+"");
        AppConstant.userResponse.setBalance(Points);
        AppConstant.userResponse.setToken(AppConstant.userResponse.getToken());
        PrefUtils.saveUserinformation(getActivity(),AppConstant.userResponse,PrefUtils.User_Singin);
    }

    @Override
    public void onChildClicked(ChildResponse post) {
        childRespon =  post ;
    }

    @Override
    public void VaildPassword() {
        modelView.setbtnConfirm(binding.transferwallet.coorwallet ,  getActivity(),true);
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
