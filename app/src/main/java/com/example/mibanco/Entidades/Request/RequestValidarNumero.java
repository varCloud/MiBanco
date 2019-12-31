package com.example.mibanco.Entidades.Request;

public class RequestValidarNumero {

   private String  NumeroSocio;
   private String  TipoOrigen;

    public String getNumeroSocio() {

        return NumeroSocio;
    }

    public void setNumeroSocio(String numeroSocio) {

        NumeroSocio = numeroSocio;
    }

    public String getTipoOrigen() {

        return TipoOrigen;
    }

    public void setTipoOrigen(String tipoOrigen) {

        TipoOrigen = tipoOrigen;
    }

    public RequestValidarNumero(String numeroSocio, String tipoOrigen) {
        NumeroSocio = numeroSocio;
        TipoOrigen = tipoOrigen;
    }

    public RequestValidarNumero(){}
}
