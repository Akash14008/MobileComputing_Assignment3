package com.example.karan.assignment_3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

public class SQLStuff {                                                             //  Creating Schema and designing table structure

    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "StudentTable";
        public static final String COLUMN_Name = "Name";
        public static final String COLUMN_RollNumber = "RollNumber";
        public static final String COLUMN_Batch = "Batch";
        public static final String COLUMN_Address = "Address";
        public static final String COLUMN_Mobile = "Mobile";
        public static final String COLUMN_CGPA = "CGPA";
    }

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" +
                    FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedEntry.COLUMN_Name + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.COLUMN_RollNumber + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.COLUMN_Batch + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.COLUMN_Address + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.COLUMN_Mobile + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.COLUMN_CGPA + TEXT_TYPE + " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;

    public class SQLStuffDbHelper extends SQLiteOpenHelper {                    // creating database using SQLiteOpenHelper

        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "SQLStuff.db";

        public SQLStuffDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_ENTRIES);
        }
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);
        }
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }
    }

    private final Context myContext;
    private SQLiteDatabase myDatabase;
    private SQLStuffDbHelper myDbHelper;

    public SQLStuff(Context c) {
        myContext = c;
    }

    public long insert(String name, String rollNumber, String batch, String address, String mobileNo, String cgpa) {
        myDbHelper = new SQLStuffDbHelper(myContext);                           // insert database function
        myDatabase = myDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FeedEntry.COLUMN_Name, name);
        values.put(FeedEntry.COLUMN_RollNumber, rollNumber);
        values.put(FeedEntry.COLUMN_Batch, batch);
        values.put(FeedEntry.COLUMN_Address, address);
        values.put(FeedEntry.COLUMN_Mobile, mobileNo);
        values.put(FeedEntry.COLUMN_CGPA, cgpa);
        return myDatabase.insert(FeedEntry.TABLE_NAME, null, values);
    }

    public int update(String id, String name, String rollNumber, String batch, String address, String mobileNo, String cgpa) {
        myDbHelper = new SQLStuffDbHelper(myContext);                           // update database function
        myDatabase = myDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FeedEntry.COLUMN_Name, name);
        values.put(FeedEntry.COLUMN_RollNumber, rollNumber);
        values.put(FeedEntry.COLUMN_Batch, batch);
        values.put(FeedEntry.COLUMN_Address, address);
        values.put(FeedEntry.COLUMN_Mobile, mobileNo);
        values.put(FeedEntry.COLUMN_CGPA, cgpa);
        return myDatabase.update(FeedEntry.TABLE_NAME, values, FeedEntry._ID + " =? ", new String[]{id});
    }

    public Cursor read(String name, String rollNumber, String batch) {          // read database function
        myDbHelper = new SQLStuffDbHelper(myContext);
        myDatabase = myDbHelper.getReadableDatabase();
        Cursor res = myDatabase.rawQuery("select * from " + FeedEntry.TABLE_NAME + " where " + FeedEntry.COLUMN_Name + " = '" + name + "' and " + FeedEntry.COLUMN_RollNumber + " = '" + rollNumber + "' and " + FeedEntry.COLUMN_Batch + " = '" + batch + "'", null);
        return res;
    }

    public int delete(String id) {                                                  // delete database row function
        myDbHelper = new SQLStuffDbHelper(myContext);
        myDatabase = myDbHelper.getWritableDatabase();
        return myDatabase.delete(FeedEntry.TABLE_NAME, FeedEntry._ID + " =? ", new String[]{id});
    }

    public void close() {
        myDbHelper.close();
    }

}
