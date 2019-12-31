package com.example.mibanco.Vistas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mibanco.DI.BaseApplicacion;
import com.example.mibanco.Entidades.Request.RequestValidarNumero;
import com.example.mibanco.Entidades.Response.Response;
import com.example.mibanco.Entidades.ValidaNumero;
import com.example.mibanco.R;
import com.example.mibanco.ws.apiSocio.ISocio;
import com.google.android.material.snackbar.Snackbar;

import java.util.regex.Pattern;

import javax.inject.Inject;

public class Login extends AppCompatActivity {

    ConstraintLayout layoutPrincipal;
    //contentValidaNumero
    View contentValidaNumero;
    Button btnContinuar;
    EditText txtNumero;
    TextView lblBienvenida,labelErrorNumSocio;
    ImageView imgLogo;

    //contentLoginPass
    View contentLoginPass;


    @Inject
    ISocio socioInterface;

    RequestValidarNumero requestValidarNumero;
    Pattern pattern = Pattern.compile("\\d{4,8}");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InitDI();
        setContentView(R.layout.activity_login);
        InitCompontent();
    }


    //region FUNCIONES  GENERALES
    private void InitDI(){
        try {
            ((BaseApplicacion)getApplication()).getRetrofitComponente().inject(this);
        }catch (Exception ex){
            Log.e("InitDI",ex.getMessage());
        }
    }

    private  void InitCompontent(){
        //Creacion de UI
        layoutPrincipal = (ConstraintLayout) findViewById(R.id.layoutPrincipal);
        contentValidaNumero = (View) findViewById(R.id.contentValidaNumero);
        btnContinuar = (Button)  findViewById(R.id.btnContinuarLogin);
        txtNumero = (EditText) findViewById(R.id.txtNumero);
        lblBienvenida = (TextView) findViewById(R.id.lblBienvenida);
        labelErrorNumSocio = (TextView) findViewById(R.id.labelErrorNumSocio);
        imgLogo = (ImageView) findViewById(R.id.imgLogo);
        //
        contentLoginPass = (View) findViewById(R.id.contentLoginPass);

        //Asignacion de animaciones
        txtNumero.animate().alpha(1f).setDuration(1200);
        lblBienvenida.animate().alpha(1f).setDuration(1200);

        //Asignacion de Eventos
        txtNumero.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before,int count) {
                if (s.length() > 0)
                {
                    labelErrorNumSocio.setVisibility(View.INVISIBLE);
                }else{
                    labelErrorNumSocio.setVisibility(View.VISIBLE);
                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count,int after) {}
            public void afterTextChanged(Editable s) {}
        });

        //Inicializacion de variables
        requestValidarNumero =new RequestValidarNumero();
    }

    private boolean ValidarDatos(RequestValidarNumero requestValidarNumero){
        return IsNumerico(requestValidarNumero.getNumeroSocio());
    }

    public boolean IsNumerico(String strNum) {
        if (strNum == null) {
            return false;
        }
        return pattern.matcher(strNum).matches();
    }

    private void IrActividad(Context contexto, Class actividad, ActivityOptions options){
        Intent intent = new Intent(contexto, actividad);
        if(options!=null) {
            startActivity(intent,options.toBundle());
        }else{
            startActivity(intent);
        }
    }

    public Bitmap StringToBitMap(String encodedImage) {
        try {
            byte[] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            return decodedByte;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
    //endregion FUNCIONES  GENERALES

    //region FUNCIONES

    private void ValidarNumero(RequestValidarNumero requestValidarNumero){
        try{
            socioInterface.ValidaNumero(requestValidarNumero).observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::SuccessValidarNumero,this::ErrorValidarNumero);
        }catch (Exception ex){
            Snackbar.make(layoutPrincipal,  R.string.msjErrorGenerico, Snackbar.LENGTH_LONG).show();
        }
    }

    private void SuccessValidarNumero(Response<ValidaNumero> validaNumeroResponse) {
        try {
            if(validaNumeroResponse.getEstatus() == 200)
            {
                GoLoginPass();
                imgLogo.setImageBitmap(StringToBitMap(validaNumeroResponse.getData().getImagenAntiphishing()));
            }else{
                Snackbar.make(layoutPrincipal, validaNumeroResponse.getMensaje(), Snackbar.LENGTH_LONG).show();
            }
        }catch (Exception ex){
            Snackbar.make(layoutPrincipal, ex.getMessage(), Snackbar.LENGTH_LONG).show();
        }
    }

    private void ErrorValidarNumero(Throwable throwable) {
        try {
            Snackbar.make(layoutPrincipal, R.string.msjErrorProcesarSolicitud, Snackbar.LENGTH_LONG).show();
            Log.d(this.getClass().getName(),throwable.getMessage());
        }catch (Exception ex){
            Log.d(this.getClass().getName(),ex.getMessage());
        }
    }

    public void GoLoginPass(){
        try {
            contentValidaNumero.animate().alpha(0);
            contentLoginPass.setVisibility(View.VISIBLE);
        }catch (Exception ex){
        }
    }

    //endregion FUNCIONES

    //region EVENTOS

    public void OnValidarNumero(View view){
        try {
            requestValidarNumero.setNumeroSocio(txtNumero.getText().toString());
            requestValidarNumero.setTipoOrigen("Movil");
            if(ValidarDatos(requestValidarNumero)){
                ValidarNumero(requestValidarNumero);
            }else{
                labelErrorNumSocio.setVisibility(View.VISIBLE);
                Snackbar.make(layoutPrincipal, "Verifique los datos ingresados", Snackbar.LENGTH_LONG).show();
            }
        }catch (Exception ex){
            Snackbar.make(layoutPrincipal,  R.string.msjErrorGenerico, Snackbar.LENGTH_LONG).show();
        }
    }

    //endregion EVENTOS

}
