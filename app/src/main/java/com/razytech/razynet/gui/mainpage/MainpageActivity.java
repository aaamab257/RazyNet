package com.razytech.razynet.gui.mainpage;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.razytech.razynet.R;
import com.razytech.razynet.Utils.AppConstant;
import com.razytech.razynet.baseClasses.BaseActivity;
import com.razytech.razynet.databinding.ActivityMainpageBinding;
import com.razytech.razynet.gui.addwallet.AddWalletFragment;
import com.razytech.razynet.gui.homepage.HomeFragment;
import com.razytech.razynet.gui.maintransaction.MainTransactionFragment;
import com.razytech.razynet.gui.maintransaction.redeem.RedeemListFragment;
import com.razytech.razynet.gui.maintransaction.transfer.TransferFragment;
import com.razytech.razynet.gui.notifications.NotificationsFragment;
import com.razytech.razynet.gui.pointhistory.PointsHistoryFragment;
import com.razytech.razynet.gui.porfile.ProfileFragment;
import com.razytech.razynet.gui.register.RegisterActivity;
import com.razytech.razynet.gui.treepage.TreeFragment;
import com.razytech.razynet.gui.updateprofile.UpdateProfileFragment;
import com.razytech.razynet.gui.walletpage.WalletFragment;

import javax.inject.Inject;
import static com.razytech.razynet.Utils.AppConstant.ADDWALLET_PAGE;
import static com.razytech.razynet.Utils.AppConstant.HOME_page;
import static com.razytech.razynet.Utils.AppConstant.MAINTRANSACTION_page;
import static com.razytech.razynet.Utils.AppConstant.NOTIFICATION_page;
import static com.razytech.razynet.Utils.AppConstant.POINTS_page;
import static com.razytech.razynet.Utils.AppConstant.PROFILE_page;
import static com.razytech.razynet.Utils.AppConstant.REDEEM_page;
import static com.razytech.razynet.Utils.AppConstant.TRANSFER_page;
import static com.razytech.razynet.Utils.AppConstant.TREE_page;
import static com.razytech.razynet.Utils.AppConstant.UPDATEPROFILE_page;
import static com.razytech.razynet.Utils.AppConstant.WALLET_page;

public class MainpageActivity extends BaseActivity<ActivityMainpageBinding , MainpageModelView>
        implements MainpageView {

    ActivityMainpageBinding binding ;
    @Inject MainpageModelView  modelView ;
    MyClickHandlers handlers  ;

    int selectedPosition =  -1 ;

    Bundle bundle =  null  ;
    FragmentManager fragmentManager ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding   =  getViewDataBinding() ;
        handlers =  new MyClickHandlers(this);
        binding.setHandlers(handlers);
        inilizeVaribles();
    }

    private void inilizeVaribles() {
        modelView.attachView(this);
        displayView(HOME_page);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_mainpage;
    }

    @Override
    public MainpageModelView getViewModel() {
        return modelView;
    }

    @Override
    public int getBindingVariable() {
        return 0;
    }


    public void displayView(int position) {
        selectedPosition = position;
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (position) {
            case HOME_page:
                //HOME_page Fragment
                BottomBarMethod(HOME_page);
                selectedPosition = HOME_page;
                fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                fragmentTransaction.replace(R.id.main_content, new HomeFragment());
                break;
            case TREE_page:
                //TREE_page Fragment
                BottomBarMethod(TREE_page);
                selectedPosition = TREE_page;
                fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                fragmentTransaction.replace(R.id.main_content, new TreeFragment());
                break;
            case ADDWALLET_PAGE:
                //ADDWALLET_PAGE Fragment
                BottomBarMethod(ADDWALLET_PAGE);
                selectedPosition = ADDWALLET_PAGE;
                fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                fragmentTransaction.replace(R.id.main_content, new AddWalletFragment());
                break;
            case MAINTRANSACTION_page:
                //MAINTRANSACTION_page Fragment
                BottomBarMethod(MAINTRANSACTION_page);
                selectedPosition = MAINTRANSACTION_page;
                fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                fragmentTransaction.replace(R.id.main_content, new MainTransactionFragment());
                break;
            case REDEEM_page:
                //REDEEM_page Fragment
                BottomBarMethod(REDEEM_page);
                selectedPosition = REDEEM_page;
                fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                fragmentTransaction.replace(R.id.main_content, new RedeemListFragment());
                break;
            case TRANSFER_page:
                //TRANSFER_page Fragment
                BottomBarMethod(TRANSFER_page);
                selectedPosition = TRANSFER_page;
                fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                fragmentTransaction.replace(R.id.main_content, new TransferFragment());
                break;


            case NOTIFICATION_page:
                //NOTIFICATION_page Fragment
                BottomBarMethod(NOTIFICATION_page);
                selectedPosition = NOTIFICATION_page;
                fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                fragmentTransaction.replace(R.id.main_content, new NotificationsFragment());
                break;
            case POINTS_page:
                //POINTS_page Fragment
                BottomBarMethod(POINTS_page);
                selectedPosition = POINTS_page;
                fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                fragmentTransaction.replace(R.id.main_content, new PointsHistoryFragment());
                break;
            case WALLET_page :
                //WALLET_page Fragment
                BottomBarMethod(WALLET_page);
                selectedPosition = WALLET_page;
                fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                fragmentTransaction.replace(R.id.main_content, new WalletFragment());
                break;
            case PROFILE_page:
                //PROFILE_page Fragment
                BottomBarMethod(PROFILE_page);
                selectedPosition = PROFILE_page;
                fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                fragmentTransaction.replace(R.id.main_content, new ProfileFragment());
                break;

            case UPDATEPROFILE_page:
                //UPDATEPROFILE_page Fragment
                BottomBarMethod(UPDATEPROFILE_page);
                selectedPosition = UPDATEPROFILE_page;
                fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                fragmentTransaction.replace(R.id.main_content, new UpdateProfileFragment());
                break;

//            case STORE_page:
//                //STORE_page Fragment
//                selectedPosition = STORE_page;
//                fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
//                storePageFragment =  new StorePageFragment() ;
//                storePageFragment.setArguments(bundle);
//                fragmentTransaction.replace(R.id.main_content, storePageFragment);
//                break;


        }
        fragmentTransaction.commit();
    }
    @Override
    public void onBackPressed() {
        if (selectedPosition >= TREE_page  && selectedPosition <= PROFILE_page) {
            displayView(HOME_page);
        }
        else {
            super.onBackPressed();
        }
    }

    public  void setViewHandling(String PointsString ,String wallet   ,boolean showback, boolean bottombar ){
        binding.toolbarpublic.setPointsnumber(PointsString);
        binding.toolbarpublic.setWalletsnumber(wallet);
        binding.toolbarpublic.setShowback(showback);
        binding.setShowbottombar(bottombar);
    }
    public  void setViewHandling(String PointsString ,String wallet  ,boolean showback){
        binding.toolbarpublic.setPointsnumber(PointsString);
        binding.toolbarpublic.setWalletsnumber(wallet);
        binding.toolbarpublic.setShowback(showback);
        binding.setShowbottombar(true);
    }


    public  void setBundlevalue(Bundle bundle,int PageView){
        this.bundle=bundle;
        displayView(PageView);
    }

    void BottomBarMethod(int position){
        switch (position){
            case HOME_page :
                binding.imghome.setImageResource(R.mipmap.home_active);
                binding.imgtree.setImageResource(R.mipmap.tree_notactive);
                binding.imgaddtrans.setImageResource(R.mipmap.add_wallet);
                binding.imgtransaction.setImageResource(R.mipmap.redeem_notactive);
                binding.toolbarpublic.imgnotification.setImageResource(R.mipmap.notification_notactive);
                break;
            case TREE_page :
                binding.imghome.setImageResource(R.mipmap.home_notactive);
                binding.imgtree.setImageResource(R.mipmap.tree_active);
                binding.imgaddtrans.setImageResource(R.mipmap.add_wallet);
                binding.imgtransaction.setImageResource(R.mipmap.redeem_notactive);
                binding.toolbarpublic.imgnotification.setImageResource(R.mipmap.notification_notactive);
                break;
            case ADDWALLET_PAGE :
                binding.imghome.setImageResource(R.mipmap.home_notactive);
                binding.imgtree.setImageResource(R.mipmap.tree_notactive);
                binding.imgaddtrans.setImageResource(R.mipmap.add_wallet);
                binding.imgtransaction.setImageResource(R.mipmap.redeem_notactive);
                binding.toolbarpublic.imgnotification.setImageResource(R.mipmap.notification_notactive);
                break;
            case MAINTRANSACTION_page :
                binding.imghome.setImageResource(R.mipmap.home_notactive);
                binding.imgtree.setImageResource(R.mipmap.tree_notactive);
                binding.imgaddtrans.setImageResource(R.mipmap.add_wallet);
                binding.imgtransaction.setImageResource(R.mipmap.redeem_active);
                binding.toolbarpublic.imgnotification.setImageResource(R.mipmap.notification_notactive);
                break;

            case NOTIFICATION_page :
                    binding.imghome.setImageResource(R.mipmap.home_notactive);
                    binding.imgtree.setImageResource(R.mipmap.tree_notactive);
                    binding.imgaddtrans.setImageResource(R.mipmap.add_wallet);
                    binding.imgtransaction.setImageResource(R.mipmap.redeem_notactive);
                    binding.toolbarpublic.imgnotification.setImageResource(R.mipmap.notification_active);
                break;
                default:
                    binding.imghome.setImageResource(R.mipmap.home_notactive);
                    binding.imgtree.setImageResource(R.mipmap.tree_notactive);
                    binding.imgaddtrans.setImageResource(R.mipmap.add_wallet);
                    binding.imgtransaction.setImageResource(R.mipmap.redeem_notactive);
                    binding.toolbarpublic.imgnotification.setImageResource(R.mipmap.notification_notactive);
                    break;

        }
    }

    public class MyClickHandlers {
        Context context;

        public MyClickHandlers(Context context) {
            this.context = context;
        }
        public void btnHome(View view) {
            if (selectedPosition != HOME_page)
                displayView(HOME_page);
        }
        public void  btnTree(View view) {
            if (selectedPosition != TREE_page)
                displayView(TREE_page);
        }
        public void btnAddWallet(View view) {
            if (selectedPosition != ADDWALLET_PAGE)
                displayView(ADDWALLET_PAGE);
        }
        public void btnTransactions(View view) {
            if (selectedPosition != MAINTRANSACTION_page)
                displayView(MAINTRANSACTION_page);
        }
    }
}