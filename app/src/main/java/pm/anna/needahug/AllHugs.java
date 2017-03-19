package pm.anna.needahug;

import android.content.Context;
import android.content.ContextWrapper;
import android.database.Cursor;

import java.util.Random;

import pm.anna.needahug.data.HugsContract;

public class AllHugs extends ContextWrapper  {

    public AllHugs (Context mContext) {
        super(mContext);
    }

    public String getHug(){
        Random randomGenerator = new Random();
        // Get hugs from the database
        Cursor c = getContentResolver().query(HugsContract.HugsEntry.CONTENT_URI, null, null, null, null);
        assert c != null;
        // Check how many hugs are there
        int hugsAvailable = c.getCount();
        // Randomly select a hug
        int randomNumber = randomGenerator.nextInt(hugsAvailable);
        c.moveToPosition(randomNumber);
        // Get hug!
        String hug = c.getString(c.getColumnIndex("hug"));
        return hug;
    }
}
