package com.razytech.razynet.gui.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.razytech.razynet.Utils.AppConstant;

import static com.razytech.razynet.Utils.AppConstant.CanaddWallet;
import static com.razytech.razynet.Utils.AppConstant.StarWallet;
import static com.razytech.razynet.Utils.AppConstant.UPDATE_CHILD;
import static com.razytech.razynet.Utils.AppConstant.UPDATE_POINTS;


public class ChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.hasExtra(UPDATE_POINTS)) {
            Log.e("here","updatepoi");
            AppConstant.userResponse.setBalance(Double.parseDouble(intent.getExtras().getString(UPDATE_POINTS)));
            if (intent.hasExtra(UPDATE_CHILD)){
                AppConstant.userResponse.setChildsCount(Integer.parseInt(intent.getExtras().getString(UPDATE_CHILD)));
            }
        }else  if(intent.hasExtra(StarWallet)){
            AppConstant.userResponse.setStarWallet(true);

            Log.e("here","star");
        }
        else  if(intent.hasExtra(CanaddWallet)){
            AppConstant.userResponse.setCanCredit(true);
            AppConstant.refreshhome =  true ;
            Log.e("here","add");
        }

    }

}