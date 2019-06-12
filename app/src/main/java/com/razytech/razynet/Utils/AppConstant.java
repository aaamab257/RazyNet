package com.razytech.razynet.Utils;

import com.razytech.razynet.data.beans.RedeemPointsResponse;
import com.razytech.razynet.data.beans.RedeemResponse;
import com.razytech.razynet.data.beans.UserResponse;

import java.util.List;

/**
 * Created by A.Noby on 4/7/2019.
 */
public  final class AppConstant {

    public static   String lang =  "ar";
    public static   String auth = "";
    public static int  User_Status;

    public  final static int REQUEST_CAMERA = 0, SELECT_FILE = 1
            , MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE=14
            , MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE=15
            ,PICK_FROM_GALLERY = 102,CALLL_PERMISION = 2
            ,MY_PERMISSIONS_REQUEST_CAMERA = 100,INTENT_PERMISION = 44;

    public final static int
            HOME_page =0,
            TREE_page =1,
            ADDWALLET_PAGE =2,
            MAINTRANSACTION_page =3,
            NOTIFICATION_page =4,
            POINTS_page =5,
            WALLET_page =6,
            PROFILE_page =7,
            UPDATEPROFILE_page =8,
            REDEEM_page =9,
            TRANSFER_page =10;

    public final static int
            MALE =1,
            FEMALE =2;


    public static UserResponse userResponse =  null ;

    public static List<RedeemResponse> redeemResponses =  null ;
    public static List<RedeemPointsResponse> redeemPointsResponses =  null ;


}
