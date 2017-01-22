package pm.anna.needahug;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.content.Intent;

public class HugActivity extends BaseActivity {
    private TextView mHugTextView;
    private Button mHugButton;
    private RelativeLayout mRelativeLayout;
    private ScrollView mScrollView;
    private TextView mHiText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final ColorWheel mColorWheel = new ColorWheel(this);
        final AllHugs mAllHugs = new AllHugs(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hug);

        Intent intent = getIntent();
        String name = intent.getStringExtra(getString(R.string.key_name));

        mHiText = (TextView) findViewById(R.id.hi);
        mHiText.setText("Hi " + name + "!");

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