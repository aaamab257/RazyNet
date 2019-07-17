package com.razytech.razynet.gui.homepage;

import com.razytech.razynet.baseClasses.BaseView;
import com.razytech.razynet.data.beans.ChildResponse;
import com.razytech.razynet.data.beans.UserResponse;

import java.util.List;

/**
 * Created by A.Noby on 6/10/2019.
 */
 interface HomeView  extends BaseView {

  void LoadWalletSystem(List<ChildResponse> topsystem);
  void LoadWallet(List<ChildResponse> topchilds);
  void UpdateUserData(UserResponse userResponse);
  void showerrorlayoutTopsystem(boolean show  ,String errortxt);
  void showerrorlayoutchilds(boolean show  ,String errortxt);
}
