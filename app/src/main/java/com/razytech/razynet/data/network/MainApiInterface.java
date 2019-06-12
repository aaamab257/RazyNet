package com.razytech.razynet.data.network;

import com.razytech.razynet.data.beans.UserResponse;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by A.Noby on 4/7/2019.
 */
public interface MainApiInterface {


    // 1
    @FormUrlEncoded
    @POST("sign_in")
    Observable<MainResponse<UserResponse>> LoginPage(@FieldMap HashMap<String, String> hashMap);
    // 2
    @FormUrlEncoded
    @POST("sign_up")
    Observable<MainResponse<UserResponse>> SignUpPage(@FieldMap HashMap<String, String> hashMap);
}
