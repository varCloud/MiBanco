package com.example.mibanco;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.example.mibanco.Entidades.Request.RequestValidarNumero;
import com.example.mibanco.Entidades.Response.Response;
import com.example.mibanco.Entidades.ValidaNumero;
import com.example.mibanco.ws.apiSocio.ApiSocio;
import com.example.mibanco.ws.apiSocio.ISocio;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Base64;
import android.util.Log;
import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.ImageView;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ISocio socioInterface;
    ImageView img ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        img = findViewById(R.id.imageView2);
        socioInterface = ApiSocio.getClient().create(ISocio.class);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Call<Response<ValidaNumero>> call = socioInterface.ValidaNumero( new RequestValidarNumero("666480", "Movil"));
                call.enqueue(new Callback<Response<ValidaNumero>>() {
                                 @Override
                                 public void onResponse(Call<Response<ValidaNumero>> call, retrofit2.Response<Response<ValidaNumero>> response) {
                                     Log.d("MainActivity", response.code()+"");
                                     if(response.body().getEstatus() == 200)
                                     {
                                         try {
                                             Log.d("MainActivity", response.body().getData().getNombreSocio());
                                             img.setImageBitmap(StringToBitMap(response.body().getData().getImagenAntiphishing()));
                                         }catch (Exception ex){
                                             Log.d("",ex.getMessage());
                                         }
                                     }
                                 }

                                 @Override
                                 public void onFailure(Call<Response<ValidaNumero>> call, Throwable t) {
                                     Log.d("MainActivity",t.getMessage());
                                 }
                             });


                        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
            }
        });



        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    public Bitmap StringToBitMap(String encodedImage) {
        try {
            //byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            //Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            byte[] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

            return decodedByte;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
