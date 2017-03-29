package com.example.cprieto.repeticionexamen2evaluacion.bbdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.cprieto.repeticionexamen2evaluacion.datos.Contacto;

/**
 * Created by cprieto on 15/03/17.
 * <p>
 * <p>
 * Manejadora donde tendremos todos los metodos referentes a la BBDD
 */

public class DBAdapter {

    private SQLiteDatabase db;
    private DBHelper helper;

    public DBAdapter(Context context) {

        helper = new DBHelper(context);

    }

    /**
     * Abrimos una conexion
     */
    public void abrirBD() {

        try {

            db = helper.getWritableDatabase();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Cerramos una conexion
     */
    public void cerrarBD() {

        try {

            helper.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Hacemos un select a la BBDD para traernos todos los datos en un cursor
     *
     * @return
     */
    public Cursor getContactos() {

        String[] columnas = {
                Constantes.Contactos.COLUMN_ID,
                Constantes.Contactos.COLUMN_NOMBRE,
                Constantes.Contactos.COLUMN_EDAD,
                Constantes.Contactos.COLUMN_TELEFONO,
                Constantes.Contactos.COLUMN_SEXO
        };

        return db.query(Constantes.Contactos.TB_NAME, columnas, null, null, null, null, null);

    }

    /**
     * Metodo que recoge un solo contacto de la BBDD mediante un id
     *
     * @param id
     * @return
     */
    public Contacto getContacto(int id) {

        Cursor c = db.query(Constantes.Contactos.TB_NAME, new String[]{Constantes.Contactos.COLUMN_ID,
                        Constantes.Contactos.COLUMN_NOMBRE, Constantes.Contactos.COLUMN_EDAD,
                        Constantes.Contactos.COLUMN_TELEFONO, Constantes.Contactos.COLUMN_SEXO}, Constantes.Contactos.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (c != null)
            c.moveToFirst();

        /*De esta forma la variable contacto es redundante
        Contacto contacto = new Contacto(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4));
        return contacto;*/

        //De esta forma es mejor
        return new Contacto(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4));
    }

    /**
     * Metodo que inserta un nuevo contacto pasandole unos parametros
     *
     * @param nombre
     * @param edad
     * @param telefono
     * @param sexo
     * @return
     */
    public boolean insertarContacto(String nombre, String edad, String telefono, String sexo) {

        boolean devolver = false;

        try {

            ContentValues cv = new ContentValues();
            cv.put(Constantes.Contactos.COLUMN_NOMBRE, nombre);
            cv.put(Constantes.Contactos.COLUMN_EDAD, edad);
            cv.put(Constantes.Contactos.COLUMN_TELEFONO, telefono);
            cv.put(Constantes.Contactos.COLUMN_SEXO, sexo);

            long resultado = db.insert(Constantes.Contactos.TB_NAME, null, cv);

            if (resultado > 0) {
                devolver = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return devolver;

    }

    /**
     * Actualizamos un contacto pasandole sus nuevas variables
     *
     * @param id
     * @param newNombre
     * @param newTelefono
     * @param newEdad
     * @param newSexo
     * @return
     */
    public boolean actualizaContacto(int id, String newNombre, String newEdad, String newTelefono, String newSexo) {

        boolean devolver = false;

        try {

            ContentValues cv = new ContentValues();
            cv.put(Constantes.Contactos.COLUMN_NOMBRE, newNombre);
            cv.put(Constantes.Contactos.COLUMN_EDAD, newEdad);
            cv.put(Constantes.Contactos.COLUMN_TELEFONO, newTelefono);
            cv.put(Constantes.Contactos.COLUMN_SEXO, newSexo);

            int resultado = db.update(Constantes.Contactos.TB_NAME, cv, Constantes.Contactos.COLUMN_ID +
                    "=?", new String[]{String.valueOf(id)});

            if (resultado > 0) {
                devolver = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return devolver;

    }

    /**
     * Metodo que borra un contacto de la BBDD dependiendo de su id
     *
     * @param id
     * @return
     */
    public boolean borrarContacto(int id) {

        boolean devolver = false;

        try {

            int resultado = db.delete(Constantes.Contactos.TB_NAME, Constantes.Contactos.COLUMN_ID + " =?", new String[]{String.valueOf(id)});

            if (resultado > 0) {
                devolver = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return devolver;

    }

}
