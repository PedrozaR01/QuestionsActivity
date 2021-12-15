package com.example.preguntas;

public class Respuestas {
    private String pregunta;
    private String Respuesta;

    public Respuestas (){ }

    public Respuestas(String pregunta, String respuesta) {
        this.pregunta = pregunta;
        Respuesta = respuesta;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuesta() {
        return Respuesta;
    }

    public void setRespuesta(String respuesta) {
        Respuesta = respuesta;
    }
}
