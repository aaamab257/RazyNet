package com.razytech.razynet.gui.verificationcode;

import com.razytech.razynet.baseClasses.BaseView;
import com.razytech.razynet.data.beans.VerifyCodeResponse;

/**
 * Created by A.Noby on 4/7/2019.
 */
interface VerifyCodeView extends BaseView {
    void OpenRegister();
    void SaveVerifyData(VerifyCodeResponse  response);
}
