package com.android.newcrime.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by zwb on 2017/3/5.
 */

public class DatabasesHelper extends SQLiteOpenHelper {
    // 資料庫名稱
    public static final String DATABASE_NAME = "csi_databases.db";
    // 資料庫版本，資料結構改變的時候要更改這個數字，通常是加一
    public static final int VERSION = 1;
    // 資料庫物件，固定的欄位變數
    private static SQLiteDatabase database;

    public DatabasesHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static SQLiteDatabase getDatabase(Context context) {
        if (database == null || !database.isOpen()) {
            database = new DatabasesHelper(context, DATABASE_NAME,
                    null, VERSION).getWritableDatabase();
        }
        return database;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CrimeProvider.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 刪除原有的表格
        dropAll(db);
        // 呼叫onCreate建立新版的表格
        onCreate(db);
    }

    private void dropAll(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS " + CrimeProvider.TABLE_NAME);
    }
}
