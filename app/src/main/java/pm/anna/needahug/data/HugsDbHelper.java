package pm.anna.needahug.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import pm.anna.needahug.data.HugsContract.HugsEntry;
/**
 * Created by Anna on 25.02.2017.
 */

public class HugsDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = HugsDbHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "hugs.db";
    private static final int DATABASE_VERSION = 1;

    public HugsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_HUGS_TABLE = "CREATE TABLE " + HugsEntry.TABLE_NAME + " ("
                + HugsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HugsEntry.COLUMN_HUG + " TEXT NOT NULL);";
        db.execSQL(SQL_CREATE_HUGS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
