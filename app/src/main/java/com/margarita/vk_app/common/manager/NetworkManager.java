package com.margarita.vk_app.common.manager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.margarita.vk_app.VkApplication;
import com.margarita.vk_app.rest.RestClient;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.realm.internal.Util;

public class NetworkManager {

    @Inject
    Context context;

    private static final int CONNECTION_TIMEOUT = 2000;

    public NetworkManager() {
        VkApplication.getApplicationComponent().inject(this);
    }

    /**
     * Check if device is connected to the Internet
     * @return True if the Internet connection if available
     */
    private boolean isOnline() {
        ConnectivityManager manager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager != null ? manager.getActiveNetworkInfo() : null;
        return networkInfo != null && networkInfo.isConnected() || Util.isEmulator();
    }

    /**
     * Check if the Vk server is available
     * @return Boolean value as Callable (true if the server if available)
     */
    private Callable<Boolean> isVkAvailableCallable() {
        return () -> {
            try {
                if (!isOnline())
                    return false;

                URL url = new URL(RestClient.VK_URL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(CONNECTION_TIMEOUT);
                connection.connect();
                return true;
            }
            catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        };
    }

    public Observable<Boolean> getNetworkStateObservable() {
        return Observable.fromCallable(isVkAvailableCallable());
    }
}
