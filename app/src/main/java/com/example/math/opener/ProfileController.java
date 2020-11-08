package com.example.math.opener;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class ProfileController {
    private final static String TABLE_PROFILE="profile";
    private final static String COL_user ="user_id";
    private final static String COL_subject="subject";
    private final static String COL_level="level";
    private final static String[] SUBJECTS = {"plus","minus","multiplication","division"};

    public static void createProfileTable(SQLiteDatabase db){
        String sql = " CREATE TABLE IF NOT EXISTS "+ TABLE_PROFILE + " ("+COL_user+" INTEGER not null, " +COL_subject+" TEXT not null, " +COL_level+" INTEGER not null, " +
                "PRIMARY KEY("+COL_user+"," +COL_subject+")," +
                "   FOREIGN KEY ("+ COL_user +") " +
                "      REFERENCES user (_id) " +
                "         ON DELETE CASCADE ) ";
        Log.e("SQL",sql);
        db.execSQL(sql);
    }

    public static void createProfile(SQLiteDatabase db, int userId) {
        for (String subject : SUBJECTS) {
            String sql = "insert into profile (" + COL_user + ", " + COL_subject + "," + COL_level + ") values"
                    + "(" + userId + ",'" + subject + "',1)";
            Log.e("SQL", sql);
            db.execSQL(sql);
        }
    }
    public static int getLevel(SQLiteDatabase db,int userId, String subject){
        String sql = "select level from profile where " +COL_user + " = "+userId+" and "+COL_subject
                + " = '" + subject + "'";
        Log.e("SQL", sql);
        Cursor result = db.rawQuery(sql,null);
        result.moveToFirst();
        int level = result.getInt(0);
        return level;
    }

    public static void updateLevel(SQLiteDatabase db,int userId, int newLevel, String subject){
        String sql = "update "+TABLE_PROFILE +" set "+COL_level +" = "+newLevel+ " where "+ COL_user+" = "+userId +" and "+COL_subject+" = '" + subject + "'";
        Log.e("Update level SQL", sql);
        db.execSQL(sql);
    }
}

