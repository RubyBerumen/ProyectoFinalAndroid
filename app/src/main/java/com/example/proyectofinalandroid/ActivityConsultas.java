package com.example.proyectofinalandroid;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import baseDeDatos.EmpresaBD;
import entidades.Proyecto;

public class ActivityConsultas extends Activity {

    EditText nombre,numero;
    RecyclerView recycelerV;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutM;
    List<Proyecto> proyectoList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas);

        nombre=findViewById(R.id.txt_Nombre4);
        numero=findViewById(R.id.txt_Numero4);
        recycelerV=findViewById(R.id.txt_RecyclerV);
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
}


class AdaptadorRegistros extends RecyclerView.Adapter<AdaptadorRegistros.MyViewHolder>{

    private String[] mDataset;

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public MyViewHolder(TextView t) {
            super(t);
            textView = t;
        }
    }

    public AdaptadorRegistros(String[] mDataset) {
        this.mDataset = mDataset;
    }

    @NonNull
    @Override
    public AdaptadorRegistros.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TextView tv = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.textview_recyclerview,parent,false);
        MyViewHolder vh = new MyViewHolder(tv);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textView.setText(mDataset[position]);
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }


}//AdaptadorR

