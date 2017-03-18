package pm.anna.needahug;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.SimpleCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.Util;

public class HugActivity extends BaseActivity {
    private TextView mHugTextView;
    private Button mHugButton;
    private RelativeLayout mRelativeLayout;
    private ScrollView mScrollView;
    private TextView mHiText;
    private Toolbar mToolbar;
    private ShareActionProvider mShareActionProvider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final ColorWheel mColorWheel = new ColorWheel(this);
        final AllHugs mAllHugs = new AllHugs(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hug);

        // Getting data from an Intent
        Intent intent = getIntent();
        String name = intent.getStringExtra(getString(R.string.key_name));
        if (name.isEmpty()) {
            name = "Anna";
        }
        String hiText = "Hi, " + name + "!";
        mHiText = (TextView) findViewById(R.id.hi);
        mHiText.setText(hiText);
        // assign the Views from the layout file to the corresponding variables
        mHugTextView = (TextView) findViewById(R.id.hugTextView);

        mHugButton = (Button) findViewById(R.id.showHugButton);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.activity_hug);
        mScrollView = (ScrollView) findViewById(R.id.scroll);
        BoomMenuButton bmb = (BoomMenuButton) findViewById(R.id.bmb);
        bmb.addBuilder(new SimpleCircleButton.Builder()
                .normalImageRes(R.drawable.ic_share_white_48dp)
                .imageRect(new Rect(8, 12, 42,42))
                .shadowEffect(false)
                .rippleEffect(true)
                .normalColorRes(R.color.dot_transparent)
                .highlightedColorRes(R.color.dot_click)
                .unableColorRes(R.color.dot_transparent)
                .buttonRadius(Util.dp2px(18))
                .pieceColorRes(R.color.semi_transparent)
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        SHARE();
                    }
                })
        );
        bmb.addBuilder(new SimpleCircleButton.Builder().normalImageRes(R.drawable.ic_add_white_48dp)
                .imageRect(new Rect(6, 6, 48,48))
                .shadowEffect(false)
                .rippleEffect(true)
                .normalColorRes(R.color.dot_transparent)
                .unableColorRes(R.color.dot_transparent)
                .highlightedColorRes(R.color.dot_click)
                .buttonRadius(Util.dp2px(18))
                .pieceColorRes(R.color.semi_transparent)
                .unable(true));
        bmb.addBuilder(new SimpleCircleButton.Builder().normalImageRes(R.drawable.ic_filter_vintage_white_48dp)
                .imageRect(new Rect(12, 12, 42,42))
                .shadowEffect(false)
                .rippleEffect(true)
                .normalColorRes(R.color.dot_transparent)
                .unableColorRes(R.color.dot_transparent)
                .highlightedColorRes(R.color.dot_click)
                .buttonRadius(Util.dp2px(18))
                .pieceColorRes(R.color.semi_transparent)
                .unable(true));


        mToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
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

    private void SHARE(){
        String shareHug = mHugTextView.getText().toString();
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareHug);
        startActivity(Intent.createChooser(sharingIntent,  getResources().getString(R.string.share)));
    }

}