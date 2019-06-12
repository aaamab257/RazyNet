package com.razytech.razynet.data.network;

/**
 * Created by A.Noby on 4/7/2019.
 */
public interface ConnectionListener<T> {

    void onSuccess(ConnectionResponse<T> connectionResponse);

    void onFail(Throwable throwable);
}
