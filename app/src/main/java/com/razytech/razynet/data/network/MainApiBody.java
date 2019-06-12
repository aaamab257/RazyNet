package com.razytech.razynet.data.network;

import android.support.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import okhttp3.MediaType;
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

    public static HashMap<String, String> LoginBoby(String Email , String Password) throws JSONException {
        HashMap<String, String> params=new HashMap<String, String>();
        params.put("username", Email);
        params.put("password", Password);
        return params;
    }

}
