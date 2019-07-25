package com.razytech.razynet.data.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.razytech.razynet.BuildConfig;
import com.razytech.razynet.Utils.AppConstant;
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
import com.razytech.razynet.gui.verificationcode.VerifyCodeActivity;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by A.Noby on 4/7/2019.
 */
public class MainApi {



    private static MainApiInterface getApi() {
        return getApi(MainApiInterface.class);
    }

    public static <T> T getApi(Class<T> aClass) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS).addInterceptor(interceptor).build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit =  new Retrofit.Builder()
                .baseUrl(AppConstant.Base_Url)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();


        return retrofit.create(aClass);
    }


    public static void VerifyCodeapi(RequestBody body
            , final ConnectionListener<MainResponse<VerifyCodeResponse>> connectionListener) {
        getApi().VerifyCodePage(body).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MainResponse<VerifyCodeResponse>>() {
                    @Override
                    public void onError(Throwable e) { connectionListener.onFail(e); }
                    @Override
                    public void onComplete() { }
                    @Override
                    public void onSubscribe(Disposable d) { }
                    @Override
                    public void onNext(MainResponse<VerifyCodeResponse> aBoolean) {
                        ConnectionResponse<MainResponse<VerifyCodeResponse>> response = new ConnectionResponse<>();
                        response.data = aBoolean;
                        connectionListener.onSuccess(response);
                    }

                });
    }



    public static void Cityapi(String token, final ConnectionListener<MainResponse<List<CityResponse>>> connectionListener) {
        getApi().CityPage(token).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MainResponse<List<CityResponse>>>() {
                    @Override
                    public void onError(Throwable e) { connectionListener.onFail(e); }
                    @Override
                    public void onComplete() { }
                    @Override
                    public void onSubscribe(Disposable d) { }
                    @Override
                    public void onNext(MainResponse<List<CityResponse>> aBoolean) {
                        ConnectionResponse<MainResponse<List<CityResponse>>> response = new ConnectionResponse<>();
                        response.data = aBoolean;
                        connectionListener.onSuccess(response);
                    }

                });
    }

    public static void Areaapi(String token, String cityId ,  final ConnectionListener<MainResponse<List<AreaResponse>>> connectionListener) {
        getApi().AreaPage(token,cityId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MainResponse<List<AreaResponse>>>() {
                    @Override
                    public void onError(Throwable e) { connectionListener.onFail(e); }
                    @Override
                    public void onComplete() { }
                    @Override
                    public void onSubscribe(Disposable d) { }
                    @Override
                    public void onNext(MainResponse<List<AreaResponse>> aBoolean) {
                        ConnectionResponse<MainResponse<List<AreaResponse>>> response = new ConnectionResponse<>();
                        response.data = aBoolean;
                        connectionListener.onSuccess(response);
                    }

                });
    }

    public static void Registerapi(String auth  , MultipartBody.Part fileToUpload, RequestBody username, RequestBody cityid,
                                      RequestBody areaid, RequestBody nid, RequestBody password,
                                      final ConnectionListener<MainResponse<UserResponse>> connectionListener) {
        getApi().SignUpPage(auth ,fileToUpload,username,cityid,areaid,nid,password).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MainResponse<UserResponse>>() {
                    @Override
                    public void onError(Throwable e) { connectionListener.onFail(e); }
                    @Override
                    public void onComplete() { }
                    @Override
                    public void onSubscribe(Disposable d) { }
                    @Override
                    public void onNext(MainResponse<UserResponse> userResponse) {
                        ConnectionResponse<MainResponse<UserResponse>> response = new ConnectionResponse<>();
                        response.data = userResponse;
                        connectionListener.onSuccess(response);
                    }
                }); }



    public static void AddWalletapi(String auth  ,  RequestBody body,
                                   final ConnectionListener<MainResponse<InviteResponse>> connectionListener) {
        getApi().InvitePage(auth ,body).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MainResponse<InviteResponse>>() {
                    @Override
                    public void onError(Throwable e) { connectionListener.onFail(e); }
                    @Override
                    public void onComplete() { }
                    @Override
                    public void onSubscribe(Disposable d) { }
                    @Override
                    public void onNext(MainResponse<InviteResponse> userResponse) {
                        ConnectionResponse<MainResponse<InviteResponse>> response = new ConnectionResponse<>();
                        response.data = userResponse;
                        connectionListener.onSuccess(response);
                    }
                }); }



    public static void Redeemapi(String auth  , final ConnectionListener<MainResponse<List<RedeemResponse>>> connectionListener) {
        getApi().RedeemPage(auth).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MainResponse<List<RedeemResponse>>>() {
                    @Override
                    public void onError(Throwable e) { connectionListener.onFail(e); }
                    @Override
                    public void onComplete() { }
                    @Override
                    public void onSubscribe(Disposable d) { }
                    @Override
                    public void onNext(MainResponse<List<RedeemResponse>> userResponse) {
                        ConnectionResponse<MainResponse<List<RedeemResponse>>> response = new ConnectionResponse<>();
                        response.data = userResponse;
                        connectionListener.onSuccess(response);
                    }
                }); }


    public static void RedeemPointsapi(String auth  , String id,
                                 final ConnectionListener<MainResponse<List<RedeemPointsResponse>>> connectionListener) {
        getApi().RedeemPointsPage(auth ,  id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MainResponse<List<RedeemPointsResponse>>>() {
                    @Override
                    public void onError(Throwable e) { connectionListener.onFail(e); }
                    @Override
                    public void onComplete() { }
                    @Override
                    public void onSubscribe(Disposable d) { }
                    @Override
                    public void onNext(MainResponse<List<RedeemPointsResponse>> userResponse) {
                        ConnectionResponse<MainResponse<List<RedeemPointsResponse>>> response = new ConnectionResponse<>();
                        response.data = userResponse;
                        connectionListener.onSuccess(response);
                    }
                }); }

    public static void ChildernListapi(String auth,RequestBody body  , final ConnectionListener<MainResponse<List<ChildResponse>>> connectionListener) {
        getApi().GetChildrensPage(auth,body).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MainResponse<List<ChildResponse>>>() {
                    @Override
                    public void onError(Throwable e) { connectionListener.onFail(e); }
                    @Override
                    public void onComplete() { }
                    @Override
                    public void onSubscribe(Disposable d) { }
                    @Override
                    public void onNext(MainResponse<List<ChildResponse>> userResponse) {
                        ConnectionResponse<MainResponse<List<ChildResponse>>> response = new ConnectionResponse<>();
                        response.data = userResponse;
                        connectionListener.onSuccess(response);
                    }
                }); }



    public static void GetChildapi(String auth  ,RequestBody  body   ,  final ConnectionListener<MainResponse<List<ChildResponse>>> connectionListener) {
        getApi().GetChildrensByPhonePage(auth ,  body).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MainResponse<List<ChildResponse>>>() {
                    @Override
                    public void onError(Throwable e) { connectionListener.onFail(e); }
                    @Override
                    public void onComplete() { }
                    @Override
                    public void onSubscribe(Disposable d) { }
                    @Override
                    public void onNext(MainResponse<List<ChildResponse>> userResponse) {
                        ConnectionResponse<MainResponse<List<ChildResponse>>> response = new ConnectionResponse<>();
                        response.data = userResponse;
                        connectionListener.onSuccess(response);
                    }
                }); }



    public static void Notificationsapi(String auth  , final ConnectionListener<MainResponse<List<NotificationsResponse>>> connectionListener) {
        getApi().NotiifcationsPage(auth).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MainResponse<List<NotificationsResponse>>>() {
                    @Override
                    public void onError(Throwable e) { connectionListener.onFail(e); }
                    @Override
                    public void onComplete() { }
                    @Override
                    public void onSubscribe(Disposable d) { }
                    @Override
                    public void onNext(MainResponse<List<NotificationsResponse>> userResponse) {
                        ConnectionResponse<MainResponse<List<NotificationsResponse>>> response = new ConnectionResponse<>();
                        response.data = userResponse;
                        connectionListener.onSuccess(response);
                    }
                }); }


    public static void Homeapi(String token
            , final ConnectionListener<MainResponse<HomeResponse>> connectionListener) {
        getApi().HomePage(token).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MainResponse<HomeResponse>>() {
                    @Override
                    public void onError(Throwable e) { connectionListener.onFail(e); }
                    @Override
                    public void onComplete() { }
                    @Override
                    public void onSubscribe(Disposable d) { }
                    @Override
                    public void onNext(MainResponse<HomeResponse> aBoolean) {
                        ConnectionResponse<MainResponse<HomeResponse>> response = new ConnectionResponse<>();
                        response.data = aBoolean;
                        connectionListener.onSuccess(response);
                    }

                });
    }

    public static void Transferapi(String token,RequestBody  body
            , final ConnectionListener<MainResponse<TransferResponse>> connectionListener) {
        getApi().TransferPage(token , body).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MainResponse<TransferResponse>>() {
                    @Override
                    public void onError(Throwable e) { connectionListener.onFail(e); }
                    @Override
                    public void onComplete() { }
                    @Override
                    public void onSubscribe(Disposable d) { }
                    @Override
                    public void onNext(MainResponse<TransferResponse> aBoolean) {
                        ConnectionResponse<MainResponse<TransferResponse>> response = new ConnectionResponse<>();
                        response.data = aBoolean;
                        connectionListener.onSuccess(response);
                    }

                });
    }


    public static void Remainingapi(String token
            , final ConnectionListener<MainResponse<RemainingResponse>> connectionListener) {
        getApi().RemainingPage(token ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MainResponse<RemainingResponse>>() {
                    @Override
                    public void onError(Throwable e) { connectionListener.onFail(e); }
                    @Override
                    public void onComplete() { }
                    @Override
                    public void onSubscribe(Disposable d) { }
                    @Override
                    public void onNext(MainResponse<RemainingResponse> aBoolean) {
                        ConnectionResponse<MainResponse<RemainingResponse>> response = new ConnectionResponse<>();
                        response.data = aBoolean;
                        connectionListener.onSuccess(response);
                    }

                });
    }

    public static void UploadNidImageapi(String auth  , MultipartBody.Part fileToUpload, RequestBody NidNumber,
                                   final ConnectionListener<MainResponse<UploadNidResponse>> connectionListener) {
        getApi().UploadNidImagePage(auth ,fileToUpload,NidNumber).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MainResponse<UploadNidResponse>>() {
                    @Override
                    public void onError(Throwable e) { connectionListener.onFail(e); }
                    @Override
                    public void onComplete() { }
                    @Override
                    public void onSubscribe(Disposable d) { }
                    @Override
                    public void onNext(MainResponse<UploadNidResponse> userResponse) {
                        ConnectionResponse<MainResponse<UploadNidResponse>> response = new ConnectionResponse<>();
                        response.data = userResponse;
                        connectionListener.onSuccess(response);
                    }
                }); }



    public static void AvailableChildapi(String auth,RequestBody body  , final ConnectionListener<MainResponse<List<ChildResponse>>> connectionListener) {
        getApi().GetAvailablePage(auth,body).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MainResponse<List<ChildResponse>>>() {
                    @Override
                    public void onError(Throwable e) { connectionListener.onFail(e); }
                    @Override
                    public void onComplete() { }
                    @Override
                    public void onSubscribe(Disposable d) { }
                    @Override
                    public void onNext(MainResponse<List<ChildResponse>> userResponse) {
                        ConnectionResponse<MainResponse<List<ChildResponse>>> response = new ConnectionResponse<>();
                        response.data = userResponse;
                        connectionListener.onSuccess(response);
                    }
                }); }

    public static void Updateapi(String auth  , MultipartBody.Part fileToUpload, RequestBody username,
                                 RequestBody cityid, RequestBody areaid,
                                   final ConnectionListener<MainResponse<UserResponse>> connectionListener) {
        getApi().UpdateProfilePage(auth ,fileToUpload,username,cityid,areaid).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MainResponse<UserResponse>>() {
                    @Override
                    public void onError(Throwable e) { connectionListener.onFail(e); }
                    @Override
                    public void onComplete() { }
                    @Override
                    public void onSubscribe(Disposable d) { }
                    @Override
                    public void onNext(MainResponse<UserResponse> userResponse) {
                        ConnectionResponse<MainResponse<UserResponse>> response = new ConnectionResponse<>();
                        response.data = userResponse;
                        connectionListener.onSuccess(response);
                    }
                }); }



    public static void Moveapi(String auth,RequestBody body  , final ConnectionListener<MainResponse<TransferResponse>> connectionListener) {
        getApi().MovePage(auth,body).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MainResponse<TransferResponse>>() {
                    @Override
                    public void onError(Throwable e) { connectionListener.onFail(e); }
                    @Override
                    public void onComplete() { }
                    @Override
                    public void onSubscribe(Disposable d) { }
                    @Override
                    public void onNext(MainResponse<TransferResponse> userResponse) {
                        ConnectionResponse<MainResponse<TransferResponse>> response = new ConnectionResponse<>();
                        response.data = userResponse;
                        connectionListener.onSuccess(response);
                    }
                }); }


    public static void Loginapi(RequestBody  body,
                                 final ConnectionListener<MainResponse<UserResponse>> connectionListener) {
        getApi().LoginPage(body).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MainResponse<UserResponse>>() {
                    @Override
                    public void onError(Throwable e) { connectionListener.onFail(e); }
                    @Override
                    public void onComplete() { }
                    @Override
                    public void onSubscribe(Disposable d) { }
                    @Override
                    public void onNext(MainResponse<UserResponse> userResponse) {
                        ConnectionResponse<MainResponse<UserResponse>> response = new ConnectionResponse<>();
                        response.data = userResponse;
                        connectionListener.onSuccess(response);
                    }
                }); }


    public static void ConfirmPasswordapi(String  token, RequestBody  body,
                                final ConnectionListener<MainResponse<UserResponse>> connectionListener) {
        getApi().ConfirmPasswordPage(token,body).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MainResponse<UserResponse>>() {
                    @Override
                    public void onError(Throwable e) { connectionListener.onFail(e); }
                    @Override
                    public void onComplete() { }
                    @Override
                    public void onSubscribe(Disposable d) { }
                    @Override
                    public void onNext(MainResponse<UserResponse> userResponse) {
                        ConnectionResponse<MainResponse<UserResponse>> response = new ConnectionResponse<>();
                        response.data = userResponse;
                        connectionListener.onSuccess(response);
                    }
                }); }



    public static void RedeemUpdateapi(String  token, RequestBody  body,
                                          final ConnectionListener<MainResponse<RedeemUpdateResponse>> connectionListener) {
        getApi().RedeemUpdatePage(token,body).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MainResponse<RedeemUpdateResponse>>() {
                    @Override
                    public void onError(Throwable e) { connectionListener.onFail(e); }
                    @Override
                    public void onComplete() { }
                    @Override
                    public void onSubscribe(Disposable d) { }
                    @Override
                    public void onNext(MainResponse<RedeemUpdateResponse> userResponse) {
                        ConnectionResponse<MainResponse<RedeemUpdateResponse>> response = new ConnectionResponse<>();
                        response.data = userResponse;
                        connectionListener.onSuccess(response);
                    }
                }); }



}
