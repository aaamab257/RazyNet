package com.razytech.razynet.gui.push;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyfireBaseInstanceIDstraw extends FirebaseInstanceIdService {


    public  static  final String TAG="ANY";

    @Override
    public void onTokenRefresh() {

        String refereshtoken= FirebaseInstanceId.getInstance().getToken();
        Log.e("refereshtoken",refereshtoken);

    }

}
