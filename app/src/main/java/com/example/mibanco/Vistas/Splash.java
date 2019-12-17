package com.example.mibanco.Vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.example.mibanco.MainActivity;
import com.example.mibanco.R;

public class Splash extends AppCompatActivity {

    ImageView imgLogo;
    ImageView imgLetrasLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        InitCompontent();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            MostrarMainActivity();
            }
        },2500);
    }


    private  void InitCompontent(){
        //imgLogo = (ImageView) findViewById(R.id.imgLogo);
        //imgLetrasLogo = (ImageView) findViewById(R.id.imgLetrasLogo);

        //Asignacion de animaciones

    }

    private void MostrarMainActivity(){
        Intent intent = new Intent(Splash.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
