package com.example.math;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.math.opener.MyOpener;
import com.example.math.opener.ProfileController;

import java.util.Random;

public class question extends AppCompatActivity {
    private Integer id, level, correctAnswer,studentAnswer, counter, mark, tempMark;
    private String subject;
    private TextView numberView1, numberView2, symbolView,questionNumberView;
    private EditText answerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Intent intent = getIntent();
        id=intent.getIntExtra("userid",0);
        subject=intent.getStringExtra("subject");

        MyOpener opener = new MyOpener(this);
        SQLiteDatabase db=opener.getWritableDatabase();
        level=ProfileController.getLevel(db,id,subject);

        numberView1=findViewById(R.id.firstNumber);
        numberView2=findViewById(R.id.secondNumber);
        symbolView=findViewById(R.id.symbol);
        questionNumberView=findViewById(R.id.questionNumber);
        correctAnswer=0;
        mark= intent.getIntExtra("mark",0);
        counter= intent.getIntExtra("counter",0);
        setQuestion(level,subject);
        if (counter ==0){
            counter=1;
        }else{
            counter=intent.getIntExtra("counter",0);
        }
        questionNumberView.setText(counter+"");


    }
    private void setQuestion(int level,String subject){
        Random rand = new Random();
        int number1 = rand.nextInt(level);
        int number2 = rand.nextInt(level);
        int number3= number1*number2;
        if (subject.equals("plus")){
            symbolView.setText("+");
            numberView1.setText(number1+"");
            numberView2.setText(number2+"");
            correctAnswer=number1+number2;
        }else if (subject.equals("minus")){
            symbolView.setText("-");
            if(number1>number2){
                numberView1.setText(number1+"");
                numberView2.setText(number2+"");
                correctAnswer=number1-number2;
            }else{
                numberView2.setText(number1+"");
                numberView1.setText(number2+"");
                correctAnswer=number2-number1;
            }
        }else if (subject.equals("multiplication")){
            symbolView.setText("x");
            numberView1.setText(number1+"");
            numberView2.setText(number2+"");
            correctAnswer=number1*number2;
        }else{
            symbolView.setText("/");
            numberView1.setText(number3+"");
            if(number2!=0){
                numberView2.setText(number2+"");
                correctAnswer=number3/number2;
            }else if(number1!=0){
                numberView2.setText(number1+"");
                correctAnswer=number3/number1;
            }else{
                numberView2.setText(number3+"");
                correctAnswer=number3/number3;
            }
        }
    }

    public void checkAnswer(View view) {
        answerView=findViewById(R.id.answer);
        studentAnswer=Integer.parseInt(answerView.getText().toString());
        Log.e("student answer",studentAnswer+"");
        Log.e("correct answer",correctAnswer+"");
        if (studentAnswer==correctAnswer){
            Toast.makeText(getApplicationContext(), "Excellent!",Toast.LENGTH_SHORT).show();
            mark++;
            Log.e("Mark",mark+"");
        }else{
            Toast.makeText(getApplicationContext(), "Wrong Answer!",Toast.LENGTH_SHORT).show();
        }
    }

    public void Next(View view) {
        counter=counter+1;
        if (counter<11){
            Intent intentNext= new Intent(question.this,question.class);
            intentNext.putExtra("userid",id);
            intentNext.putExtra("subject",subject);
            intentNext.putExtra("counter",counter);
            intentNext.putExtra("mark",mark);
            finish();
            overridePendingTransition(0, 0);
            startActivity(intentNext);
            overridePendingTransition(0, 0);
        }else{
            Intent intentDone= new Intent(question.this,summary.class);
            intentDone.putExtra("userid",id);
            intentDone.putExtra("subject",subject);
            intentDone.putExtra("counter",counter);
            intentDone.putExtra("mark",mark);
            startActivity(intentDone);
        }

    }
}