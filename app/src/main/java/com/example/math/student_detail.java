package com.example.math;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.math.opener.MyOpener;
import com.example.math.opener.ProfileController;

public class student_detail extends AppCompatActivity {
    private Integer id, plusLevelValue, minusLevelValue, multLevelValue, divLevelValue;
    private Integer newPlusValue, newMinusValue, newMultValue,newDivValue;
    private String name;
    private TextView nameView;
    private EditText plusView, minusView, multView, divView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);

        Intent intent = getIntent();
        name= intent.getStringExtra("name");
        Log.e("IntentExtra",name);
        nameView=findViewById(R.id.detailName);
        nameView.setText(name);
        id =intent.getIntExtra("ID",0);

        MyOpener opener = new MyOpener(this);
        SQLiteDatabase db=opener.getWritableDatabase();
        plusLevelValue = ProfileController.getLevel(db,id,"plus");
        Log.e("plus value",""+plusLevelValue);
        plusView=findViewById(R.id.plusLevel);
        Log.e("plus view",plusView.toString());
        plusView.setText(Integer.toString(plusLevelValue));
        minusLevelValue= ProfileController.getLevel(db,id,"minus");
        minusView=findViewById(R.id.minusLevel);
        minusView.setText(Integer.toString(minusLevelValue));
        multLevelValue=ProfileController.getLevel(db,id,"multiplication");
        multView=findViewById(R.id.multLevel);
        multView.setText(Integer.toString(multLevelValue));
        divLevelValue=ProfileController.getLevel(db,id,"division");
        divView=findViewById(R.id.divisionLevel);
        divView.setText(Integer.toString(divLevelValue));
    }

    public void updateLevel(View view) {
        newPlusValue=Integer.parseInt(plusView.getText().toString());
        newMinusValue =Integer.parseInt(minusView.getText().toString());
        newMultValue=Integer.parseInt(multView.getText().toString());
        newDivValue=Integer.parseInt(divView.getText().toString());
        MyOpener opener = new MyOpener(this);
        SQLiteDatabase db=opener.getWritableDatabase();
        ProfileController.updateLevel(db,id,newPlusValue,"plus");
        ProfileController.updateLevel(db,id,newMinusValue,"minus");
        ProfileController.updateLevel(db,id,newMultValue,"multiplication");
        ProfileController.updateLevel(db,id,newDivValue,"division");
        Toast.makeText(getApplicationContext(), "The levels have been updated successfully!",Toast.LENGTH_SHORT).show();
    }
}