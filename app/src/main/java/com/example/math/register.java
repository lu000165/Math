package com.example.math;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.math.data.User;
import com.example.math.opener.MyOpener;
import com.example.math.opener.ProfileController;
import com.example.math.opener.UserController;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class register extends AppCompatActivity {
    private RadioButton parent;
    private RadioButton student;
    private TextView name;
    private TextView password;
    private TextView password2;
    private TextView email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        parent = findViewById(R.id.register_isParent);
        student = findViewById(R.id.register_isStudent);
        name = findViewById(R.id.register_name);
        password = findViewById(R.id.register_password);
        password2 = findViewById(R.id.register_password2);
        email = findViewById(R.id.register_email);
    }

    public void addUser(View view) {

        String nameString = name.getText().toString();
        String passString = password.getText().toString();
        String passString2= password2.getText().toString();
        Boolean isParent = parent.isChecked();
        String emailString = email.getText().toString();

        if (!(passString.equals(passString2))){//check if passwords are match
            Toast.makeText(getApplicationContext(), "Repeated Password does not match",Toast.LENGTH_SHORT).show();

        }else if (!checkPass(passString)) {//restrict the password type
            Toast.makeText(getApplicationContext(), "The password need to contains specail characeters, captial letters and numbers. Minimium length is 8. ", Toast.LENGTH_SHORT).show();
        }else{//able to register user
            MyOpener opener = new MyOpener(this);
            SQLiteDatabase db=opener.getWritableDatabase();
            UserController.addUser(db,nameString,passString,emailString,isParent);

            //create profile for user
            User user = UserController.getUser(db,nameString,passString);
            if (!user.isParent()) {//if student
                int userId = user.getId();
                ProfileController.createProfile(db, userId);
                //back to login page
                Intent intent = new Intent(register.this, MainActivity.class);
                startActivity(intent);
            }

        }
    }
    public boolean checkPass(String pass){
        Pattern passPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-zA-Z])(.{8,16})$");
        Matcher matcher =passPattern.matcher(pass);
        if (matcher.matches()){
            return true;
        }
        return false;
    }

    public void loginIntent(View view) {
        Intent intent = new Intent(register.this, MainActivity.class);
        startActivity(intent);
    }
}