package com.example.android.habittracker.Data;

import android.provider.BaseColumns;

/**
 * Created by jasmbo on 10/27/17.
 */

public class HabitContract {


    private HabitContract(){}

    public static final class HabitEntry implements BaseColumns{

        public final static String TABLE_NAME = "habits";
        public final static String _ID = BaseColumns._ID;
        public static final String COLUMN_HABIT_NAME = "name";
        public static final String COLUMN_HABIT_TIME = "time_spent";
        public static final String COLUMN_HABIT_STATUS = "status";


    }
}
