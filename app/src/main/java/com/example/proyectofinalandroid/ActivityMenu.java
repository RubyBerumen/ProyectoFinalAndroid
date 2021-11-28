package com.example.proyectofinalandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ActivityMenu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void abrirActivities(View v){
        Intent i=null;
        switch (v.getId()){
            case R.id.btn_altas:
                i=new Intent(this,ActivityAltas.class);
                break;
            case R.id.btn_bajas:
                i=new Intent(this,ActivityBajas.class);
                break;
            case R.id.btn_cambios:
                i=new Intent(this,ActivityCambios.class);
                break;
            case R.id.btn_consultas:
                i=new Intent(this,ActivityConsultas.class);
                break;
        }
        startActivity(i);
    }



}
