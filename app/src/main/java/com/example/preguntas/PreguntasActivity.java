package com.example.preguntas;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Collections;
import java.util.List;

public class PreguntasActivity extends AppCompatActivity {
    private TextView textViewQuestion;
    private TextView textViewQuestionCount;
    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton radioButton;
    private Button bttnConfirmNext;


    private ColorStateList textColorDefaultRb;
    private int questionCounter;
    private int questionCounttotal;
    private Preguntas currentQ;

    private boolean answered;


    private List<Preguntas> questionlist;

    public PreguntasActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas);

        textViewQuestion = findViewById(R.id.text_question);
        textViewQuestionCount = findViewById(R.id.text_view_preguntacont);
        rbGroup = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.radio_button1);
        rb2 = findViewById(R.id.radio_button2);
        rb3 = findViewById(R.id.radio_button3);
        bttnConfirmNext = findViewById(R.id.btn_sig);

        textColorDefaultRb = rb1.getTextColors();

        DbHelper dbhelper = new DbHelper(this);
        questionlist = dbhelper.getAllQuestions();
        questionCounttotal = questionlist.size();
        Collections.shuffle(questionlist);

        showNextQuestion();

        bttnConfirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!answered) {
                    if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked()){
                        saveAnswer();
                    } else {
                        Toast.makeText(PreguntasActivity.this, "Selecciona una opción", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    showNextQuestion();

                }
            }
        });

    }

    private void showNextQuestion() {
        rb1.setTextColor(textColorDefaultRb);
        rb2.setTextColor(textColorDefaultRb);
        rb3.setTextColor(textColorDefaultRb);
        rbGroup.clearCheck();

        if (questionCounter < questionCounttotal) {
            currentQ = questionlist.get(questionCounter);

            textViewQuestion.setText(currentQ.getPregunta());
            rb1.setText(currentQ.getRspuesta1());
            rb2.setText(currentQ.getRspuesta2());
            rb3.setText(currentQ.getRspuesta3());

            questionCounter++;
            textViewQuestionCount.setText("Pregunta :" + questionCounter + "/" + questionCounttotal);
            answered = false;
            bttnConfirmNext.setText("Confirmar");
        } else {
            finishQuiz();
        }
    }

    private void saveAnswer(){
        DbHelper dbhelper = new DbHelper(this);
        Respuestas respuesta = new Respuestas();
        rbGroup = (RadioGroup) findViewById(R.id.radio_group);
        answered = true;

        int selectedId = rbGroup.getCheckedRadioButtonId();

        radioButton = (RadioButton) findViewById(selectedId);

        Toast.makeText(PreguntasActivity.this,
                radioButton.getText(), Toast.LENGTH_SHORT).show();

        String questionToAnswer = textViewQuestion.getText().toString();
        String questionAnswer = radioButton.getText().toString();
        respuesta.setPregunta(questionToAnswer);
        respuesta.setRespuesta(questionAnswer);
        dbhelper.fillAnswersTable(respuesta.getPregunta(), respuesta.getRespuesta());

        if (questionCounter < questionCounttotal) {
            bttnConfirmNext.setText("Siguiente");
        }else{
            bttnConfirmNext.setText("Terminar");
        }
    }
    private void finishQuiz(){
        finish();
    }
}