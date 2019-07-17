package com.razytech.razynet.gui.maintransaction.transfer;

import com.razytech.razynet.baseClasses.BaseView;
import com.razytech.razynet.data.beans.ChildResponse;

import java.util.List;

/**
 * Created by A.Noby on 6/11/2019.
 */
 interface TransferView  extends BaseView {
 void SetStepsHandlingView(int TranFerPage);
 void SetPointValue(String Points);
 void LoadingchildData (List<ChildResponse> childResponses );
 void show_errorView(boolean Isshow , String error);
 void UpdatePoints(double Points);
}
