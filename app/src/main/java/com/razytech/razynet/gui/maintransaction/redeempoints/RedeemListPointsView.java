package com.razytech.razynet.gui.maintransaction.redeempoints;

import com.razytech.razynet.baseClasses.BaseView;
import com.razytech.razynet.data.beans.RedeemPointsResponse;
import com.razytech.razynet.data.beans.RedeemResponse;

import java.util.List;

/**
 * Created by A.Noby on 6/12/2019.
 */
 interface RedeemListPointsView  extends BaseView {


 void   LoadingReddemData (List<RedeemPointsResponse> redeemResponses );
 void  hide_refreshView();
}
