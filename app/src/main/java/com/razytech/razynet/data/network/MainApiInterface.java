package com.razytech.razynet.data.network;

import com.razytech.razynet.data.beans.AreaResponse;
import com.razytech.razynet.data.beans.ChildResponse;
import com.razytech.razynet.data.beans.CityResponse;
import com.razytech.razynet.data.beans.HomeResponse;
import com.razytech.razynet.data.beans.InviteResponse;
import com.razytech.razynet.data.beans.NotificationsResponse;
import com.razytech.razynet.data.beans.RedeemPointsResponse;
import com.razytech.razynet.data.beans.RedeemResponse;
import com.razytech.razynet.data.beans.RedeemUpdateResponse;
import com.razytech.razynet.data.beans.RemainingResponse;
import com.razytech.razynet.data.beans.TransferResponse;
import com.razytech.razynet.data.beans.UploadNidResponse;
import com.razytech.razynet.data.beans.UserResponse;
import com.razytech.razynet.data.beans.VerifyCodeResponse;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by A.Noby on 4/7/2019.
 */
public interface MainApiInterface {


    // 1
    @POST("Verification")
    Observable<MainResponse<VerifyCodeResponse>> VerifyCodePage(@Body RequestBody RequestBody);

    // 2
    @GET("City")
    Observable<MainResponse<List<CityResponse>>> CityPage(@Header("Authorization") String auth);

    // 3
    @GET("Area/ByCity/{id}")
    Observable<MainResponse<List<AreaResponse>>> AreaPage(@Header("Authorization") String auth,@Path("id") String id);

    // 4
    @Multipart
    @PUT("Wallet/Register")
    Observable<MainResponse<UserResponse>> SignUpPage(@Header("Authorization") String auth,
                                                      @Part MultipartBody.Part image, @Part("DisplayName") RequestBody DisplayName,
                                                      @Part("CityId") RequestBody CityId, @Part("AreaId") RequestBody AreaId
                                                    , @Part("IdentityNo") RequestBody IdentityNo, @Part("Password") RequestBody Password);


    // 5
    @POST("Wallet/Invite")
    Observable<MainResponse<InviteResponse>> InvitePage(@Header("Authorization") String auth,@Body RequestBody RequestBody);

    // 6
    @GET("Categories")
    Observable<MainResponse<List<RedeemResponse>>> RedeemPage(@Header("Authorization") String auth);

    // 7
    @GET("Categories/{id}/products")
    Observable<MainResponse<List<RedeemPointsResponse>>> RedeemPointsPage(@Header("Authorization") String auth  , @Path("id") String id);

    // 8
    @POST("Wallet/Childrens")
    Observable<MainResponse<List<ChildResponse>>> GetChildrensPage(@Header("Authorization") String auth, @Body RequestBody RequestBody );

    // 9
    @POST("Wallet/WalletByPhone")
    Observable<MainResponse<List<ChildResponse>>> GetChildrensByPhonePage(@Header("Authorization") String auth , @Body RequestBody RequestBody );

    // 10
    @GET("Notification")
    Observable<MainResponse<List<NotificationsResponse>>> NotiifcationsPage(@Header("Authorization") String auth );

    // 11
    @GET("Wallet/Home")
    Observable<MainResponse<HomeResponse>> HomePage(@Header("Authorization") String auth );

    // 12
    @POST("Wallet/TransferPoints")
    Observable<MainResponse<TransferResponse>> TransferPage(@Header("Authorization") String auth, @Body RequestBody RequestBody );

    // 13
    @PUT("Wallet/SuspendedCountDown")
    Observable<MainResponse<RemainingResponse>> RemainingPage(@Header("Authorization") String auth );


    // 14
    @Multipart
    @POST("Wallet/UploadIdenityImage")
    Observable<MainResponse<UploadNidResponse>> UploadNidImagePage(@Header("Authorization") String auth
            , @Part MultipartBody.Part image, @Part("IdentityNumber") RequestBody DisplayName);


    // 15
    @POST("Wallet/AvailableWallets")
    Observable<MainResponse<List<ChildResponse>>> GetAvailablePage(@Header("Authorization") String auth
                                                                 , @Body RequestBody RequestBody );


    // 16
    @PUT("Wallet/MoveChildWallet")
    Observable<MainResponse<TransferResponse>> MovePage(@Header("Authorization") String auth
                                                             , @Body RequestBody RequestBody);


    // 17
    @Multipart
    @POST("Wallet/UpdateProfile")
    Observable<MainResponse<UserResponse>> UpdateProfilePage(@Header("Authorization") String auth,
                                                      @Part MultipartBody.Part image, @Part("DisplayName") RequestBody DisplayName,
                                                      @Part("CityId") RequestBody CityId, @Part("AreaId") RequestBody AreaId);

    // 18
    @POST("User/LoginMobile")
    Observable<MainResponse<UserResponse>> LoginPage(@Body RequestBody RequestBody);


    // 19
    @POST("User/ConfirmPassword")
    Observable<MainResponse<UserResponse>> ConfirmPasswordPage(@Header("Authorization") String auth
            ,@Body RequestBody RequestBody);

    // 20
    @POST("Product/Execute")
    Observable<MainResponse<RedeemUpdateResponse>> RedeemUpdatePage(@Header("Authorization") String auth
            , @Body RequestBody RequestBody);


}
