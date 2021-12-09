package com.example.proyectofinalandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import baseDeDatos.EmpresaBD;
import entidades.Usuario;

public class MainActivity extends AppCompatActivity {

    EditText nombre, contra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nombre = findViewById(R.id.txt_Usuario);
        contra = findViewById(R.id.txt_Contraseña);
        Usuario u = new Usuario("Ruby","158");

        /*new Thread(new Runnable() {
            @Override
            public void run() {
                EmpresaBD conexion = EmpresaBD.gettAppDatabase(getBaseContext());
                conexion.uDAO().agregarUsuario(u);
            }
        }).start();*/


    }

    public void abrirMenu(View v){
        Intent i = new Intent(this, ActivityMenu.class);
        startActivity(i);

    }

    public void ingresar(View v){
        Intent i = new Intent(this, ActivityMenu.class);
        Usuario u = new Usuario(nombre.getText().toString(),contra.getText().toString());

        new Thread(new Runnable() {
            @Override
            public void run() {
                EmpresaBD conexion = EmpresaBD.gettAppDatabase(getBaseContext());
                Usuario u2 = conexion.uDAO().buscarUsuario(nombre.getText().toString());

                if (!(nombre.getText().toString().isEmpty() && contra.getText().toString().isEmpty()) && u2 != null){
                    if(u.getUsuario().equals(u2.getUsuario()) && u.getContraseña().equals(u2.getContraseña())){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getBaseContext(), "Usuario y contraseña correctos", Toast.LENGTH_SHORT).show();
                                startActivity(i);
                                limpiarCajas();
                            }
                        });
                    }else{
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getBaseContext(), "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }else{
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getBaseContext(), "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        }).start();


    }//ingresar


    public void limpiarCajas() {
        nombre.setText("");
        contra.setText("");
    }

}//main

