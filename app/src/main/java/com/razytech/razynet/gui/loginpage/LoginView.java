package com.razytech.razynet.gui.loginpage;

import com.razytech.razynet.baseClasses.BaseView;
import com.razytech.razynet.data.beans.UserResponse;

/**
 * Created by A.Noby on 7/22/2019.
 */
interface LoginView extends BaseView {

    void  SavingData (UserResponse userResponse ) ;
    void  OpenMainPage();
}
