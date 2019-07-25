package com.razytech.razynet.gui.childDetails;

import com.razytech.razynet.baseClasses.BaseView;
import com.razytech.razynet.data.beans.ChildResponse;

import java.util.List;

/**
 * Created by A.Noby on 7/2/2019.
 */
 interface ChildDetailsView extends BaseView {
  void LoadingchildData(List<ChildResponse> childResponses);
  void show_errorView(boolean Isshow , String error);
}
