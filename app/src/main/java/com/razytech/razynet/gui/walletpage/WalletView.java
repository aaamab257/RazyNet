package com.razytech.razynet.gui.walletpage;

import com.razytech.razynet.baseClasses.BaseView;
import com.razytech.razynet.data.beans.ChildResponse;

import java.util.List;

/**
 * Created by A.Noby on 6/11/2019.
 */
interface WalletView extends BaseView  {
    void   LoadingchildData (List<ChildResponse> childResponses );
    void   hide_refreshView();
}
