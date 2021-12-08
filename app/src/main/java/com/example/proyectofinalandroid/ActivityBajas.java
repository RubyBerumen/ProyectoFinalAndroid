package com.example.proyectofinalandroid;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import baseDeDatos.EmpresaBD;
import entidades.Proyecto;


public class ActivityBajas extends AppCompatActivity {

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

    public void eliminarProyecto(View v){
        if (verificarCajasVacias()==false){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    EmpresaBD conexion = EmpresaBD.gettAppDatabase(getBaseContext());
                    int num = Integer.parseInt(String.valueOf(numero.getText()));
                    if(conexion.pDAO().buscarPorNumP(num)!=null){
                        conexion.pDAO().eliminarProyecto(num);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getBaseContext(),"Se eliminó correctamente",Toast.LENGTH_LONG).show();
                                limpiarCajas();
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
        }
    }//eliminar

    public void buscar(View v){
        if(numero.getText().toString().isEmpty()==false){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Proyecto p = null;
                    EmpresaBD conexion = EmpresaBD.gettAppDatabase(getBaseContext());
                    int num = Integer.parseInt(String.valueOf(numero.getText()));
                    p = conexion.pDAO().buscarPorNumP(num);
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
        }
    }


    public boolean verificarCajasVacias(){
        if(nombre.getText().equals("")||numero.getText().equals("")){
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
