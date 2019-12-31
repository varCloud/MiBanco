package com.example.mibanco.DI;

import com.example.mibanco.ws.apiSocio.ISocio;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class RetrofitModulo {

    private static String urlApiSocio = "http://192.168.98.54:5555/ApiSocio/";
    private static String urlApPrestamo = "http://192.168.98.54:5555/ApiPrestamo/";


    @Singleton
    @Provides
    RxJava2CallAdapterFactory provideRxJava2CallAdapterFactory(){
        return  RxJava2CallAdapterFactory.create();
    }


    @Singleton
    @Provides
    GsonConverterFactory provideGsonConverterFactory(){
        return  GsonConverterFactory.create();
    }

    @Singleton
    @Provides
    HttpLoggingInterceptor provideHttpLoggingInterceptor(){
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor){
        return new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
    }

    @Named("apiSocio")
    @Singleton
    @Provides
    Retrofit provideRetrofitApiSocio(GsonConverterFactory gsonConverterFactory,
                                     RxJava2CallAdapterFactory rxJava2CallAdapterFactory,
                                     OkHttpClient client){
        return  new Retrofit.Builder()
                .baseUrl(urlApiSocio)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .client(client)
                .build();
    }


    @Singleton
    @Provides
    public ISocio provideApiClientSocio(@Named("apiSocio")Retrofit retrofit){
        return retrofit.create(ISocio.class);
    }


    /*@Named("ApiPrestamos")
    @Singleton
    @Provides
    Retrofit provideRetrofitApiPrestamo(GsonConverterFactory gsonConverterFactory,OkHttpClient client){
        return  new Retrofit.Builder()
                .baseUrl(urlApPrestamo)
                .addConverterFactory(gsonConverterFactory)
                .client(client)
                .build();
    }
    */

    /*
    @Singleton
    @Provides
    ISocio provideApiClientPrestamo(@Named("ApiPrestamo")Retrofit retrofit){
        return retrofit.create(ISocio.class);
    }

    */

}
