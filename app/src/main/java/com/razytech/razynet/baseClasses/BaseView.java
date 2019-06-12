package com.razytech.razynet.baseClasses;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;

/**
 * Created by A.Noby on 4/7/2019.
 */
public interface BaseView {
    void showErrorMessageBase(CoordinatorLayout coordinatorLayout, Context context, String errormessage);
    void showNoNetworkConnectionBase(CoordinatorLayout coordinatorLayout, Context context);
    void showErrorMessageBase( Context context, String errormessage);
    void showSuccessMessageBase( Context context, String errormessage);
    void showSuccessMessageBase( CoordinatorLayout coordinatorLayout,Context context, String errormessage);
    void showNoNetworkConnectionBase(Context context);
    void showloadingviewBase(Context context);
    void hideloadingviewBase();
    Context getContextBase();

}
