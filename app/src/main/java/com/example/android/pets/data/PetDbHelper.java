package com.example.android.pets.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.pets.data.PetsContract.petsEntry;
public class PetDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="Shelter.db";

    public PetDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    String SQL_Create_PETS_TABLE=    "CREATE TABLE "+ petsEntry.TABLE_NAME +"("+petsEntry.COLUMN_Id + " INTEGER PRIMARY KEY AUTOINCREMENT,"+petsEntry.COLUMN_NAME+" TEXT NOT NULL,"+petsEntry.COLUMN_BREED+" TEXT," +petsEntry
        .COLMUN_GENDER+" INTEGER NOT NULL,"+petsEntry.COLMUN_WEIGHT+" INTEGER NOT NULL DEFAULT 0);";
    db.execSQL(SQL_Create_PETS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
