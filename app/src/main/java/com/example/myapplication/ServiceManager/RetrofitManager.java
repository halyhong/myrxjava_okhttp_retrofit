package com.example.myapplication.ServiceManager;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    private static final int DEFAULT_TIME_OUT = 15;//超时时间 5s
    private static final int DEFAULT_READ_TIME_OUT = 15;

    private static HashMap<String, RetrofitManager> manageMap;
    private Retrofit mRetrofit;

    private RetrofitManager(String baseUrl) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);//连接超时时间
        client.writeTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS);//写操作 超时时间
        client.readTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS);//读操作超时时间

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        client.addInterceptor(loggingInterceptor);

        mRetrofit = new Retrofit.Builder()
                .client(client.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build();
    }

    public static synchronized RetrofitManager getInstance(String baseUrl) {
        if (manageMap == null) {
            manageMap = new HashMap<>();
        }
        if (manageMap.containsKey(baseUrl)) {
            return manageMap.get(baseUrl);
        }

        RetrofitManager manager = new RetrofitManager(baseUrl);
        manageMap.put(baseUrl, manager);

        return manager;
    }

    public <T> T create(Class<T> service) {
        if(null != mRetrofit) {
            return mRetrofit.create(service);
        }

        return null;
    }
}
