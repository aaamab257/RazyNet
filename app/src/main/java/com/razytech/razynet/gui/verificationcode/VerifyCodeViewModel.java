package com.razytech.razynet.gui.verificationcode;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;

import com.razytech.razynet.R;
import com.razytech.razynet.Utils.StaticMethods;
import com.razytech.razynet.Utils.Validator;
import com.razytech.razynet.baseClasses.BaseViewModel;

import java.io.File;

/**
 * Created by A.Noby on 4/7/2019.
 */
 class VerifyCodeViewModel extends BaseViewModel<VerifyCodeView> {




 void vaildatedata(Context context, CoordinatorLayout coordinatorLayout , String Invitationcode) {

  boolean internetAvailable = StaticMethods.isConnectingToInternet(context);
  if (!internetAvailable) {
   view.showNoNetworkConnectionBase(coordinatorLayout,context);
   return;
  }
  if (Validator.isTextEmpty(Invitationcode)) {
   view.showErrorMessageBase(context, context.getString(R.string.emptyinvcode));
   return;
  }

  view.OpenRegister();
  //String Id = AppConstant.userResponse.getUser().getId();
  // SendEditProfile(context,coordinatorLayout,Id,Name,Phone,array,email,gender,file);
 }

}
