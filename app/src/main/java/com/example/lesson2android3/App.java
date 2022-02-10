package com.example.lesson2android3;

import android.app.Application;

import com.example.lesson2android3.network.PixabayApi;
import com.example.lesson2android3.network.RetrofitClient;

public class App extends Application {
    public static PixabayApi api;
    public RetrofitClient retrofitClient;

    @Override
    public void onCreate() {
        super.onCreate();
        retrofitClient = new RetrofitClient();
        api = retrofitClient.provideApi();
    }
}
