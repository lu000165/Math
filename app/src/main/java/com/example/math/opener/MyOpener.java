package com.example.math.opener;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyOpener extends SQLiteOpenHelper {
    protected final static String DATABASE_NAME ="MyDB";
    protected final static int VERSION_NUM =2;


    public MyOpener(Context ctx){ super(ctx,DATABASE_NAME,null,VERSION_NUM);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        UserController.createUserTable(db);
        ProfileController.createProfileTable(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        UserController.createUserTable(db);
        ProfileController.createProfileTable(db);
    }
}
