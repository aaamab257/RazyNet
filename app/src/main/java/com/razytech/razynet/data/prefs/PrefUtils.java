package com.razytech.razynet.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.razytech.razynet.Utils.AppConstant;
import com.razytech.razynet.data.beans.UserResponse;
import static com.razytech.razynet.data.prefs.PrefKeys.App_RazyNet;

/**
 * Created by A.Noby on 4/7/2019.
 */
public class PrefUtils {


    public static final String Is_First_open = "firstopen",
            User_data = "Userdata", Status_User = "status_user",
            Language_List = "languagelist", Language_Selected = "languageselected",
            Country_List = "countrylist", Country_Selected = "Countryselected";

    public static final int  User_Singin = 1 ,
            User_Singout = 0 ,  User_Verify = 2 ;


    public static void saveOpenStatus(boolean indexlang, Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences(App_RazyNet, Context.MODE_PRIVATE).edit();
        editor.putBoolean(Is_First_open, indexlang);
        editor.commit();
    }

    public static boolean getOpenStatus(Context context) {
        String lang = "";
        boolean status = context.getSharedPreferences(App_RazyNet, Context.MODE_PRIVATE).getBoolean(Is_First_open, false);
        if (!status) {
            return true;
        }else {
            saveOpenStatus(true,context);
            return false;
        }
    }

    public static void saveUserinformation(Context context, UserResponse consumerResponse, int status ) {
        SharedPreferences sharedpreferences = context.getSharedPreferences(App_RazyNet, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        Gson gson = new Gson();
        String Userdatavalue = gson.toJson(consumerResponse);
        editor.putString(User_data, Userdatavalue);
        editor.putInt(Status_User,status);
        editor.commit();
    }
    public static Boolean getUserformation(Context context) {
        SharedPreferences sharedpreferences = context.getSharedPreferences(App_RazyNet, Context.MODE_PRIVATE);
        if (sharedpreferences.getInt(Status_User, User_Singout) != User_Singout){
            AppConstant.userResponse=new UserResponse();
            String userdatavalue = sharedpreferences.getString(User_data, null);
            AppConstant.userResponse =  new Gson().fromJson(userdatavalue, UserResponse.class);
            AppConstant.User_Status = sharedpreferences.getInt(Status_User, User_Singout);
            AppConstant.auth = AppConstant.userResponse.getToken();
            return  true;
        }else {
            return  false;
        }
    }

    public static  void  SignOut_User(Context context){
        SharedPreferences sharedpreferences = context.getSharedPreferences(App_RazyNet, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putInt(Status_User,User_Singout);
        editor.commit();
    }

    public static  int User_Status(Context context){
        SharedPreferences sharedpreferences = context.getSharedPreferences(App_RazyNet, Context.MODE_PRIVATE);
        return   sharedpreferences.getInt(Status_User, User_Verify);
    }


//    public static void saveLanguage(Context context , List<LanguageResponse> LangList , String languageSelected ) {
//        SharedPreferences.Editor editor = context.getSharedPreferences(App_RazyNet, Context.MODE_PRIVATE).edit();
//        Gson  gson = new Gson();
//        String jsonLang = gson.toJson(LangList);
//        editor.putString(Language_List, jsonLang);
//        editor.putString(Language_Selected, languageSelected);
//        editor.commit();
//    }


}
