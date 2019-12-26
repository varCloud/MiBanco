package com.example.mibanco.ws.apiSocio;

import com.example.mibanco.Entidades.Request.RequestValidarNumero;
import com.example.mibanco.Entidades.Response.Response;
import com.example.mibanco.Entidades.ValidaNumero;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ISocio {

    @POST("api/Socio/ValidaNumero")
    Call<Response<ValidaNumero>> ValidaNumero(@Body RequestValidarNumero request);

}
