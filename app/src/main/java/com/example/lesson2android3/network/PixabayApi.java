package com.example.lesson2android3.network;

import com.example.lesson2android3.model.Hit;
import com.example.lesson2android3.model.ImageResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PixabayApi {
    @GET("/api")
    Call<ImageResponse> getImageBySearch(@Query("key") String key, @Query("q") String keyWord);
}
