package com.example.cprieto.repeticionexamen2evaluacion.bbdd;

import android.provider.BaseColumns;

/**
 * Created by cprieto on 15/03/17.
 * <p>
 * <p>
 * Esta clase contiene todas las constantes que se usa en la BBDD
 */

final class Constantes {

    private Constantes() {

    }

    /**
     * Creamos una clase por cada tabla que tengamos
     */
    static final class Contactos implements BaseColumns {

        private Contactos() {

        }

        //Propiedades
        static final String DB_NAME = "contactos.db";
        static final String TB_NAME = "contacto";
        static final int DB_VERSION = 1;

        //Columnas
        static final String COLUMN_ID = "id";
        static final String COLUMN_NOMBRE = "nombre";
        static final String COLUMN_EDAD = "edad";
        static final String COLUMN_TELEFONO = "telefono";
        static final String COLUMN_SEXO = "sexo";


        //Crear tabla
        static final String CREATE_TB = "CREATE TABLE " + TB_NAME + " (" + COLUMN_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NOMBRE + " TEXT, " +
                COLUMN_EDAD + " TEXT, " + COLUMN_TELEFONO + " TEXT, " + COLUMN_SEXO + " TEXT);";

        //Borrar tabla
        static final String DROP_TB = "DROP TABLE IF EXISTS " + TB_NAME;

    }

}
