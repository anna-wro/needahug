package pm.anna.needahug.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Anna on 25.02.2017.
 */

public final class HugsContract {

    public static final String CONTENT_AUTHORITY = "pm.anna.needahug";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_HUGS = "hugs";

    private HugsContract() {
    }

    /* Inner class that defines the table contents */
    public static class HugsEntry implements BaseColumns {
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_HUGS);
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_HUGS;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_HUGS;

        public static final String TABLE_NAME = "hugs";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_HUG = "hug";
        public static final String COLUMN_FAV = "favourite";
    }
}
