package com.razytech.razynet.gui.updateprofile;

import com.razytech.razynet.baseClasses.BaseView;
import com.razytech.razynet.data.beans.AreaResponse;
import com.razytech.razynet.data.beans.CityResponse;
import com.razytech.razynet.data.beans.UserResponse;

import java.util.List;

/**
 * Created by A.Noby on 6/11/2019.
 */
 interface UpdateProfileView  extends BaseView {

 void LoadCitydata(List<CityResponse> cityResponses);
 void LoadAreadata(List<AreaResponse>  areaResponses);
 void  SavingData (UserResponse userResponse ) ;
}
