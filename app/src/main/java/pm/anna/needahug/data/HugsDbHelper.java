package pm.anna.needahug.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import pm.anna.needahug.R;
import pm.anna.needahug.data.HugsContract.HugsEntry;

/**
 * Created by Anna on 25.02.2017.
 */

public class HugsDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = HugsDbHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "hugs.db";
    private static final int DATABASE_VERSION = 3;
    private final String[] mHugs;
    private static final String INSERT_HUGS = "INSERT INTO hugs (hug) VALUES (?)";

    public HugsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mHugs = context.getResources().getStringArray(R.array.hugs);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String SQL_CREATE_HUGS_TABLE = "CREATE TABLE " + HugsEntry.TABLE_NAME + " ("
                + HugsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HugsEntry.COLUMN_HUG + " TEXT NOT NULL"
                + HugsEntry.COLUMN_FAV + "INTEGER DEFAULT 0);";
        db.execSQL(SQL_CREATE_HUGS_TABLE);

        SQLiteStatement statement = db.compileStatement(INSERT_HUGS);
        for (String hugs : mHugs) {
            statement.bindString(1, hugs);
            statement.executeInsert();
        }
        statement.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
