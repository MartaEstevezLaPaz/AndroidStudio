package com.example.toshibapc.ejemplosubirverimagen;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class PaginaRegistro extends AppCompatActivity {

    Spinner spCursos;
    CheckBox profesor;
    EditText codigoProfesor;

    private JSONArray jSONArray;
    private JSONObject jsonObject = new JSONObject();
    private DevuelveJSON devuelveJSON;
    private Cursos curso = new Cursos();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_registro);
        spCursos = (Spinner) findViewById(R.id.spinnerCursos);
        profesor = (CheckBox) findViewById(R.id.checkbox_profesor);
        codigoProfesor = (EditText) findViewById(R.id.etCodigo);
        codigoProfesor.setEnabled(false);

        devuelveJSON = new DevuelveJSON();

        new GetCursos().execute();

        comprobarCheckBox();

    }

    private void comprobarCheckBox() {
        profesor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if ( isChecked ){
                    System.out.println("checked");
                    spCursos.setEnabled(false);
                    spCursos.setClickable(false);
                    codigoProfesor.setEnabled(true);
                } else {
                    System.out.println("not checked");
                    spCursos.setEnabled(true);
                    spCursos.setClickable(true);
                    codigoProfesor.setEnabled(false);
                }
            }
        });
    }

    class GetCursos extends AsyncTask<String, String, JSONArray> {
        private ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            pDialog = new ProgressDialog(PaginaRegistro.this);
            pDialog.setMessage("Cargando...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        @Override
        protected JSONArray doInBackground(String... args) {
            try{
                String consultaSQL = "SELECT * FROM Cursos";
                HashMap<String, String> parametrosPost = new HashMap<>();
                parametrosPost.put("ins_sql",consultaSQL);
                jSONArray = devuelveJSON.sendRequest(parametrosPost);
                if (jSONArray != null) {
                    return jSONArray;
                }
            } catch (Exception e) {
                System.out.println("error doinbackground");
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(JSONArray json) {
            if (pDialog != null && pDialog.isShowing()) {
                pDialog.dismiss();
            }
            if (json != null) {
                List<String> listaCursos = new ArrayList<String>();
                for (int i = 0; i < json.length(); i++) {
                    try {
                        jsonObject = json.getJSONObject(i);
                        curso = new Cursos(jsonObject.getInt("idCurso"),jsonObject.getString("nombreCurso"));

                        listaCursos.add(curso.getNombre());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    //Rellenamos el spinner de los Cursos
                    ArrayAdapter<String> spinnerAdapter = new ArrayAdapter(getBaseContext(), R.layout.support_simple_spinner_dropdown_item, listaCursos);
                    spCursos.setAdapter(spinnerAdapter);
                }


            } else {
                Toast.makeText(PaginaRegistro.this, "Error", Toast.LENGTH_SHORT).show();
            }

        }
    }

}
