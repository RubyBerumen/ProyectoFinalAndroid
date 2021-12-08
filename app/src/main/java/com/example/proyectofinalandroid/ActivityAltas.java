package com.example.proyectofinalandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import baseDeDatos.EmpresaBD;
import entidades.Proyecto;

public class ActivityAltas extends AppCompatActivity {
    EditText nombre,numero,ubicacion,numDepartamento;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altas);
        nombre=findViewById(R.id.txt_Nombre);
        numero=findViewById(R.id.txt_Numero);
        ubicacion=findViewById(R.id.txt_Ubicacion);
        numDepartamento=findViewById(R.id.txt_NumeroDpto);
    }

    public void agregarProyecto(View v){
        if(verificarCajasVacias()==false){
            String nom = nombre.getText().toString();
            int num = Integer.parseInt(numero.getText().toString());
            String ubi = ubicacion.getText().toString();
            byte numDpto = Byte.parseByte(numDepartamento.getText().toString());
            Proyecto p = new Proyecto(nom,num,ubi,numDpto);



            new Thread(new Runnable() {
                @Override
                public void run() {
                    EmpresaBD conexion = EmpresaBD.gettAppDatabase(getBaseContext());
                    try{
                        conexion.pDAO().insertarProyecto(p);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getBaseContext(),"Se agregó correctamente",Toast.LENGTH_LONG).show();
                                limpiarCajas();
                            }
                        });
                    }catch (Exception e){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getBaseContext(),"Los datos ya existen",Toast.LENGTH_LONG).show();
                                limpiarCajas();
                            }
                        });

                    }

                }//run
            }).start();

        }else{
            Toast.makeText(getBaseContext(),"Debes completar todos los campos",Toast.LENGTH_LONG).show();
        }

    }


    public boolean verificarCajasVacias(){
        if(nombre.getText().equals("")||numero.getText().equals("")||ubicacion.getText().equals("")||numDepartamento.getText().equals("")){
            return true;
        }else{
            return false;
        }
    }

    public void limpiarCajas(){
        nombre.setText("");
        numero.setText("");
        ubicacion.setText("");
        numDepartamento.setText("");
    }

    public void borrar(View v){
        limpiarCajas();
    }

}
