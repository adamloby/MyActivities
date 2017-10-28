package usc.ict311.adamlobegeier.myactivities.database;

/**
 * Created by Lubeyy on 23/10/17.
 */

public class ActivityDbSchema {
    public static final class ActivityTable {
        public static final String NAME = "activities";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DATE = "date";
            public static final String PLACE = "place";
            public static final String DURATION = "duration";
            public static final String TYPE = "type";
            public static final String COMMENTS = "comments";

        }
    }

    public static final class UserTable {
        public static final String NAME = "users";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String GENDER = "gender";
            public static final String WHOLENAME = "wholename";
            public static final String EMAIL = "email";
            public static final String COMMENTS = "comments";
        }
    }
}

