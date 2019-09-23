package com.razytech.razynet.gui.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.razytech.razynet.Utils.AppConstant;

import static com.razytech.razynet.Utils.AppConstant.UPDATE_CHILD;
import static com.razytech.razynet.Utils.AppConstant.UPDATE_POINTS;


public class ChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.hasExtra(UPDATE_POINTS)) {
            AppConstant.userResponse.setBalance(Double.parseDouble(intent.getExtras().getString(UPDATE_POINTS)));
            if (intent.hasExtra(UPDATE_CHILD)){
                AppConstant.userResponse.setChildsCount(Integer.parseInt(intent.getExtras().getString(UPDATE_CHILD)));
            }
        }

    }

}