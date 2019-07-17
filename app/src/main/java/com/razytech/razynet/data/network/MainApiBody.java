package com.razytech.razynet.data.network;

import android.support.annotation.NonNull;

import com.razytech.razynet.data.beans.MultiPartImage;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by A.Noby on 4/7/2019.
 */
public class MainApiBody {

    private static final String JSON_TYPE = "application/json";

    @NonNull
    private static RequestBody requestBody(JSONObject jsonBody) {
        return RequestBody.create(MediaType.parse(JSON_TYPE), jsonBody.toString());
    }

    public static RequestBody VerifyCodeBoby(String code ) throws JSONException {
        JSONObject params=new JSONObject();
        params.put("code", code);
        return requestBody(params);
    }

    public static RequestBody InviteBoby(String mobilenumber ) throws JSONException {
        JSONObject params=new JSONObject();
        params.put("mobileNo", mobilenumber);
        return requestBody(params);
    }

    public static MultiPartImage RegisterBoby(String UserName, String CityId, String AreaId,
                                                 String IdentityNo, String Password, File file) throws JSONException {
        RequestBody CityIdreq=null ,AreaIdreq = null ,
                IdentityNoreq = null ,Passwordreq = null , UserNamereq  = null  ;

        MultipartBody.Part imagereq =null ;
        if(file != null) {
            RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
            imagereq = MultipartBody.Part.createFormData("IdentityImage", file.getName(), reqFile);
        }
        if(UserName != null) {
            UserNamereq = RequestBody.create(MediaType.parse(JSON_TYPE), UserName); }
        if(CityId != null) {
            CityIdreq = RequestBody.create(MediaType.parse(JSON_TYPE), CityId);
        }
        if(AreaId != null) {
            AreaIdreq = RequestBody.create(MediaType.parse(JSON_TYPE), AreaId);
        }
        if(IdentityNo != null) {
            IdentityNoreq = RequestBody.create(MediaType.parse(JSON_TYPE), IdentityNo); }
        if(Password != null) {
            Passwordreq = RequestBody.create(MediaType.parse(JSON_TYPE), Password); }

        MultiPartImage multiPartImage =new MultiPartImage() ;
        multiPartImage.imagereq = imagereq ;
        multiPartImage.CityIdreq = CityIdreq ;
        multiPartImage.AreaIdreq = AreaIdreq ;
        multiPartImage.IdentityNoreq = IdentityNoreq ;
        multiPartImage.Passwordreq = Passwordreq ;
        multiPartImage.UserNamereq = UserNamereq ;
        return multiPartImage;
    }
    public static RequestBody GetChildBoby(String PhoneNumber ) throws JSONException {
        JSONObject params=new JSONObject();
        params.put("phone", PhoneNumber);
        return requestBody(params);
    }
    public static RequestBody GetChildListBoby(String WalletId ) throws JSONException {
        JSONObject params=new JSONObject();
        params.put("WalletId", WalletId);
        return requestBody(params);
    }

    public static RequestBody TransferBoby(String PhoneNumber ,String points   ) throws JSONException {
        JSONObject params=new JSONObject();
        params.put("phoneNumber", PhoneNumber);
        params.put("points", points);
        return requestBody(params);
    }

}
