package com.example.mibanco.Entidades.Response;

public class Response <T>  {

   protected T Data;
   private String Mensaje;
   private Integer Estatus;

   public T getData() {
      return Data;
   }

   public void setData(T data) {
      Data = data;
   }

   public String getMensaje() {
      return Mensaje;
   }

   public void setMensaje(String mensaje) {
      Mensaje = mensaje;
   }

   public Integer getEstatus() {
      return Estatus;
   }

   public void setEstatus(Integer estatus) {
      Estatus = estatus;
   }
}
