package com.razytech.razynet.Utils;

import com.razytech.razynet.data.beans.AreaResponse;
import com.razytech.razynet.data.beans.ChildResponse;
import com.razytech.razynet.data.beans.CityResponse;
import com.razytech.razynet.data.beans.HomeResponse;
import com.razytech.razynet.data.beans.NotificationsResponse;
import com.razytech.razynet.data.beans.PointHistoryResponse;
import com.razytech.razynet.data.beans.PointsResponse;
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
    //   http://10.0.10.10:5200/
    public static final String Base_Url  =  "http://81.29.101.110:5201/api/";
    public  final static int REQUEST_CAMERA = 0, SELECT_FILE = 1
            , MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE=14
            , MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE=15
            ,PICK_FROM_GALLERY = 102,CALLL_PERMISION = 2
            ,MY_PERMISSIONS_REQUEST_CAMERA = 100,REQUEST_PICK_IMAGE = 1002;
   // private static final int REQUEST_PICK_IMAGE = 1002;
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
            CHANGEPASSWORD_page =9,
            REDEEM_page =10,
            TRANSFER_page =11,
            REDEEMPOINTS_page =12,
            CHILDDETAILS_page =13,
            MOVE_page =14,
            TRANSFERWALLET_page =20,
            TRANSFERPOINTS_page =21,
            TRANSFERCONFIRM_page =22,
            TRANSFERCONFIRMFINAL_page =23,
            TEST_page =50;


    public final static int
            NORMAL_notification =1,
            UPDATEPOINT_notification =2,
            UPDATECHILE_notification =3,
            STARWALLET_notification =4,
            CANADDWALLET_notification =5;


    public final static int
            MALE =1,
            FEMALE =2;
    public final static int
            BTN_All =1,
            BTN_IN =2,
            BTN_OUT =3;
    // inten keys
    public final static String RedeemidKey  =  "redeemid"
            ,RedeemnameKey  =  "redeemname",UPDATE_POINTS = "updatepoints",UPDATE_CHILD = "updatechilds",
            TokenKey  =  "token",phoneKey  =  "phonekey",
            ChildId  =  "childid",ChildName  =  "childname",
            ChildChilds  =  "childnumber",ChildMoved  =  "childmoved"
            ,ChildImage  =  "childimage",StarWallet  =  "starwallet",CanaddWallet  =  "canaddwallet"
            ,OpenNotification  =  "opennotification",
            ActionString = "com.android.ChangePoints";

    public static boolean refreshhome =  false ;

    public final static String Tokenbar =  "Bearer" ;

    public static UserResponse userResponse =  null ;
    public static List<RedeemResponse> redeemResponses =  null ;
    public static List<RedeemPointsResponse> redeemPointsResponses =  null ;
    public static List<NotificationsResponse> notificationsResponses =  null ;
    public static List<ChildResponse> childResponses =  null ;
    public static List<PointHistoryResponse> pointsResponses =  null ;
    public static List<CityResponse> cityResponses =  null ;
    public static List<AreaResponse> areaResponses =  null ;
    public static HomeResponse homeResponse = null ;
    public static List<ChildResponse> topsystem = null ;
    public static List<ChildResponse> topchilds = null ;




}
