package com.example.cprieto.repeticionexamen2evaluacion.datos;

/**
 * Created by cprieto on 16/03/17.
 * <p>
 * <p>
 * Clase donde tendremos todos los getters y setters de un contacto
 */

public class Contacto {

    private int id;
    private String nombre, telefono, edad, sexo;

    public Contacto() {

    }

    public Contacto(int id, String nombre, String telefono, String edad, String sexo) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.edad = edad;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
