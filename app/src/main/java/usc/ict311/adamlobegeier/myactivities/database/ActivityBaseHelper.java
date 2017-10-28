package usc.ict311.adamlobegeier.myactivities.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.UUID;
import usc.ict311.adamlobegeier.myactivities.database.ActivityDbSchema.ActivityTable;
import usc.ict311.adamlobegeier.myactivities.database.ActivityDbSchema.UserTable;

/**
 * Created by Lubeyy on 23/10/17.
 */

public class ActivityBaseHelper extends SQLiteOpenHelper {
    private static  final int VERSION = 1;
    private static final String DATABASE_NAME = "activityBase.db";
    public ActivityBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + ActivityTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                ActivityTable.Cols.UUID + ", " +
                ActivityTable.Cols.TITLE + ", " +
                ActivityTable.Cols.DATE + ", " +
                ActivityTable.Cols.PLACE + ", " +
                ActivityTable.Cols.COMMENTS + ", " +
                ActivityTable.Cols.DURATION + ", " +
                ActivityTable.Cols.TYPE +
                ")"
        );
        db.execSQL("create table " + UserTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                UserTable.Cols.UUID + ", " +
                UserTable.Cols.WHOLENAME + ", " +
                UserTable.Cols.GENDER + ", " +
                UserTable.Cols.EMAIL + ", " +
                UserTable.Cols.COMMENTS + ")"
        );
        db.execSQL("insert into users (uuid) values " +
                "('" + UUID.randomUUID() + "')" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

