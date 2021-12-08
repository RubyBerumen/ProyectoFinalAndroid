package com.example.proyectofinalandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import baseDeDatos.EmpresaBD;
import entidades.Proyecto;

public class ActivityCambios extends Activity {

    EditText nombre,numero,ubicacion,numDepartamento;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bajas);
        nombre=findViewById(R.id.txt_Nombre2);
        numero=findViewById(R.id.txt_Numero2);
        ubicacion=findViewById(R.id.txt_Ubicacion2);
        numDepartamento=findViewById(R.id.txt_NumeroDpto2);
    }

    public void modificarProyecto(View v){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Proyecto p = null;
                EmpresaBD conexion = EmpresaBD.gettAppDatabase(getBaseContext());
                int num = Integer.parseInt(String.valueOf(numero.getText()));
                p = (Proyecto) conexion.pDAO().buscarPorNumP(num);
                if (p!=null){
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
        if(numero.getText().toString().isEmpty()==false){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Proyecto p = null;
                    EmpresaBD conexion = EmpresaBD.gettAppDatabase(getBaseContext());
                    int num = Integer.parseInt(String.valueOf(numero.getText()));
                    p = (Proyecto) conexion.pDAO().buscarPorNumP(num);
                    if (p!=null){
                        nombre.setText(p.getNombreProyecto());
                        numero.setText(p.getNumProyecto());
                        ubicacion.setText(p.getUbicaciónProyecto());
                        numDepartamento.setText(p.getNumDptoProyecto());
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
