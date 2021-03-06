package com.razytech.razynet.gui.mainpage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.razytech.razynet.R;
import com.razytech.razynet.Utils.AppConstant;
import com.razytech.razynet.Utils.IntentUtiles;
import com.razytech.razynet.Utils.StaticMethods;
import com.razytech.razynet.baseClasses.BaseActivity;
import com.razytech.razynet.data.prefs.PrefUtils;
import com.razytech.razynet.databinding.ActivityMainpageBinding;
import com.razytech.razynet.gui.addwallet.AddWalletFragment;
import com.razytech.razynet.gui.changepassword.ChangePasswordFragment;
import com.razytech.razynet.gui.childDetails.ChildDetailsFragment;
import com.razytech.razynet.gui.homepage.HomeFragment;
import com.razytech.razynet.gui.loginpage.LoginActivity;
import com.razytech.razynet.gui.maintransaction.MainTransactionFragment;
import com.razytech.razynet.gui.maintransaction.redeem.RedeemListFragment;
import com.razytech.razynet.gui.maintransaction.redeempoints.RedeemListPointsFragment;
import com.razytech.razynet.gui.maintransaction.transfer.TransferFragment;
import com.razytech.razynet.gui.movepage.MovepageFragment;
import com.razytech.razynet.gui.notifications.NotificationsFragment;
import com.razytech.razynet.gui.pointhistory.PointsHistoryFragment;
import com.razytech.razynet.gui.porfile.ProfileFragment;
import com.razytech.razynet.gui.register.RegisterActivity;
import com.razytech.razynet.gui.testfragment.TestFragment;
import com.razytech.razynet.gui.treepage.TreeFragment;
import com.razytech.razynet.gui.updateprofile.UpdateProfileFragment;
import com.razytech.razynet.gui.walletpage.WalletFragment;

import javax.inject.Inject;
import static com.razytech.razynet.Utils.AppConstant.ADDWALLET_PAGE;
import static com.razytech.razynet.Utils.AppConstant.CHANGEPASSWORD_page;
import static com.razytech.razynet.Utils.AppConstant.CHILDDETAILS_page;
import static com.razytech.razynet.Utils.AppConstant.CanaddWallet;
import static com.razytech.razynet.Utils.AppConstant.HOME_page;
import static com.razytech.razynet.Utils.AppConstant.MAINTRANSACTION_page;
import static com.razytech.razynet.Utils.AppConstant.MOVE_page;
import static com.razytech.razynet.Utils.AppConstant.NOTIFICATION_page;
import static com.razytech.razynet.Utils.AppConstant.POINTS_page;
import static com.razytech.razynet.Utils.AppConstant.PROFILE_page;
import static com.razytech.razynet.Utils.AppConstant.REDEEMPOINTS_page;
import static com.razytech.razynet.Utils.AppConstant.REDEEM_page;
import static com.razytech.razynet.Utils.AppConstant.StarWallet;
import static com.razytech.razynet.Utils.AppConstant.TEST_page;
import static com.razytech.razynet.Utils.AppConstant.TRANSFERCONFIRMFINAL_page;
import static com.razytech.razynet.Utils.AppConstant.TRANSFERCONFIRM_page;
import static com.razytech.razynet.Utils.AppConstant.TRANSFERPOINTS_page;
import static com.razytech.razynet.Utils.AppConstant.TRANSFER_page;
import static com.razytech.razynet.Utils.AppConstant.TREE_page;
import static com.razytech.razynet.Utils.AppConstant.UPDATEPROFILE_page;
import static com.razytech.razynet.Utils.AppConstant.UPDATE_CHILD;
import static com.razytech.razynet.Utils.AppConstant.UPDATE_POINTS;
import static com.razytech.razynet.Utils.AppConstant.WALLET_page;

public class MainpageActivity extends BaseActivity<ActivityMainpageBinding , MainpageModelView>
        implements MainpageView {

    ActivityMainpageBinding binding ;
    @Inject MainpageModelView  modelView ;
    MyClickHandlers handlers  ;

   public   int selectedPosition =  -1 ;

    Bundle bundle =  null  ;
    FragmentManager fragmentManager ;
    MovepageFragment movepageFragment ;
    RedeemListPointsFragment redeemListPointsFragment ;



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
        //displayView(TEST_page);
        displayView(HOME_page);
        binding.toolbarpublic.imgback.setOnClickListener((View) ->{
            onBackPressed();
        });

        binding.toolbarpublic.imgnotification.setOnClickListener((View) ->{
           displayView(NOTIFICATION_page);
        });
        binding.toolbarpublic.linpoints.setOnClickListener((View) ->{
            displayView(POINTS_page);
        });
        binding.toolbarpublic.linwallet.setOnClickListener((View) ->{
           displayView(WALLET_page);
        });
        binding.toolbarpublic.imgProfile.setOnClickListener((View) ->{
          displayView(PROFILE_page);
        });

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

    public void displayView(int position , boolean refresh){
        displayView(position);
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
                selectedPosition = UPDATEPROFILE_page;
                BottomBarMethod(UPDATEPROFILE_page);
                fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                fragmentTransaction.replace(R.id.main_content, new UpdateProfileFragment());
                  break;

            case CHANGEPASSWORD_page:
                //CHANGEPASSWORD_page Fragment
                BottomBarMethod(CHANGEPASSWORD_page);
                selectedPosition = CHANGEPASSWORD_page;
                fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                fragmentTransaction.replace(R.id.main_content, new ChangePasswordFragment());
                break;

            case REDEEMPOINTS_page:
                //REDEEMPOINTS_page Fragment
                selectedPosition = REDEEMPOINTS_page;
                fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                redeemListPointsFragment =  new RedeemListPointsFragment() ;
                redeemListPointsFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.main_content, redeemListPointsFragment);
                break;


            case CHILDDETAILS_page:
                //CHILDDETAILS_page Fragment
                selectedPosition = CHILDDETAILS_page;
                fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                ChildDetailsFragment childDetailsFragment =  new ChildDetailsFragment();
                childDetailsFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.main_content, childDetailsFragment);
                break;


            case MOVE_page:
                //MOVE_page Fragment
                selectedPosition = MOVE_page;
                fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                movepageFragment =  new MovepageFragment();
                movepageFragment.setArguments(bundle);
                fragmentTransaction.add(R.id.main_content, movepageFragment);
                break;

            case TEST_page:
                //TEST_page Fragment
                selectedPosition = TEST_page;
                fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                fragmentTransaction.replace(R.id.main_content, new TestFragment());
                break;

        }
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (selectedPosition >= TREE_page  && selectedPosition <= PROFILE_page ||selectedPosition == CHILDDETAILS_page ) {
            displayView(HOME_page);
        }
        else if(selectedPosition == REDEEM_page || selectedPosition == TRANSFER_page ) {
            displayView(MAINTRANSACTION_page);
        }
        else if(selectedPosition == REDEEMPOINTS_page ) {
            displayView(REDEEM_page);
        }
        else if(selectedPosition == MOVE_page ) {
           // displayView(CHILDDETAILS_page);
            selectedPosition =CHILDDETAILS_page;
            getSupportFragmentManager().beginTransaction().remove(movepageFragment).commit();
        }
        else if(selectedPosition == UPDATEPROFILE_page || selectedPosition == CHANGEPASSWORD_page) {
             displayView(PROFILE_page);
            //selectedPosition =PROFILE_page;
            //getSupportFragmentManager().beginTransaction().remove(updateProfileFragment).commit();
        }
        else if(selectedPosition == TRANSFERPOINTS_page || selectedPosition == TRANSFERCONFIRM_page || selectedPosition == TRANSFERCONFIRMFINAL_page) {
            //displayView(REDEEM_page);
        }
        else {
            super.onBackPressed();
        }
    }

    public  void setViewHandling(String PointsString ,String wallet   ,boolean showback, boolean bottombar ){
        binding.toolbarpublic.setPointsnumber(AppConstant.userResponse.getBalance()+"");
        binding.toolbarpublic.setWalletsnumber(AppConstant.userResponse.getChildsCount()+"");
        binding.toolbarpublic.setShowback(showback);
        binding.setShowbottombar(bottombar);
        StaticMethods.LoadImage(MainpageActivity.this, binding.toolbarpublic.imgProfile,AppConstant.userResponse.getIdImageUrl(),null);

    }
    public  void setViewHandling(String PointsString ,String wallet  ,boolean showback){
        if (AppConstant.userResponse != null) {
            binding.toolbarpublic.setPointsnumber(AppConstant.userResponse.getBalance() + "");
            binding.toolbarpublic.setWalletsnumber(AppConstant.userResponse.getChildsCount() + "");
            binding.toolbarpublic.setShowback(showback);
            binding.setShowbottombar(true);
            StaticMethods.LoadImage(MainpageActivity.this, binding.toolbarpublic.imgProfile, AppConstant.userResponse.getIdImageUrl(), null);
        }
    }
    public  void setViewHandling(String PointsString ,String wallet ) {
        try {
        binding.toolbarpublic.setPointsnumber(PointsString);
        binding.toolbarpublic.setWalletsnumber(wallet);
        }catch (Exception e) {}
    }
    public  void UpdatePointsHandling(String PointsString   ){
        try {
            AppConstant.userResponse.setBalance(Double.parseDouble(PointsString));
        }catch (Exception e) {}
        binding.toolbarpublic.setPointsnumber(PointsString);
        StaticMethods.LoadImage(MainpageActivity.this, binding.toolbarpublic.imgProfile,AppConstant.userResponse.getIdImageUrl(),null);
    }


    public  void setBundlevalue(Bundle bundle,int PageView){
        this.bundle=bundle;
        displayView(PageView);
    }


    public void logout() {
        StaticMethods.ClearChash();
        PrefUtils.SignOut_User(MainpageActivity.this);
        AppConstant.userResponse = null ;
        IntentUtiles.openActivityInNewStack(MainpageActivity.this, LoginActivity.class);
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



    @Override
    protected void onResume() {
        super.onResume();
        try {
            registerReceiver(netSwitchReceiver, new IntentFilter(AppConstant.ActionString));
        }
        catch (Exception e){ }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            unregisterReceiver(netSwitchReceiver);
        }
        catch (Exception e){ }
    }
    @Override
    protected void onPause() {
        super.onPause();
        try {
            unregisterReceiver(netSwitchReceiver);
        }
        catch (Exception e){ }
    }

    BroadcastReceiver netSwitchReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.hasExtra(UPDATE_POINTS)) {
                UpdatePointsHandling(intent.getExtras().getString(UPDATE_POINTS));
            }else if (intent.hasExtra(UPDATE_CHILD)){
                setViewHandling(intent.getExtras().getString(UPDATE_POINTS),intent.getExtras().getString(UPDATE_CHILD));
            }else if (intent.hasExtra(StarWallet) ){
              //  displayView(HOME_page);
            }
            else if ( intent.hasExtra(CanaddWallet)){
              //  displayView(HOME_page);
            }


        }
    };

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
