package com.example.toshibapc.ejemplosubirverimagen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final int REGISTRO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void registrarse(View view){
        Intent i = new Intent(this, PaginaRegistro.class);
        startActivityForResult(i, REGISTRO);
    }
}
