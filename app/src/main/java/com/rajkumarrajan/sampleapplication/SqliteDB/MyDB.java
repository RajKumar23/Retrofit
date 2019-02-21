package com.rajkumarrajan.sampleapplication.SqliteDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.rajkumarrajan.sampleapplication.Pojo.MyPojo;

import java.util.List;

public class MyDB extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MyLocalDB";

    public static final String TABLE_JsonPlaceHolder= "JsonPlaceHolder";
    private static final String LOG = "DB";

    public static final String ID = "ID";
    public static final String userId ="userId";
    public static final String id ="id";
    public static final String title ="title";

    public MyDB(Context context) {

        super(context, DATABASE_NAME, null, 1);
        String Path = context.getDatabasePath(DATABASE_NAME).getPath();
        Log.i("DataBasePath",Path);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_JsonPlaceHolder +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT,"+userId+" TEXT,"+id+" TEXT,"+title+" TEXT)");
        Log.d(LOG, " Add Income table created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_JsonPlaceHolder);
    }

    public boolean InsertDataToJsonPlaceHolderTable(List<MyPojo> myPojos) {
        boolean result = false;
        int i = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        while ( i < myPojos.size())
        {
            contentValues.put(userId, myPojos.get(i).getUserId());
            contentValues.put(id, myPojos.get(i).getId());
            contentValues.put(title, myPojos.get(i).getTitle());
            i++;
            result =  db.insert(TABLE_JsonPlaceHolder,null ,contentValues)>0;
        }
        return  result;
    }
}
