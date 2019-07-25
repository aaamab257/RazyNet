package com.razytech.razynet.gui.remainingpage;

import com.razytech.razynet.baseClasses.BaseView;
import com.razytech.razynet.data.beans.RemainingResponse;

/**
 * Created by A.Noby on 6/19/2019.
 */
 interface RemainingView  extends BaseView {
  void SetRemainingData(RemainingResponse remainingResponse ,  String Message  );
  void show_errorView(boolean Isshow , String error);
  void UpdateUserStatus(String messsage);
}
