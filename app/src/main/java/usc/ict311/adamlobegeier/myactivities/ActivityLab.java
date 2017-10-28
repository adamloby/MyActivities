package usc.ict311.adamlobegeier.myactivities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import usc.ict311.adamlobegeier.myactivities.database.ActivityBaseHelper;
import usc.ict311.adamlobegeier.myactivities.database.ActivityCursorWrapper;
import usc.ict311.adamlobegeier.myactivities.database.ActivityDbSchema;

import static usc.ict311.adamlobegeier.myactivities.database.ActivityDbSchema.*;

/**
 * Created by Lubeyy on 23/10/17.
 */

public class ActivityLab {
    private static ActivityLab sActivityLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;


    public static ActivityLab get(Context context) {
        if (sActivityLab == null) {
            sActivityLab = new ActivityLab(context);
        }
        return sActivityLab;
    }

    private ActivityLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new ActivityBaseHelper(mContext)
                .getWritableDatabase();
    }

    public List<Activity> getActivities() {
        List<Activity> activities = new ArrayList<>();

        ActivityCursorWrapper cursor = queryActivities(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                activities.add(cursor.getActivity());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return activities;
    }

    public void addActivity(Activity a) {
        ContentValues values = getContentValues(a);
        mDatabase.insert(ActivityTable.NAME, null, values);
    }

    public void deleteActivity(UUID id) {
        this.mDatabase.delete("activities", "uuid=?", new String[] { String.valueOf(id) });
    }

    public Activity getActivity(UUID id) {
        ActivityCursorWrapper cursor = queryActivities(
                ActivityTable.Cols.UUID + " = ?",
                new String[] { id.toString() }
        );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getActivity();
        } finally {
            cursor.close();
        }
    }

    public File getPhotoFile(Activity activity) {
        File externalFilesDir = mContext
                .getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        if (externalFilesDir == null) {
            return null;
        }
        return new File(externalFilesDir, activity.getPhotoFilename());
    }

    public void updateActivity(Activity activity) {
        String uuidString = activity.getId().toString();
        ContentValues values = getContentValues(activity);

        mDatabase.update(ActivityTable.NAME, values,
                ActivityTable.Cols.UUID + " = ?",
                new String [] { uuidString});
    }

    private static ContentValues getContentValues(Activity activity) {
        ContentValues values = new ContentValues();
        values.put(ActivityTable.Cols.UUID, activity.getId().toString());
        values.put(ActivityTable.Cols.TITLE, activity.getTitle());
        values.put(ActivityTable.Cols.DATE, activity.getDate().getTime());
        values.put(ActivityTable.Cols.PLACE, activity.getPlace());
        values.put(ActivityTable.Cols.DURATION, activity.getDuration());
        values.put(ActivityTable.Cols.TYPE, activity.getType());
        values.put(ActivityTable.Cols.COMMENTS, activity.getComments());

        return values;
    }

    private ActivityCursorWrapper queryActivities(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                ActivityDbSchema.ActivityTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new ActivityCursorWrapper(cursor);
    }


}

