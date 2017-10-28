package usc.ict311.adamlobegeier.myactivities.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import usc.ict311.adamlobegeier.myactivities.user.User;
import usc.ict311.adamlobegeier.myactivities.database.ActivityDbSchema.UserTable;

import java.util.UUID;

/**
 * Created by Lubeyy on 23/10/17.
 */

public class UserCursorWrapper extends CursorWrapper {
    public UserCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public User getUser() {
        String uuidString = getString(getColumnIndex(UserTable.Cols.UUID));
        Integer gender = getInt(getColumnIndex(UserTable.Cols.GENDER));
        String wholename = getString(getColumnIndex(UserTable.Cols.WHOLENAME));
        String email = getString(getColumnIndex(UserTable.Cols.EMAIL));
        String comments = getString(getColumnIndex(UserTable.Cols.COMMENTS));

        User user = new User(UUID.fromString(uuidString));
        user.setGender(gender);
        user.setWholeName(wholename);
        user.setEmail(email);
        user.setComments(comments);

        return user;
    }
}

