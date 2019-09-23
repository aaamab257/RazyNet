package com.razytech.razynet.data.network;

import android.support.annotation.NonNull;

import com.google.firebase.iid.FirebaseInstanceId;
import com.razytech.razynet.data.beans.MultiPartImage;
import com.razytech.razynet.data.beans.MultiPartNid;
import com.razytech.razynet.data.beans.MultiPartUpdate;

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
                                                 String IdentityNo, String Password,String TokenDev, File file) throws JSONException {
        RequestBody CityIdreq=null ,AreaIdreq = null ,
                IdentityNoreq = null ,Passwordreq = null , UserNamereq  = null  , Tokenreq  = null  ;

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


        if(TokenDev != null) {
            Tokenreq = RequestBody.create(MediaType.parse(JSON_TYPE), TokenDev); }

        MultiPartImage multiPartImage =new MultiPartImage() ;
        multiPartImage.imagereq = imagereq ;
        multiPartImage.CityIdreq = CityIdreq ;
        multiPartImage.AreaIdreq = AreaIdreq ;
        multiPartImage.IdentityNoreq = IdentityNoreq ;
        multiPartImage.Passwordreq = Passwordreq ;
        multiPartImage.UserNamereq = UserNamereq ;
        multiPartImage.Tokenreq = Tokenreq ;
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

    public static MultiPartNid UploadNidBoby(String NIDNumber , File file) throws JSONException {
        RequestBody NIDNumberreq=null  ;
        MultipartBody.Part imagereq =null ;
        if(file != null) {
            RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
            imagereq = MultipartBody.Part.createFormData("IdentityImage", file.getName(), reqFile);
        }
        if(NIDNumber != null) {
            NIDNumberreq = RequestBody.create(MediaType.parse(JSON_TYPE), NIDNumber); }


        MultiPartNid multiPartImage =new MultiPartNid() ;
        multiPartImage.imagereq = imagereq ;
        multiPartImage.NIDNumberreq = NIDNumberreq ;
        return multiPartImage;
    }


    public static RequestBody GetAvailableBoby(String WalletId ) throws JSONException {
        JSONObject params=new JSONObject();
        params.put("walletId", WalletId);
        return requestBody(params);
    }
//    {
//        "newRootWalletId": "string",
//            "childWalletId": "string"
//    }
public static RequestBody MoveBoby(String WalletId  , String newRoot) throws JSONException {
    JSONObject params=new JSONObject();
    params.put("newRootWalletId", newRoot);
    params.put("childWalletId", WalletId);
    return requestBody(params);
}


    public static MultiPartUpdate UpdateBoby(String UserName, String CityId, String AreaId, File file) throws JSONException {

        RequestBody CityIdreq=null ,AreaIdreq = null , UserNamereq  = null  ;

        MultipartBody.Part imagereq =null ;
        if(file != null) {
            RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
            imagereq = MultipartBody.Part.createFormData("UserImageUrl", file.getName(), reqFile);
        }
        if(UserName != null) {
            UserNamereq = RequestBody.create(MediaType.parse(JSON_TYPE), UserName); }
        if(CityId != null) {
            CityIdreq = RequestBody.create(MediaType.parse(JSON_TYPE), CityId);
        }
        if(AreaId != null) {
            AreaIdreq = RequestBody.create(MediaType.parse(JSON_TYPE), AreaId);
        }


        MultiPartUpdate multiPartImage =new MultiPartUpdate() ;
        multiPartImage.imagereq = imagereq ;
        multiPartImage.CityIdreq = CityIdreq ;
        multiPartImage.AreaIdreq = AreaIdreq ;
        multiPartImage.Namereq = UserNamereq ;
        return multiPartImage;
    }


    public static RequestBody LoginBoby(String PhoneNumber  , String Password ) throws JSONException {
        JSONObject params=new JSONObject();
        params.put("phone", PhoneNumber);
        params.put("password", Password);
        params.put("DeviceId", FirebaseInstanceId.getInstance().getToken().toString());

        return requestBody(params);
    }


    public static RequestBody ChangePasswordBoby(String newPassword  , String oldPassword ) throws JSONException {
        JSONObject params=new JSONObject();
        params.put("oldPassWord", oldPassword);
        params.put("newPassWord", newPassword);


        return requestBody(params);
    }


    public static RequestBody PasswordBoby(String Password ) throws JSONException {
        JSONObject params=new JSONObject();
        params.put("password", Password);
        return requestBody(params);
    }

    public static RequestBody RedeemPointBoby(String id ) throws JSONException {
        JSONObject params=new JSONObject();
        params.put("id", id);
        return requestBody(params);
    }

}
