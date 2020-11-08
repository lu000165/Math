package com.example.math;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.math.data.User;
import com.example.math.opener.MyOpener;
import com.example.math.opener.UserController;

import java.util.ArrayList;

public class parentMenu extends AppCompatActivity {
    private ArrayList names = new ArrayList();
    private ListView myList;
    private TextView name;
    private MyListAdapter aListAdapter;
    public ArrayList <User> students = new ArrayList<>();
    private TextView studentName, studentID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_menu);
        myList = findViewById(R.id.list_view_parent);
        aListAdapter = new MyListAdapter();
        myList.setAdapter(aListAdapter);
        MyOpener opener = new MyOpener(this);
        SQLiteDatabase db=opener.getWritableDatabase();
        students= UserController.getAllStudent(db);


        myList.setOnItemLongClickListener((p,b,pos,id)->{
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("Do you want to delete this?")
                    .setMessage("1.The selected row is "+ pos+"\n" +"2.The database ID is "+id)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface click, int arg) {
                            UserController.deleteUser(db,id);
                            aListAdapter.notifyDataSetChanged();
                            students= UserController.getAllStudent(db);
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface click, int arg) {
                        }
                    })
                    .setView(getLayoutInflater().inflate(R.layout.row_layout,null))
                    .create().show();
            return true;
        });
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),student_detail.class);
                intent.putExtra("ID", students.get(position).getId());
                Log.e("lon", "lon detail"+students.get(position).getId());
                intent.putExtra("name",students.get(position).getName());
                startActivity(intent);
            }
        });
    }


    private class MyListAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return students.size();
        }

        @Override
        public Object getItem(int position) {
            return students.get(position);
        }

        @Override
        public long getItemId(int position) {
            return (students.get(position).getId());
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View newView=convertView;
            LayoutInflater inflater =getLayoutInflater();
            if(newView==null){
                newView=inflater.inflate(R.layout.row_layout,parent,false);
            }
            studentID=newView.findViewById(R.id.studentID);
            studentName=newView.findViewById(R.id.studentName);
            User student = students.get(position);
            Integer id= student.getId();
            String name= student.getName();
            studentID.setText("ID=" +id);
            studentName.setText("Name="+name);
            return newView;
        }
    }

}