package pm.anna.ineedahug;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;


public class HugActivity extends AppCompatActivity {
    private TextView mHugTextView;
    private Button mHugButton;
    private RelativeLayout mRelativeLayout;
    private ScrollView mScrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final ColorWheel mColorWheel = new ColorWheel(this);
        final AllHugs mAllHugs = new AllHugs(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hug);

        // assign the Views from the layout file to the corresponding variables
        mHugTextView = (TextView) findViewById(R.id.hugTextView);
        mHugButton = (Button) findViewById(R.id.showHugButton);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.activity_hug);
        mScrollView = (ScrollView) findViewById(R.id.scroll);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hugs = mAllHugs.getHug();
                int color = mColorWheel.getColor();
                mHugTextView.setText(hugs);
                mScrollView.setBackgroundColor(color);
                mRelativeLayout.setBackgroundColor(color);
                mHugButton.setTextColor(color);

            }
        };
        mHugButton.setOnClickListener(listener);
    }
}
