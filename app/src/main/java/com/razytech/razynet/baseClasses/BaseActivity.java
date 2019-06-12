package com.razytech.razynet.baseClasses;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.razytech.razynet.R;
import com.razytech.razynet.Utils.StaticMethods;
import com.razytech.razynet.Utils.ToastUtil;
import com.razytech.razynet.app.RazyNetApp;

import dagger.android.AndroidInjection;
import dmax.dialog.SpotsDialog;

/**
 * Created by A.Noby on 4/7/2019.
 */
public  abstract class BaseActivity<T extends ViewDataBinding,
        V extends BaseViewModel> extends AppCompatActivity
        implements BaseView{

    SpotsDialog dialog;
    private T mViewDataBinding;
    private V mViewModel;



    public abstract
    @LayoutRes
    int getLayoutId();

    @Override
    public Context getContextBase() {
        return RazyNetApp.getAppContext();
    }

    @Override
    public void showErrorMessageBase(CoordinatorLayout coordinatorLayout, Context context, String errormessage){
        StaticMethods.ShowSnake(coordinatorLayout,context,errormessage);
    }
    @Override
    public void showErrorMessageBase( Context context, String errormessage){
        ToastUtil.showErrorToast(context,errormessage);
    }

    @Override
    public void showNoNetworkConnectionBase(CoordinatorLayout coordinatorLayout, Context context){
        StaticMethods.NOConnecting(coordinatorLayout,context);
    }
    @Override
    public void showNoNetworkConnectionBase( Context context){
        ToastUtil.showErrorToast(context,context.getString(R.string.noconnection));
    }

    public  void hideKeyboard(){
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }


    @Override
    public void showSuccessMessageBase(Context context, String message) {
        ToastUtil.showSuccessToast(context,message);
    }

    @Override
    public void showSuccessMessageBase(CoordinatorLayout coordinatorLayout, Context context, String message) {
        StaticMethods.ShowSnake(coordinatorLayout,context,message);
    }

    @Override
    public void showloadingviewBase(Context context){
        spotDialoug(context);
    }

    @Override
    public void hideloadingviewBase(){
        if(dialog!=null){
            dialog.dismiss();
        }
    }
//    //public void performDependencyInjection() {
//        AndroidInjection.inject(this);
//    }


    private void spotDialoug(Context context){
        dialog = new SpotsDialog(context,getString(R.string.loading));
        dialog.setCancelable(false);
        dialog.show();
    }

    public T getViewDataBinding() {
        return mViewDataBinding;
    }
    public abstract V getViewModel();
    public abstract int getBindingVariable();

    private void performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        this.mViewModel = mViewModel == null ? getViewModel() : mViewModel;
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
        mViewDataBinding.executePendingBindings();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mViewModel != null)
            mViewModel.detachView();
    }
    public void performDependencyInjection() {
        AndroidInjection.inject(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        performDependencyInjection();
        super.onCreate(savedInstanceState);
        mViewModel =  getViewModel() ;
        performDataBinding();
    }

}
