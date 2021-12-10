package com.example.proyectofinalandroid;

import android.app.Activity;
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

public class ActivityCambios extends AppCompatActivity {

    EditText nombre,numero,ubicacion,numDepartamento;
    RecyclerView recycelerV;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutM;
    List<Proyecto> proyectoList;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambios);
        nombre=findViewById(R.id.txt_Nombre3);
        numero=findViewById(R.id.txt_Numero3);
        ubicacion=findViewById(R.id.txt_Ubicacion3);
        numDepartamento=findViewById(R.id.txt_NumeroDpto3);

        recycelerV=findViewById(R.id.txt_RecyclerView);
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

    public void modificarProyecto(View v){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Proyecto p = null;
                EmpresaBD conexion = EmpresaBD.gettAppDatabase(getBaseContext());
                if(!numero.getText().toString().isEmpty()){
                    int num = Integer.parseInt(""+numero.getText().toString());
                    p = (Proyecto) conexion.pDAO().buscarPorNumP(num);
                }

                if (p!=null){
                    if(!nombre.getText().toString().isEmpty()&&!numero.getText().toString().isEmpty()&&
                        !ubicacion.getText().toString().isEmpty()&&!numDepartamento.getText().toString().isEmpty()){

                        try {
                            String nom = nombre.getText().toString();
                            int no = Integer.parseInt(numero.getText().toString());
                            String ubi = ubicacion.getText().toString();
                            byte numDpto = Byte.parseByte(numDepartamento.getText().toString());
                            conexion.pDAO().modificarProyecto(no,nom,ubi,numDpto);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getBaseContext(), "Se modificó correctamente", Toast.LENGTH_LONG).show();
                                }
                            });

                        }catch (Exception e){

                        }
                    }else{
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getBaseContext(), "Debes completar todos los campos", Toast.LENGTH_LONG).show();
                            }
                        });
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
