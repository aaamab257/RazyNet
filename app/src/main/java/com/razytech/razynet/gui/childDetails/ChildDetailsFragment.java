package com.razytech.razynet.gui.childDetails;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.razytech.razynet.Adapter.ChildAdpater;
import com.razytech.razynet.R;
import com.razytech.razynet.Utils.AppConstant;
import com.razytech.razynet.Utils.StaticMethods;
import com.razytech.razynet.baseClasses.BaseFragment;
import com.razytech.razynet.data.beans.ChildResponse;
import com.razytech.razynet.databinding.ActivityChildDetailsFragmentBinding;
import com.razytech.razynet.gui.mainpage.MainpageActivity;

import java.util.List;

import static com.razytech.razynet.Utils.AppConstant.CHILDDETAILS_page;
import static com.razytech.razynet.Utils.AppConstant.ChildChilds;
import static com.razytech.razynet.Utils.AppConstant.ChildId;
import static com.razytech.razynet.Utils.AppConstant.ChildImage;
import static com.razytech.razynet.Utils.AppConstant.ChildMoved;
import static com.razytech.razynet.Utils.AppConstant.ChildName;
import static com.razytech.razynet.Utils.AppConstant.MOVE_page;

public class ChildDetailsFragment extends BaseFragment implements  ChildDetailsView ,  ChildAdpater.ChildListener{

   View view ;
   ActivityChildDetailsFragmentBinding  binding  ;
   ChildDetailsViewModel viewModel ;
   MyClickHandlers handlers  ;
   String ChildName =  "" ,ChildId =  "" , ChildImage =  "" , ChildNumber =  "";
   boolean ismove =  false;
    ChildAdpater adpater  ;


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
        ChildName= getArguments().getString(AppConstant.ChildName);
        ChildId= getArguments().getString(AppConstant.ChildId);
        ChildNumber= getArguments().getString(AppConstant.ChildChilds);
        ChildImage= getArguments().getString(AppConstant.ChildImage);
        ismove= getArguments().getBoolean(AppConstant.ChildMoved,  false);
        viewModel.loadingChildsData(binding.coorchilddetails , getActivity() , ChildId );
        fillData();
    }

    private void fillData() {
        binding.setChildname(ChildName);
        binding.setWallet(ChildNumber);
        binding.setIsmove(ismove);
        StaticMethods.LoadImage(getActivity(), binding.createAccImg,ChildImage,null);
      //  Log.e("childid", ChildId);

    }

    @Override
    public void LoadingchildData(List<ChildResponse> childResponses) {
        show_errorView(false,  "" );
        binding.recWallet.setLayoutManager(new LinearLayoutManager(getActivity()));
        adpater =  new ChildAdpater(getActivity(),childResponses,this ,  true ,  false);
        binding.recWallet.setAdapter(adpater);
    }

    @Override
    public void show_errorView(boolean Isshow, String error) {
        if (Isshow){
            binding.errorLayout.setViewerror(Isshow);
            binding.errorLayout.setErrortxt(error);
            binding.errorLayout.btnTryAgain.setOnClickListener((View)->{
                viewModel.loadingChildsData(binding.coorchilddetails , getActivity() , ChildId );
            });
        }else {
            binding.errorLayout.setViewerror(Isshow);
        }
    }

    @Override
    public void onChildClicked(ChildResponse post) {

    }

    public class MyClickHandlers {
        Context context;
        public MyClickHandlers(Context context) {
            this.context = context;
        }
        public void btn_move(View view) {
            Bundle bundle = new Bundle();
            bundle.putString(AppConstant.ChildId,ChildId);
            bundle.putString(AppConstant.ChildName,ChildName);
            bundle.putString(AppConstant.ChildChilds,ChildNumber);
            bundle.putString(AppConstant.ChildImage,ChildImage);
            ((MainpageActivity)getActivity()).setBundlevalue(bundle , MOVE_page);
        }
    }

}
