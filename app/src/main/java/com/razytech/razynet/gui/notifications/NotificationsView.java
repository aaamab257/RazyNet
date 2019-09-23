package com.razytech.razynet.gui.notifications;

import com.razytech.razynet.baseClasses.BaseView;
import com.razytech.razynet.data.beans.NotificationsResponse;
import com.razytech.razynet.data.beans.RedeemResponse;

import java.util.List;

/**
 * Created by A.Noby on 6/11/2019.
 */
 interface NotificationsView  extends BaseView  {
 void   LoadingnotificationData (List<NotificationsResponse> notificationsResponses );
 void  hide_refreshView();
 void show_errorView(boolean Isshow , String error);

}
