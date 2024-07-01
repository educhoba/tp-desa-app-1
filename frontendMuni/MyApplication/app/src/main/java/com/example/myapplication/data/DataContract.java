package com.example.myapplication.data;

import android.provider.BaseColumns;

public class DataContract {

    /**
     * Clase interna para la definición de la tabla.
     * */
    public static abstract class ReclamosEntry implements BaseColumns{
        //BaseColumns se agrega para agregar una columna _ID extra (recomendado)
        public static final String TABLE_NAME = "reclamosLocalTable";
        //Definición de los campos de la tabla
        public static final String ID = "idReclamo";
        public static final String DOCUMENTO = "documento";
        public static final String LEGAJO = "legajo";
        public static final String IDSITIO = "idSitio";
        public static final String IDDESPERFECTO = "idDesperfecto";
        public static final String DESCRIPCION = "descripcion";
        public static final String ESTADO = "estado";
        public static final String IDRECLAMOUNIFICADO = "IdReclamoUnificado";
        public static final String SITIOMANUAL = "sitioManual";
        public static final String IMAGEN_1 = "imagen1";
        public static final String IMAGEN_2 = "imagen2";
        public static final String IMAGEN_3 = "imagen3";
        public static final String IMAGEN_4 = "imagen4";
        public static final String IMAGEN_5 = "imagen5";
        public static final String IMAGEN_6 = "imagen6";
        public static final String IMAGEN_7 = "imagen7";
    }


}
