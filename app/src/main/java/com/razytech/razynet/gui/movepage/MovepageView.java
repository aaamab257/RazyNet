package com.razytech.razynet.gui.movepage;

import com.razytech.razynet.baseClasses.BaseView;
import com.razytech.razynet.data.beans.ChildResponse;

import java.util.List;

/**
 * Created by A.Noby on 7/8/2019.
 */
  interface MovepageView extends BaseView {
  void LoadingchildData(List<ChildResponse> childResponses);
  void show_errorView(boolean Isshow , String error);
  void After_ChildMove(double updatePoints);
  }
