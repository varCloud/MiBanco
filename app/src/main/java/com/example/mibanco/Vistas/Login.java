package com.example.mibanco.Vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mibanco.R;

public class Login extends AppCompatActivity {


    Button btnContinuar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }


    private  void InitCompontent(){
        //Creacion de UI
        btnContinuar = (Button)  findViewById(R.id.btnContinuarLogin);

    }


    public void GoValidarNumero(View view){
        try {

        }catch (Exception ex){

        }
    }

}
