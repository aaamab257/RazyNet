package com.razytech.razynet.gui.maintransaction.redeem;

import com.razytech.razynet.baseClasses.BaseView;
import com.razytech.razynet.data.beans.RedeemResponse;

import java.util.List;

/**
 * Created by A.Noby on 6/11/2019.
 */
 interface RedeemView  extends BaseView {

   void   LoadingReddemData (List<RedeemResponse>  redeemResponses );
   void  hide_refreshView();
   void show_errorView(boolean Isshow , String error);
}
