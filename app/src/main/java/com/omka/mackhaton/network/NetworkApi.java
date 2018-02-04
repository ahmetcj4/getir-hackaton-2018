package com.omka.mackhaton.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.omka.mackhaton.entity.request.SearchRequest;
import com.omka.mackhaton.entity.response.SearchResult;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ahmet on 2/4/2018.
 */

public class NetworkApi {

    private static NetworkApi instance;
    private final NetworkService service;


    public static NetworkApi getInstance() {
        if (instance == null) {
            instance = new NetworkApi();
        }
        return instance;
    }

    private NetworkApi() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);

        OkHttpClient.Builder clientBuilder = new OkHttpClient().newBuilder().addNetworkInterceptor(loggingInterceptor);

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-dd-MM HH:mm:ss")
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(NetworkConstants.BASE_URL)
                .client(clientBuilder.build())
                .build();

        service = retrofit.create(NetworkService.class);
    }


    public void search(SearchRequest searchRequest, NetworkCallback<SearchResult> callback) {
        sendRequest(service.search(searchRequest), callback);
    }

    private <R> void sendRequest(Call<R> call, final NetworkCallback<R> callBack) {
        Callback<R> requestCallback = new Callback<R>() {
            @Override
            public void onResponse(Call<R> call, retrofit2.Response<R> response) {
                if (response.isSuccessful()) {
                    if (callBack != null) {
                        callBack.onSuccess(response.body());
                    }
                } else {
                    if (callBack != null) {
                        callBack.onServiceFailure(response.code(), "Unknown error occured");
                    }
                }
            }

            @Override
            public void onFailure(Call<R> call, Throwable throwable) {
                if (callBack != null) {
                    callBack.onNetworkFailure(throwable);
                }
            }
        };
        call.enqueue(requestCallback);
    }

}
