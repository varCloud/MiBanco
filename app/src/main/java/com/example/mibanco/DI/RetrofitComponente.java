package com.example.mibanco.DI;

import com.example.mibanco.MainActivity;
import com.example.mibanco.Vistas.Login;
import com.example.mibanco.Vistas.Splash;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = RetrofitModulo.class)
public interface RetrofitComponente {

    void inject(MainActivity mainActivity);
    void inject(Login login);

}
