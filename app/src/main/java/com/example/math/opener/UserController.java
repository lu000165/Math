package com.example.math.opener;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.math.data.User;

import java.util.ArrayList;

public class UserController {
    private final static String TABLE_USER="user";
    private final static String COL_ID ="_id";
    private final static String COL_NAME="name";
    private final static String COL_PASS="password";
    private final static String COL_EMAIL="email";
    private final static String COL_PARENT="isParent";

    public static void createUserTable(SQLiteDatabase db) {
        String sql = " CREATE TABLE IF NOT EXISTS "+ TABLE_USER + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "+COL_NAME+" TEXT not null, " +COL_PASS+" TEXT not null, " +COL_EMAIL+" TEXT not null, " + COL_PARENT + " boolean not null) ";
        Log.e("SQL",sql);
        db.execSQL(sql);
    }

    public static void addUser(SQLiteDatabase db, String nameString, String passString, String emailString, Boolean isParent) {

        int parent = 0;
        if (isParent){
            parent = 1;
        }
        String sql = "insert into " + TABLE_USER + "(" + COL_NAME + "," +COL_PASS+"," + COL_EMAIL + "," + COL_PARENT + ")";
        sql = sql + "Values ('"+ nameString +"','" + passString + "','" + emailString + "'," + parent + ")";

        Log.e("SQL",sql);

        db.execSQL(sql);
    }

    public static User getUser(SQLiteDatabase db, String nameString, String passwordString) {
        String sql = "select * from " + TABLE_USER + " where "+ COL_NAME + " = '" + nameString + "' and " +  COL_PASS + " = '" + passwordString +"'";
        Log.e("SQL",sql);
        User user = null;
        Cursor results = db.rawQuery(sql,null);
        if (results.getCount()>0){
            results.moveToNext();
            int useridIndex = results.getColumnIndex(COL_ID);
            int nameIndex = results.getColumnIndex(COL_NAME);
            int isParentIndex = results.getColumnIndex(COL_PARENT);
            Log.e("SQL","isParent Index" +isParentIndex );
            int emailIndex = results.getColumnIndex(COL_EMAIL);

            int userId = results.getInt(useridIndex);
            String name = results.getString(nameIndex);

            Log.e("SQL","isParent result" +results.getInt(isParentIndex));
            Boolean isParent = results.getInt(isParentIndex)>0;
            String email = results.getString(emailIndex);

            user = new User(userId,name,email,isParent);

        }
        return user;
    }
    public static User getUserbyID(SQLiteDatabase db, int id){
        String sql = "select * from " + TABLE_USER + " where "+  COL_ID + " = " + id ;
        User user = null;
        Cursor results = db.rawQuery(sql,null);
        if (results.getCount()>0){
            results.moveToNext();
            int useridIndex = results.getColumnIndex(COL_ID);
            int nameIndex = results.getColumnIndex(COL_NAME);
            int isParentIndex = results.getColumnIndex(COL_PARENT);
            Log.e("SQL","isParent Index" +isParentIndex );
            int emailIndex = results.getColumnIndex(COL_EMAIL);

            int userId = results.getInt(useridIndex);
            String name = results.getString(nameIndex);

            Log.e("SQL","isParent result" +results.getInt(isParentIndex));
            Boolean isParent = results.getInt(isParentIndex)>0;
            String email = results.getString(emailIndex);

            user = new User(userId,name,email,isParent);

        }
        return user;
    }
    public static ArrayList<User> getAllStudent(SQLiteDatabase db){
        ArrayList<User> userList=new ArrayList<>();
        String sql = "select * from "+ TABLE_USER+" WHERE " + COL_PARENT +"= 0";
        Cursor results = db.rawQuery(sql,null);
        while(results.moveToNext()) {
            int useridIndex = results.getColumnIndex(COL_ID);
            int nameIndex = results.getColumnIndex(COL_NAME);
            int isParentIndex = results.getColumnIndex(COL_PARENT);
            Log.e("SQL","isParent Index" +isParentIndex );
            int emailIndex = results.getColumnIndex(COL_EMAIL);

            int userId = results.getInt(useridIndex);
            String name = results.getString(nameIndex);

            Log.e("SQL","isParent result" +results.getInt(isParentIndex));
            Boolean isParent = results.getInt(isParentIndex)>0;
            String email = results.getString(emailIndex);

            User user = new User(userId,name,email,isParent);
            userList.add(user);
        }
        return userList;
    }

    public static void deleteUser(SQLiteDatabase db,long id){
        String sql = "DELETE FROM " + TABLE_USER +" WHERE "+ COL_ID+"="+id;
        db.execSQL(sql);
    }




}
