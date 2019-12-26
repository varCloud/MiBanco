package com.example.mibanco.Entidades;

public class ValidaNumero {

    private String NombreSocio;
    private String NombreSocioEnmascarado;
    private String MotivoBloqueo;
    private String ImagenAntiphishing;
    private Boolean VieneDeBloqueo ;

    public String getNombreSocio() {
        return NombreSocio;
    }

    public void setNombreSocio(String nombreSocio) {
        NombreSocio = nombreSocio;
    }

    public String getNombreSocioEnmascarado() {
        return NombreSocioEnmascarado;
    }

    public void setNombreSocioEnmascarado(String nombreSocioEnmascarado) {
        NombreSocioEnmascarado = nombreSocioEnmascarado;
    }

    public String getMotivoBloqueo() {
        return MotivoBloqueo;
    }

    public void setMotivoBloqueo(String motivoBloqueo) {
        MotivoBloqueo = motivoBloqueo;
    }

    public String getImagenAntiphishing() {
        return ImagenAntiphishing;
    }

    public void setImagenAntiphishing(String imagenAntiphishing) {
        ImagenAntiphishing = imagenAntiphishing;
    }

    public Boolean getVieneDeBloqueo() {
        return VieneDeBloqueo;
    }

    public void setVieneDeBloqueo(Boolean vieneDeBloqueo) {
        VieneDeBloqueo = vieneDeBloqueo;
    }
}
