package com.example.proyectofinalandroid;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import baseDeDatos.EmpresaBD;
import entidades.Proyecto;


public class ActivityBajas extends AppCompatActivity {

    EditText nombre,numero,ubicacion,numDepartamento;
    RecyclerView recycelerV;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutM;
    List<Proyecto> proyectoList;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bajas);
        nombre=findViewById(R.id.txt_Nombre2);
        numero=findViewById(R.id.txt_Numero2);
        ubicacion=findViewById(R.id.txt_Ubicacion2);
        numDepartamento=findViewById(R.id.txt_NumeroDpto2);

        recycelerV=findViewById(R.id.txt_RecyclerView1);
        recycelerV.setHasFixedSize(true);

        layoutM = new LinearLayoutManager(this);
        recycelerV.setLayoutManager(layoutM);


        numero.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String[] datos = {""};
                int[] p = new int[1];

                if(numero.getText().toString().isEmpty()){
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            EmpresaBD conexion = EmpresaBD.gettAppDatabase(getBaseContext());
                            proyectoList = conexion.pDAO().obtenerTodos();
                            p[0] = proyectoList.size();
                            for (Proyecto i:proyectoList){
                                Log.d("proyecto", i.toString());
                            }
                            for(int i=0; i<p[0];i++){
                                datos[0] = datos[0]+proyectoList.get(i)+"/";
                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    adapter = new AdaptadorRegistros(datos[0].split("/"));
                                    recycelerV.setAdapter(adapter);
                                }
                            });
                        }
                    }).start();
                }else{
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            EmpresaBD conexion = EmpresaBD.gettAppDatabase(getBaseContext());
                            proyectoList = conexion.pDAO().buscarNumLista("%"+numero.getText().toString()+"%");
                            p[0] = proyectoList.size();
                            for (int i = 0; i < p[0]; i++) {
                                datos[0] = datos[0] + proyectoList.get(i)+"/";
                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    adapter = new AdaptadorRegistros(datos[0].split("/"));
                                    recycelerV.setAdapter(adapter);
                                }
                            });
                        }
                    }).start();
                }

            }
        });




        nombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String[] datos = {""};
                int[] p = new int[1];

                if(nombre.getText().toString().isEmpty()){
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            EmpresaBD conexion = EmpresaBD.gettAppDatabase(getBaseContext());
                            proyectoList = conexion.pDAO().obtenerTodos();
                            p[0] = proyectoList.size();
                            for (Proyecto i:proyectoList){
                                Log.d("proyecto", i.toString());
                            }
                            for(int i=0; i<p[0];i++){
                                datos[0] = datos[0]+proyectoList.get(i)+"/";
                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    adapter = new AdaptadorRegistros(datos[0].split("/"));
                                    recycelerV.setAdapter(adapter);
                                }
                            });
                        }
                    }).start();
                }else{
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            EmpresaBD conexion = EmpresaBD.gettAppDatabase(getBaseContext());
                            proyectoList = conexion.pDAO().buscarNombre("%"+nombre.getText().toString()+"%");
                            p[0] = proyectoList.size();
                            for (int i = 0; i < p[0]; i++) {
                                datos[0] = datos[0] + proyectoList.get(i)+"/";
                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    adapter = new AdaptadorRegistros(datos[0].split("/"));
                                    recycelerV.setAdapter(adapter);
                                }
                            });
                        }
                    }).start();
                }

            }
        });




    }//onCreate

    public void eliminarProyecto(View v){
        if (verificarCajasVacias()){
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
        }else{
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getBaseContext(), "Debes completar todos los campos", Toast.LENGTH_LONG).show();
                }
            });
        }
    }//eliminar

    public void buscar(View v){
        if(numero.getText().toString().isEmpty()==false){
            new Thread(new Runnable() {
                @Override
                public void run() {
                   try {
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
                   }catch (Exception e){

                   }

                }
            }).start();
        }
    }


    public boolean verificarCajasVacias(){
        if(nombre.getText().toString().isEmpty()||numero.getText().toString().isEmpty()
                ||ubicacion.getText().toString().isEmpty()||numDepartamento.getText().toString().isEmpty()){
            return false;
        }else{
            return true;
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
