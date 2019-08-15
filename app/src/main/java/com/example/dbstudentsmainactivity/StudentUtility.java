package com.example.dbstudentsmainactivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class StudentUtility {

    public static final String DB_NAME = "students.db";
    public static final String TABLE_NAME = "students";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_AGE = "age";
    public static final int DB_VERSION = 1;
    private static final String TABLE_CREATE_QUERY =
            "create table " + TABLE_NAME + "(" + COLUMN_ID + " integer primary key autoincrement," + COLUMN_NAME + " text," + COLUMN_AGE + " text);";
    private StudentDatabaseUtility databaseUtility;
    private SQLiteDatabase sqLiteDatabase;
    private Context mContext;

    public StudentUtility(Context context) {
        mContext = context;
        databaseUtility = new StudentDatabaseUtility(context);
    }

    public void open() {
        sqLiteDatabase = databaseUtility
                .getWritableDatabase();
    }

    public void close() {
        sqLiteDatabase.close();
    }

    public void addStudent(Student student) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, student.name);
        values.put(COLUMN_AGE, student.age);
        long id = sqLiteDatabase.
                insert(TABLE_NAME, null, values);
        if (-1 != id) {
            Toast.makeText(mContext
                    , "Insert Success " + id, Toast.LENGTH_SHORT).
                    show();
        }
    }

    public void listStudents() {
        Cursor cursor = sqLiteDatabase.
                query
                        (TABLE_NAME, null, COLUMN_NAME + "=? AND " + COLUMN_AGE + "=?",
                                new String[]{"Edureka", "10"}, null, null, null);


        if (cursor != null && cursor.moveToFirst()) {
            do {
                Log.d("kkdb","inside listStudents");
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                String age = cursor.getString(cursor.getColumnIndex(COLUMN_AGE));
                Toast.makeText(mContext, name + " " + age, Toast.LENGTH_LONG).show();

            } while (cursor.moveToNext());
        }

        if (null != cursor) {
            cursor.close();
        }
    }

    public void updateStudent(Student student) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_AGE, student.age);
        int numRowsUpdated = sqLiteDatabase.update
                (TABLE_NAME, values, COLUMN_NAME + "=?", new String[]{student.name});

        Toast.makeText(mContext, "Rows Updated " + numRowsUpdated,
                Toast.LENGTH_SHORT).show();
    }

    public void deleteStudent(Student student) {
        int numRowsDeleted = sqLiteDatabase.delete
                (TABLE_NAME, COLUMN_NAME + "=?", new String[]{student.name});

        Toast.makeText(mContext, "Rows Deleted " + numRowsDeleted,
                Toast.LENGTH_SHORT).show();
    }

    private class StudentDatabaseUtility extends SQLiteOpenHelper {

        public StudentDatabaseUtility(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(TABLE_CREATE_QUERY);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
