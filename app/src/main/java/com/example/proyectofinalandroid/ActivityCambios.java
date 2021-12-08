package com.example.proyectofinalandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import baseDeDatos.EmpresaBD;
import entidades.Proyecto;

public class ActivityCambios extends AppCompatActivity {

    EditText nombre,numero,ubicacion,numDepartamento;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambios);
        nombre=findViewById(R.id.txt_Nombre3);
        numero=findViewById(R.id.txt_Numero3);
        ubicacion=findViewById(R.id.txt_Ubicacion3);
        numDepartamento=findViewById(R.id.txt_NumeroDpto3);
    }

    public void modificarProyecto(View v){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Proyecto p = null;
                EmpresaBD conexion = EmpresaBD.gettAppDatabase(getBaseContext());
                int num = Integer.parseInt(""+numero.getText().toString());
                p = (Proyecto) conexion.pDAO().buscarPorNumP(num);
                if (p!=null){
                   try {
                       String nom = nombre.getText().toString();
                       int no = Integer.parseInt(numero.getText().toString());
                       String ubi = ubicacion.getText().toString();
                       byte numDpto = Byte.parseByte(numDepartamento.getText().toString());
                       conexion.pDAO().modificarProyecto(no,nom,ubi,numDpto);

                       runOnUiThread(new Runnable() {
                           @Override
                           public void run() {
                               Toast.makeText(getBaseContext(), "Se modificó correctamente", Toast.LENGTH_LONG);
                           }
                       });

                   }catch (Exception e){

                   }


                }else{
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getBaseContext(), "Los datos ingresados no existen", Toast.LENGTH_LONG).show();
                            limpiarCajas();
                        }
                    });
                }
            }
        }).start();
    }//modificar


    public void buscar(View v){
        if(!numero.getText().toString().isEmpty()){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Proyecto p = null;
                    EmpresaBD conexion = EmpresaBD.gettAppDatabase(getBaseContext());
                    int num = Integer.parseInt(""+numero.getText().toString());
                    p = (Proyecto) conexion.pDAO().buscarPorNumP(num);
                    if (p!=null){
                        try {
                            nombre.setText(p.getNombreProyecto());
                            numero.setText(""+p.getNumProyecto());
                            ubicacion.setText(p.getUbicaciónProyecto());
                            numDepartamento.setText(""+p.getNumDptoProyecto());
                        }catch (Exception e){

                        }
                    }else{
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getBaseContext(), "Los datos ingresados no existen", Toast.LENGTH_LONG).show();
                                limpiarCajas();
                            }
                        });
                    }

                }
            }).start();
        }else{
            Toast.makeText(getBaseContext(), "Debes ingresar el numero de proyecto", Toast.LENGTH_LONG);
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
