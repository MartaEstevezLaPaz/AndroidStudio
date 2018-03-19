package com.example.toshibapc.ejemplosubirverimagen;

/**
 * Created by Toshiba PC on 06/03/2018.
 */

public class Cursos {
    int idCurso;
    String nombre;

    public Cursos() {}

    public Cursos(int idCurso, String nombre) {
        this.idCurso = idCurso;
        this.nombre = nombre;
    }

    public int getIdCurso() { return idCurso; }

    public void setIdCurso(int idCurso) { this.idCurso = idCurso; }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }
}
