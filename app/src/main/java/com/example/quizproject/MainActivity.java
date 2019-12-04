package com.example.quizproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private QuizModel[] quizModels = new QuizModel[]{
            new QuizModel(R.string.q10,true),
            new QuizModel(R.string.q1,true),
            new QuizModel(R.string.q2,false),
            new QuizModel(R.string.q3,true),
            new QuizModel(R.string.q4,false),
            new QuizModel(R.string.q5,true),
            new QuizModel(R.string.q6,false),
            new QuizModel(R.string.q7,true),
            new QuizModel(R.string.q8,true),
            new QuizModel(R.string.q9,false),
    };
    final int incPro = (int) Math.ceil( 100.0/quizModels.length);

    private Button btnTrue , btnFalse;
    private TextView txtQuestion , txtQues;
    private ProgressBar progressBar;
    private int mUserValue;
    private int no;


    private int mQuestionIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intiWidget();

        QuizModel model = quizModels[mQuestionIndex];

        txtQuestion.setText(model.getmQuestion());



        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userAnswerEvaluate(true);
               changeButton();
            }
        });

        btnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userAnswerEvaluate(false);
                changeButton();

            }
        });
    }

    public void changeButton(){
        no++;
        mQuestionIndex +=1;
        mQuestionIndex = mQuestionIndex % 10;
        if (mQuestionIndex == 0){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("The quiz is Finished");
            alert.setMessage("The Score is " + mUserValue);
            alert.setPositiveButton("Finish the quiz", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            alert.show();
        }
        QuizModel model = quizModels[mQuestionIndex];
        txtQuestion.setText(model.getmQuestion());
        progressBar.incrementProgressBy(incPro);
        txtQues.setText(no + " / 10");
    }

    public void intiWidget(){
        btnTrue = (Button)findViewById(R.id.btnTrue);
        btnFalse = (Button) findViewById(R.id.btnFalse);
        txtQuestion = (TextView) findViewById(R.id.txtQuestions);
        txtQues = (TextView) findViewById(R.id.txtQues);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
    }

    public void userAnswerEvaluate(boolean guess){

        if (quizModels[mQuestionIndex].ismAnswer() == guess){
            Toast.makeText(getApplicationContext(),R.string.correct_text,Toast.LENGTH_SHORT).show();
            mUserValue = mUserValue + 1;
        }else {
            Toast.makeText(getApplicationContext(),R.string.incorrect_text,Toast.LENGTH_SHORT).show();
        }
    }
}
