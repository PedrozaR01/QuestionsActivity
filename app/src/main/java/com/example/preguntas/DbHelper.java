package com.example.preguntas;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.preguntas.Contrac.*;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "PreguntasData.db";
    private static int DATABASE_VERSION = 2;

    private SQLiteDatabase db;

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                tablaPreguntas.TABLE_NAME + " ( " +
                tablaPreguntas._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                tablaPreguntas.COLUMN_QUESTION + " TEXT, " +
                tablaPreguntas.COLUMN_OPTION1 + " TEXT, " +
                tablaPreguntas.COLUMN_OPTION2 + " TEXT, " +
                tablaPreguntas.COLUMN_OPTION3 + " TEXT " +
                " ) ";

        final String SQL_CREATE_ANSWERS_TABLE = "CREATE TABLE " +
                tablaResultados.TABLE_NAME + " ( " +
                tablaResultados._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                tablaResultados.COLUMN_QUESTION + " TEXT, " +
                tablaResultados.COLUMN_ANSWER + " TEXT " +
                " ) ";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        db.execSQL(SQL_CREATE_ANSWERS_TABLE);
        fillQuestionsTable();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + tablaPreguntas.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + tablaResultados.TABLE_NAME);
        onCreate(db);

    }

    private void fillQuestionsTable(){
        Preguntas q1 = new Preguntas("Ves a otro niño maltratando a un perro", "Te divierte", "Te pones triste", "Te enojas");
        addQuestion(q1);
        Preguntas q2 = new Preguntas("Un amigo tuyo está llorando", "Te pone triste", "Te burlas", "No haces nada");
        addQuestion(q2);
        Preguntas q3 = new Preguntas("Te equivocas y otros se rien", "Te ries igualmente", "Te enojas", "Te confundes");
        addQuestion(q3);
        Preguntas q4 = new Preguntas("Tu profesr(a) te regaña porque le pegaste a otro compañer@", "Te enojas", "Te avergúenzas", "Te pones triste");
        addQuestion(q4);
        Preguntas q5 = new Preguntas("Un niño habla mucho", "Te fastidias", "Te agrada", "No te importa");
        addQuestion(q5);

    }

    private void addQuestion(Preguntas pregunta){
        ContentValues cv = new ContentValues();
        cv.put(tablaPreguntas.COLUMN_QUESTION, pregunta.getPregunta());
        cv.put(tablaPreguntas.COLUMN_OPTION1, pregunta.getRspuesta1());
        cv.put(tablaPreguntas.COLUMN_OPTION2, pregunta.getRspuesta2());
        cv.put(tablaPreguntas.COLUMN_OPTION3, pregunta.getRspuesta3());
        db.insert(tablaPreguntas.TABLE_NAME, null, cv);
    }

    public void fillAnswersTable(String pregunta, String respuesta) {
        Respuestas r1 = new Respuestas(pregunta, respuesta);
        addAnswer(r1);
    }


    public void addAnswer(Respuestas respuesta){
        this.db = db;
        db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(tablaResultados.COLUMN_QUESTION, respuesta.getPregunta());
        cv.put(tablaResultados.COLUMN_ANSWER, respuesta.getRespuesta());
        db.insert(tablaResultados.TABLE_NAME, null, cv);


    }

    @SuppressLint("Range")
    public List<Preguntas> getAllQuestions() {
        List<Preguntas> questionlist = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + tablaPreguntas.TABLE_NAME, null);
        if (c.moveToFirst()) {
            do{
                Preguntas pregunta = new Preguntas();
                pregunta.setPregunta(c.getString(c.getColumnIndex(tablaPreguntas.COLUMN_QUESTION)));
                pregunta.setRspuesta1(c.getString(c.getColumnIndex(tablaPreguntas.COLUMN_OPTION1)));
                pregunta.setRspuesta2(c.getString(c.getColumnIndex(tablaPreguntas.COLUMN_OPTION2)));
                pregunta.setRspuesta3(c.getString(c.getColumnIndex(tablaPreguntas.COLUMN_OPTION3)));
                questionlist.add(pregunta);
            } while(c.moveToNext());
        }

        c.close();
        return questionlist;

    }
}
