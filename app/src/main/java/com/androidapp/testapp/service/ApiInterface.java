package com.androidapp.testapp.service;

import com.androidapp.testapp.model.Model;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface ApiInterface {

    String URL = "https://api.androidhive.info/";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @GET("contacts/")
    Call<Model> getContactList();

}
