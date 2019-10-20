package com.razytech.razynet.baseClasses;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.razytech.razynet.R;
import com.razytech.razynet.Utils.StaticMethods;
import com.razytech.razynet.Utils.ToastUtil;

import dmax.dialog.SpotsDialog;

/**
 * Created by A.Noby on 4/7/2019.
 */
public class BaseFragment extends Fragment implements BaseView {


    public BaseViewModel<? extends BaseView> presenter;
    SpotsDialog dialog;

    @Override
    public Context getContextBase() {
        return getActivity();
    }

    @Override
    public void showErrorMessageBase(CoordinatorLayout coordinatorLayout, Context context, String errormessage){
        StaticMethods.ShowSnake(coordinatorLayout,context,errormessage);
    }

    public  void hideKeyboard(){
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
//        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }

    @Override
    public void showNoNetworkConnectionBase(CoordinatorLayout coordinatorLayout, Context context){
        StaticMethods.NOConnecting(coordinatorLayout,context);
    }
    @Override
    public void showErrorMessageBase( Context context, String errormessage){
        ToastUtil.showErrorToast(context,errormessage);
    }

    @Override
    public void showNoNetworkConnectionBase( Context context){
        ToastUtil.showErrorToast(context,context.getString(R.string.noconnection));
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //  presenter = getPresenter();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        presenter.detachView();
//    }
    //   public abstract BasePresenter<? extends BaseView> getPresenter();


    private void spotDialoug(Context context){
        dialog = new SpotsDialog(context,getString(R.string.loading));
        dialog.setCancelable(false);
        dialog.show();
    }
}
