package com.androidapp.testapp;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.androidapp.testapp.service.ApiInterface;

public class MainViewModel extends AndroidViewModel {
    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    public ApiInterface getApi(){
        ApiInterface apiInterface = ApiInterface.retrofit.create(ApiInterface.class);
        return apiInterface;
    }
}
