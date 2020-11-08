package com.example.math;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class studentMenu extends AppCompatActivity {
    Integer id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_menu);

        Intent intent = getIntent();
        id =intent.getIntExtra("userid",0);
    }

    public void plusQuestion(View view) {
        Intent intent = new Intent(studentMenu.this, question.class);
        intent.putExtra("userid",id);
        intent.putExtra("subject","plus");
        startActivity(intent);
    }

    public void minusQuestion(View view) {
        Intent intent = new Intent(studentMenu.this, question.class);
        intent.putExtra("userid",id);
        intent.putExtra("subject","minus");
        startActivity(intent);
    }
    public void multQuestion(View view) {
        Intent intent = new Intent(studentMenu.this, question.class);
        intent.putExtra("userid",id);
        intent.putExtra("subject","multiplication");
        startActivity(intent);
    }
    public void divQuestion(View view) {
        Intent intent = new Intent(studentMenu.this, question.class);
        intent.putExtra("userid",id);
        intent.putExtra("subject","division");
        startActivity(intent);
    }
}