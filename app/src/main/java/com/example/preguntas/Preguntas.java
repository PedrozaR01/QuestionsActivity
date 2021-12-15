package com.example.preguntas;

public class Preguntas {
    private String pregunta;
    private String rspuesta1;
    private String rspuesta2;
    private String rspuesta3;

    public Preguntas(){}

    public Preguntas(String pregunta, String rspuesta1, String rspuesta2, String rspuesta3) {
        this.pregunta = pregunta;
        this.rspuesta1 = rspuesta1;
        this.rspuesta2 = rspuesta2;
        this.rspuesta3 = rspuesta3;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getRspuesta1() {
        return rspuesta1;
    }

    public void setRspuesta1(String rspuesta1) {
        this.rspuesta1 = rspuesta1;
    }

    public String getRspuesta2() {
        return rspuesta2;
    }

    public void setRspuesta2(String rspuesta2) {
        this.rspuesta2 = rspuesta2;
    }

    public String getRspuesta3() {
        return rspuesta3;
    }

    public void setRspuesta3(String rspuesta3) {
        this.rspuesta3 = rspuesta3;
    }
}
