package com.razytech.razynet.gui.register;

import android.hardware.Camera;

import com.razytech.razynet.baseClasses.BaseView;
import com.razytech.razynet.data.beans.AreaResponse;
import com.razytech.razynet.data.beans.CityResponse;
import com.razytech.razynet.data.beans.UserResponse;

import java.util.List;

/**
 * Created by A.Noby on 6/9/2019.
 */
 interface RegisterView  extends BaseView {
  void OpenMainPage();
 void LoadCitydata(List<CityResponse> cityResponses);
 void LoadAreadata(List<AreaResponse>  areaResponses);
 void  SavingData (UserResponse userResponse ) ;
}
