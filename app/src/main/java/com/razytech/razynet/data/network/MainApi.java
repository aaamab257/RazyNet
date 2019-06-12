package com.razytech.razynet.data.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.razytech.razynet.BuildConfig;
import com.razytech.razynet.data.beans.UserResponse;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
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
                .baseUrl(BuildConfig.API_LINK)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();


        return retrofit.create(aClass);
    }



 

    public static void SignUpapi(HashMap<String, String> body
            , final ConnectionListener<MainResponse<UserResponse>> connectionListener) {
        getApi().SignUpPage(body).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MainResponse<UserResponse>>() {
                    @Override
                    public void onError(Throwable e) { connectionListener.onFail(e); }
                    @Override
                    public void onComplete() { }
                    @Override
                    public void onSubscribe(Disposable d) { }
                    @Override
                    public void onNext(MainResponse<UserResponse> aBoolean) {
                        ConnectionResponse<MainResponse<UserResponse>> response = new ConnectionResponse<>();
                        response.data = aBoolean;
                        connectionListener.onSuccess(response);
                    }
                });
    }





}
