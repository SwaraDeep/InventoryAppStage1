package com.example.unknown.inventoryappstage1.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.unknown.inventoryappstage1.data.ItemEntries.*;

public class ItemDbHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "shelter.db";
    private static final int DATABASE_VERSION = 1;

    public ItemDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_CREATE_ITEMS_TABLE =  "CREATE TABLE " + ItemEntry.TABLE_NAME + " ("
                + ItemEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ItemEntry.COLUMN_ITEM_NAME + " TEXT NOT NULL, "
                + ItemEntry.COLUMN_ITEM_PRICE + " INTEGER NOT NULL, "
                + ItemEntry.COLUMN_ITEM_QUANTITY + " INTEGER NOT NULL, "
                + ItemEntry.COLUMN_ITEM_SUPPLIER_NAME + " TEXT NOT NULL, "
                + ItemEntry.COLUMN_ITEM_MOBILE + " TEXT NOT NULL" + ");";

        Log.v("SQL_CREATE:", SQL_CREATE_ITEMS_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_ITEMS_TABLE);
    }
}
