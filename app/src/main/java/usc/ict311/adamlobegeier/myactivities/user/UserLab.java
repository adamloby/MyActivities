package usc.ict311.adamlobegeier.myactivities.user;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.UUID;

import usc.ict311.adamlobegeier.myactivities.database.ActivityBaseHelper;
import usc.ict311.adamlobegeier.myactivities.database.ActivityDbSchema.UserTable;
import usc.ict311.adamlobegeier.myactivities.database.UserCursorWrapper;

/**
 * Created by Lubeyy on 23/10/17.
 */

public class UserLab {
    private static UserLab sUserLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static UserLab get(Context context) {
        if (sUserLab == null) {
            sUserLab = new UserLab(context);
        }
        return sUserLab;
    }

    private UserLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new ActivityBaseHelper(mContext).getWritableDatabase();
    }

    public User getUser() {
        UserCursorWrapper cursor = queryUsers();

        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getUser();
        } finally {
            cursor.close();
        }
    }

    public void updateUser(User user, UUID id) {
        String uuidString = user.getId().toString();
        ContentValues values = getContentValues(user);

        mDatabase.update(UserTable.NAME, values,
                UserTable.Cols.UUID + " = ?",
                new String [] {id.toString()});
    }


    private static ContentValues getContentValues(User user) {
        ContentValues values = new ContentValues();
        values.put(UserTable.Cols.UUID, user.getId().toString());
        values.put(UserTable.Cols.WHOLENAME, user.getWholeName());
        values.put(UserTable.Cols.GENDER, user.getGender());
        values.put(UserTable.Cols.EMAIL, user.getEmail());
        values.put(UserTable.Cols.COMMENTS, user.getComments());

        return values;
    }

    private UserCursorWrapper queryUsers() {
        Cursor cursor = mDatabase.query(
                UserTable.NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        return new UserCursorWrapper(cursor);
    }
}
