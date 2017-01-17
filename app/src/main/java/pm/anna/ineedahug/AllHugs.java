package pm.anna.ineedahug;

import java.util.Random;

/**
 * Created by Anna on 16.01.2017.
 */

public class AllHugs {
    // Fields - properties
    private String[] mHugs = {
            "Yes you can. Don't ya ever don't ya ever give in ♡",
            "Always always always make any self-judgement in daylight. \nWe are all a little worse for wear at night-time.",
            "Hold on!!! You can get through this",
            "My most loving, caring, squeezing, encouraging hugs to you my dear, you can do it ♡",
            "Sending huge, enormous, irritatingly large hugs!",
            "You're not a failure you're just going through a harsh time. Take care.xoxo",
            "You win some, you lose some (: the only time you'll always fail is when you never try.\n" + "Gads I sound like a Chinese fortune cookie ((: but, point stands.",
            "Remember to be compassionate to everyone, including yourself ♡",
            "hold on,\nyou've come so far!",
            "you aren't stupid. 'Failure isn't fatal. Success isn't forever. What matters is the strength to get back up again' :)"
    };
    // Methods - actions
public String getHug(){
    String hug = "";
    // Randomly select a text
    Random randomGenerator = new Random();
    int randomNumber = randomGenerator.nextInt(mHugs.length);
    hug = mHugs[randomNumber];
    return hug;
};
}
