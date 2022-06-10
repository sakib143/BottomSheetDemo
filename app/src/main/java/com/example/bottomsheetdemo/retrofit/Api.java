package com.example.bottomsheetdemo.retrofit;

import com.example.bottomsheetdemo.model.GiftModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("v1/d54aad68-00fc-42a1-83b4-f49564695bf7")
    Call<GiftModel> getGiftsData();

}
