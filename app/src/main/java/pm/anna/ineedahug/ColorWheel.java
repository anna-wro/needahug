package pm.anna.ineedahug;

import android.graphics.Color;

import java.util.Random;

/**
 * Created by Anna on 16.01.2017.
 */

public class ColorWheel {
    // Fields - properties
    private String[] mColors = {
            "#62BFAD",  // green
            "#D69ECD", // pink
            "#C66FB9", // violet
            "#9357A9", // dark violet
            "#189BA3", // green
            "#1C95DB", // blue
            "#384D9D", // navy blue
            "#FF6B61", // orange
            "#EEB200", // yellow
            "#D3586D", // pink
            "#9C9CDD", // subtle violet
            "#6676CC", // favourite blue
            "#F4985A", // orange
            "#8DD0F4", // blue
            "#6BCEB4"  // mint
    };
    // Methods - actions
    public int getColor(){
        String color;
        // Randomly select a text
        Random randomGenerator = new Random();
        int randomNumber = randomGenerator.nextInt(mColors.length);
        color = mColors[randomNumber];
        int colorAsInt = Color.parseColor(color);
        return colorAsInt;
    };
}
