package com.corona.coronaapp.system;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    final static String DB_NAME = "Corona.db";
    final static int DB_VERSION = 2;

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String qry1 = "CREATE TABLE Destination ( Destination_ID INTEGER NOT NULL, Destination_name CHAR(500) NOT NULL PRIMARY KEY, Local_ID INTEGER NOT NULL)";
        String qry2 = "CREATE TABLE Current_Info(Local_ID INTEGER NOT NULL PRIMARY KEY, Grade CHAR(500) NOT NULL)";
        String qry3 = "CREATE TABLE Guideline(Grade CHAR(10) NOT NULL PRIMARY KEY, Guideline CHAR(1000)";
        String qry4 = "CREATE TABLE News(News_ID INTEGER NOT NULL PRIMARY KEY, News_Info CHAR(500) NOT NULL, News_URL CHAR(500) NOT NULL)";
        String qry5 = "CREATE TABLE Setting(Setting_Location INTEGER NOT NULL, Local_ID INTEGER NOT NULL, Setting_ID INTEGER NOT NULL PRIMARY KEY)";

        /////
        sqLiteDatabase.execSQL(qry1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS list");
        onCreate(sqLiteDatabase);
    }
}