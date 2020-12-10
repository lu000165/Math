package com.example.math;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.math.data.User;
import com.example.math.opener.MyOpener;
import com.example.math.opener.UserController;

public class summary extends AppCompatActivity {

    private TextView nameView, markView,subjectView;
    private Integer id, mark;
    private String subject,name,emails;

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
        User parent = UserController.getParent(db);
        emails=parent.getEmail();
        Log.e("email","email address"+emails);

    }

    public void backToMenu(View view) {
        Intent intent = new Intent(summary.this, studentMenu.class);
        intent.putExtra("userid",id);
        startActivity(intent);
    }

    /*public void sendEmail(View view) {
        Intent email = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", emails+"", null));
        //email.putExtra(Intent.EXTRA_EMAIL, emails);
        email.putExtra(Intent.EXTRA_SUBJECT,name+"'s Summary");
        email.putExtra(Intent.EXTRA_TEXT,name + " got "+mark +" /10 in subject "+subject );
        email.setType("message/rfc822");
        startActivity(Intent.createChooser(email,"Choose an Email client :"));
    }*/
    public void sendEmail(View view) {
        Log.i("Send email", "");

        String[] TO = {emails+""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");

        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, name+"'s Summary");
        emailIntent.putExtra(Intent.EXTRA_TEXT, name + " got "+mark +" /10 in subject "+subject);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email.", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(summary.this,
                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }


}