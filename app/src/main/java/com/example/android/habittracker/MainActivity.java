package com.example.android.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.android.habittracker.Data.HabitContract;
import com.example.android.habittracker.Data.HabitContract.HabitEntry;
import com.example.android.habittracker.Data.HabitDbHelper;


public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newHabit("Habit00", "45", 1);
        newHabit("Habit01", "15", 1);
        newHabit("Habit02", "0", 0);

        Cursor cursor = read();
        try {
            int idColumnIndex = cursor.getColumnIndex(HabitContract.HabitEntry._ID);
            int timeColumnIndex = cursor.getColumnIndex(HabitContract.HabitEntry.COLUMN_HABIT_TIME);
            int statusColumnIndex = cursor.getColumnIndex(HabitContract.HabitEntry.COLUMN_HABIT_STATUS);
            int nameColumnIndex = cursor.getColumnIndex(HabitContract.HabitEntry.COLUMN_HABIT_NAME);
            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                int currentTime = cursor.getInt(timeColumnIndex);
                int currentStatus = cursor.getInt(statusColumnIndex);

                Log.v(LOG_TAG, "id:" + currentID +
                        " name:" + currentName +
                        " time:" + currentTime +
                        " status:" + currentStatus);
            }

        } finally {
            cursor.close();
        }

    }

    public void newHabit(String name, String timeSpent, Integer status) {
        Integer time = Integer.parseInt(timeSpent);

        HabitDbHelper mDbHelper = new HabitDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(HabitEntry.COLUMN_HABIT_NAME, name);
        values.put(HabitEntry.COLUMN_HABIT_TIME, time);
        values.put(HabitEntry.COLUMN_HABIT_STATUS, status);

        long newRowId = db.insert(HabitEntry.TABLE_NAME, null, values);
    }

    public Cursor read() {
        HabitDbHelper habitDbHelper = new HabitDbHelper(this);
        SQLiteDatabase db = habitDbHelper.getReadableDatabase();

        String[] projection = {
                HabitEntry._ID,
                HabitEntry.COLUMN_HABIT_NAME,
                HabitEntry.COLUMN_HABIT_TIME,
                HabitEntry.COLUMN_HABIT_STATUS};

        return db.query(
                HabitEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);
    }
}