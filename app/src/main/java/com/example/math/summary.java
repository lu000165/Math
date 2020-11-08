package com.example.math;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.math.data.User;
import com.example.math.opener.MyOpener;
import com.example.math.opener.UserController;

public class summary extends AppCompatActivity {

    private TextView nameView, markView,subjectView;
    private Integer id, mark;
    private String subject,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        Intent intent = getIntent();
        id=intent.getIntExtra("userid",0);
        subject=intent.getStringExtra("subject");
        mark=intent.getIntExtra("mark",0);

        nameView=findViewById(R.id.nameSummary);
        markView=findViewById(R.id.markSummay);
        subjectView=findViewById(R.id.subjectSummay);
        markView.setText(mark+"");
        subjectView.setText(subject+"");

        MyOpener opener = new MyOpener(this);
        SQLiteDatabase db=opener.getWritableDatabase();
        User student = UserController.getUserbyID(db,id);
        name=student.getName();
        nameView.setText(name);

    }

    public void backToMenu(View view) {
        Intent intent = new Intent(summary.this, studentMenu.class);
        intent.putExtra("userid",id);
        startActivity(intent);
    }
}