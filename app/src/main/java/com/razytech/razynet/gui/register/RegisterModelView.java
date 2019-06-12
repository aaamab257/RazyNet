package com.razytech.razynet.gui.register;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.Log;

import com.razytech.razynet.R;
import com.razytech.razynet.Utils.AppConstant;
import com.razytech.razynet.Utils.StaticMethods;
import com.razytech.razynet.Utils.Validator;
import com.razytech.razynet.baseClasses.BaseViewModel;
import com.razytech.razynet.data.network.MainApi;
import com.razytech.razynet.data.network.MainApiBody;
import com.razytech.razynet.data.prefs.PrefUtils;

import org.json.JSONException;

import java.io.File;

/**
 * Created by A.Noby on 6/9/2019.
 */
 class RegisterModelView   extends BaseViewModel<RegisterView> {

 void vaildatedata(Context context, CoordinatorLayout coordinatorLayout , String UserName, String Phone, String nid, String password ,String confpassword,String gender ,File file, byte [] array) {

  boolean internetAvailable = StaticMethods.isConnectingToInternet(context);
  if (!internetAvailable) {
   view.showNoNetworkConnectionBase(coordinatorLayout,context);
   return;
  }

  if (Validator.isTextEmpty(UserName)) {
   view.showErrorMessageBase(context, context.getString(R.string.emptyuserName));
   return;
  }
  else  if (Validator.isTextEmpty(nid)) {
   view.showErrorMessageBase(context, context.getString(R.string.emptynid));
   return;
  }
  else  if (Validator.isTextEmpty(password)) {
   view.showErrorMessageBase(context, context.getString(R.string.emptyPassword));
   return;
  }
  else  if (Validator.isTextEmpty(confpassword)) {
   view.showErrorMessageBase(context, context.getString(R.string.emptyConfPassword));
   return;
  }
   view.OpenMainPage();
  //String Id = AppConstant.userResponse.getUser().getId();
 // SendEditProfile(context,coordinatorLayout,Id,Name,Phone,array,email,gender,file);
 }

 private void SendRegisterData(Context context, CoordinatorLayout coordinatorLayout,String id, String name, String phone, byte[] array, String email, String gender, File file) {

//  MultiPartImage multiPartImage =null;
//  try {
//   multiPartImage =   MainApiBody.EditProfileBoby(id,name,phone,email,gender, PrefUtils.getLanguage(context).Key,file);
//  } catch (JSONException e) {
//   e.printStackTrace();
//  }
//  view.showloadingviewBase(context);
//
//  MainApi.EditProfileapi(multiPartImage.imagereq,multiPartImage.Idreq,multiPartImage.namereq
//          ,multiPartImage.Phonereq,multiPartImage.emailreq,multiPartImage.genderreq,
//          multiPartImage.Langdreq,new ConnectionListener<MainResponse<UpdateUserData>>() {
//           @Override
//           public void onSuccess(ConnectionResponse<MainResponse<UpdateUserData>> connectionResponse) {
//            view.hideloadingviewBase();
//            if (connectionResponse.data.status ) {
//             view.UpdateUSerData(connectionResponse.data.data.getUser());
//            } else {
//             view.showErrorMessageBase(coordinatorLayout,context,connectionResponse.data.message);
//            }
//           }
//
//           @Override
//           public void onFail(Throwable throwable) {
//            view.hideloadingviewBase();
//            view.showErrorMessageBase(coordinatorLayout,context,context.getString(R.string.tryagaing));
//            Log.e("error", throwable.toString());
//           }
//          });
 }

}
