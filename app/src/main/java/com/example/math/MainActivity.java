package com.example.math;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.math.data.User;
import com.example.math.opener.MyOpener;
import com.example.math.opener.UserController;

public class MainActivity extends AppCompatActivity {

    Button login,register;
    EditText name,password;

    TextView attemptCount;
    int counter = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = (Button)findViewById(R.id.login_login);
        register = (Button)findViewById(R.id.login_register);
        name = (EditText)findViewById(R.id.login_name);
        password = (EditText)findViewById(R.id.login_password);


        attemptCount = (TextView)findViewById(R.id.login_attempLeft);
        attemptCount.setVisibility(View.GONE);

/*        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ed1.getText().toString().equals("admin") &&
                        ed2.getText().toString().equals("admin")) {
                    Toast.makeText(getApplicationContext(),
                            "Redirecting...",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Wrong Credentials",Toast.LENGTH_SHORT).show();

                            tx1.setVisibility(View.VISIBLE);
                    tx1.setBackgroundColor(Color.RED);
                    counter--;
                    tx1.setText(Integer.toString(counter));

                    if (counter == 0) {
                        b1.setEnabled(false);
                    }
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });*/
    }

    public void loginUser(View view) {
        String nameString = name.getText().toString();
        String passwordString = password.getText().toString();

        MyOpener opener = new MyOpener(this);
        SQLiteDatabase db = opener.getWritableDatabase();

        User user = UserController.getUser(db,nameString,passwordString);
        if (user == null){
            Toast.makeText(getApplicationContext(), "Wrong Credentials",Toast.LENGTH_SHORT).show();
            attemptCount.setVisibility(View.VISIBLE);
            attemptCount.setBackgroundColor(Color.RED);
            counter--;
            attemptCount.setText(Integer.toString(counter));

            if (counter == 0) {
                login.setEnabled(false);
            }
        }else{
            Toast.makeText(getApplicationContext(),
                    "Redirecting...",Toast.LENGTH_SHORT).show();

            //Intent
            if (user.isParent()){
                Intent intent = new Intent(MainActivity.this, parentMenu.class);
                intent.putExtra("userid",user.getId());
                startActivity(intent);
            }else {
                Intent intent = new Intent(MainActivity.this, studentMenu.class);
                intent.putExtra("userid",user.getId());
                startActivity(intent);
            }
        }
    }

    public void register(View view) {
        Intent intent = new Intent(MainActivity.this, register.class);
        startActivity(intent);
    }
}