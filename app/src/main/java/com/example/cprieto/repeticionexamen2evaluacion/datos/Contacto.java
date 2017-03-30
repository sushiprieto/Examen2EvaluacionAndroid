package com.example.cprieto.repeticionexamen2evaluacion.datos;

/**
 * Created by cprieto on 16/03/17.
 * <p>
 * <p>
 * Clase donde tendremos todos los getters y setters de un contacto
 */

public class Contacto {

    private int id;
    private String nombre, edad, telefono, sexo;

    public Contacto() {

    }

    public Contacto(int id, String nombre, String edad, String telefono, String sexo) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.telefono = telefono;
        this.sexo = sexo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
