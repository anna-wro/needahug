package pm.anna.ineedahug;
import android.content.Context;
import android.content.ContextWrapper;

import java.util.Random;

public class AllHugs extends ContextWrapper  {

    public String[] mHugs;
    public AllHugs (Context mContext) {
        super(mContext);
        mHugs = getResources().getStringArray(R.array.hugs);
    }

    public String getHug(){
        // Randomly select a text
        Random randomGenerator = new Random();
        int randomNumber = randomGenerator.nextInt(mHugs.length);
        return mHugs[randomNumber];
    }
}
