package com.razytech.razynet.gui.pointhistory;

import com.razytech.razynet.baseClasses.BaseView;
import com.razytech.razynet.data.beans.ChildResponse;
import com.razytech.razynet.data.beans.NotificationsResponse;
import com.razytech.razynet.data.beans.PointHistoryResponse;
import com.razytech.razynet.data.beans.PointsResponse;

import java.util.List;

/**
 * Created by A.Noby on 6/11/2019.
 */
 interface PointsHistoryView extends BaseView {
 void   LoadingPointsData (List<PointHistoryResponse> pointsResponses );
 void show_errorView(boolean show  ,String errortxt);
}
