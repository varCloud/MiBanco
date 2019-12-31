package com.example.mibanco.DI;

import android.app.Application;

public class BaseApplicacion extends Application {

    private RetrofitComponente retrofitComponente;


    @Override
    public void onCreate() {
        super.onCreate();
        retrofitComponente = DaggerRetrofitComponente.builder().retrofitModulo(new RetrofitModulo()).build();
    }

    public RetrofitComponente getRetrofitComponente(){
        return retrofitComponente;
    }
}
