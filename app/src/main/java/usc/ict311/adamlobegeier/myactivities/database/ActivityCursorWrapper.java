package usc.ict311.adamlobegeier.myactivities.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import usc.ict311.adamlobegeier.myactivities.Activity;
import usc.ict311.adamlobegeier.myactivities.database.ActivityDbSchema.ActivityTable;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Lubeyy on 23/10/17.
 */

public class ActivityCursorWrapper extends CursorWrapper {
    public ActivityCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Activity getActivity() {
        String uuidString = getString(getColumnIndex(ActivityTable.Cols.UUID));
        String title = getString(getColumnIndex(ActivityTable.Cols.TITLE));
        long date = getLong(getColumnIndex(ActivityTable.Cols.DATE));
        String place = getString(getColumnIndex(ActivityTable.Cols.PLACE));
        String duration = getString(getColumnIndex(ActivityTable.Cols.DURATION));
        Integer type = getInt(getColumnIndex(ActivityTable.Cols.TYPE));
        String comments = getString(getColumnIndex(ActivityTable.Cols.COMMENTS));

        Activity activity = new Activity(UUID.fromString(uuidString));
        activity.setTitle(title);
        activity.setDate(new Date(date));
        activity.setPlace(place);
        activity.setDuration(duration);
        activity.setType(type);
        activity.setComments(comments);

        return activity;
    }
}
