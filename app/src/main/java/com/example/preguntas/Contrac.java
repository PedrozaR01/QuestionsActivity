package com.example.preguntas;

import android.provider.BaseColumns;

public final class Contrac {

    private Contrac(){}

    public static class tablaPreguntas implements BaseColumns {
        public static final String TABLE_NAME = "emoc_preguntas";
        public static final String COLUMN_QUESTION = "preguntas";
        public static final String COLUMN_OPTION1 = "opcion1";
        public static final String COLUMN_OPTION2 = "opcion2";
        public static final String COLUMN_OPTION3 = "opcion3";
    }

    public static class tablaResultados implements BaseColumns {
        public static final String TABLE_NAME = "emoc_resultados";
        public static final String COLUMN_QUESTION = "pregunta";
        public static final String COLUMN_ANSWER = "respuesta";
    }
}
