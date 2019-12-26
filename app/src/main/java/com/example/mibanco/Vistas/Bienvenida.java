package com.example.mibanco.Vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.StateListAnimator;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mibanco.MainActivity;
import com.example.mibanco.R;

public class Bienvenida extends AppCompatActivity {

    ImageView imgLogo;
    ImageView imgLetrasLogo;
    Button btnIniciarSesion,btnToken;
    TextView txtRegistrarse;

    Animation animationTop,animationBottom,animationFromLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenida);

        InitCompontent();

        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                MostrarMainActivity();
            }
        },2500);*/
    }


    private  void InitCompontent(){
        //Creacion de UI
        imgLogo = (ImageView) findViewById(R.id.imgLogo);
        imgLetrasLogo = (ImageView) findViewById(R.id.imgLetrasLogo);
        btnIniciarSesion = (Button)  findViewById(R.id.btnIniciarSesion);
        btnToken = (Button)  findViewById(R.id.btnToken);
        txtRegistrarse = (TextView) findViewById(R.id.txtRegistrarse);

        //Creacion de animaciones
        animationTop = AnimationUtils.loadAnimation(this,R.anim.fromtop);
        animationBottom = AnimationUtils.loadAnimation(this,R.anim.frombottom);
        animationFromLeft = AnimationUtils.loadAnimation(this,R.anim.fromleft);
        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
        fadeIn.setDuration(1000);

        //Asignacion de animaciones
        imgLogo.setAnimation(animationTop);
        imgLetrasLogo.setAnimation(animationBottom);

        animationBottom.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                btnIniciarSesion.animate().alpha(1f).setDuration(1800);
                btnToken.animate().alpha(1f).setDuration(1800);
                txtRegistrarse.animate().alpha(1f).setDuration(1800);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    private void IrActividad(Context contexto,Class actividad,ActivityOptions options){
        Intent intent = new Intent(contexto, actividad);
        if(options!=null) {
            startActivity(intent,options.toBundle());
        }else{
            startActivity(intent);
        }
    }

    public void GoIniciarSesion(View view){
        try {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this,
                    Pair.create(imgLogo,"logoTransition"),
                    Pair.create(imgLetrasLogo,"letrasLogoTransition"),
                    Pair.create(btnIniciarSesion,"btnSesionTransition"),
                    Pair.create(btnToken,"btnTokenTransition"),
                    Pair.create(txtRegistrarse,"txtRegistrarTransition")
            );
            IrActividad(this,Login.class,options);
            //finish();
        }catch (Exception ex){
        }
    }

    public void GoToken(View view){
        try {

        }catch (Exception ex){

        }
    }

    public void GoRegistrarse(View view){
        try {

        }catch (Exception ex){

        }
    }

}
