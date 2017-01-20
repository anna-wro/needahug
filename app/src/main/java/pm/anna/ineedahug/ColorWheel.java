package pm.anna.ineedahug;

import android.graphics.Color;

import java.util.Random;
import android.content.Context;
import android.content.ContextWrapper;

public class ColorWheel extends ContextWrapper {

    private String[] mColors;
    public ColorWheel (Context mContext) {
        super(mContext);
        mColors = getResources().getStringArray(R.array.colors);
    }

    public int getColor(){
        String color;
        // Randomly select a text
        Random randomGenerator = new Random();
        int randomNumber = randomGenerator.nextInt(mColors.length);
        color = mColors[randomNumber];
        return Color.parseColor(color);
    };
}
