package pm.anna.needahug;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.SimpleCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.Util;

import java.util.Random;

import pm.anna.needahug.data.HugsContract;
import pm.anna.needahug.data.HugsContract.HugsEntry;

public class HugActivity extends BaseActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int HUGS_LOADER = 0;
    CursorAdapter mCursorAdapter;
    private TextView mHugTextView;
    private Button mHugButton;
    private RelativeLayout mRelativeLayout;
    private ScrollView mScrollView;
    private TextView mHiText;
    private Toolbar mToolbar;
    String mNewMessages[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hug);

        initUiElements();
        initPersonName();
        initBoomMenu();
        initToolbar();
        addListeners();
    }

    private void SHARE(){
        String shareHug = mHugTextView.getText().toString();
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareHug);
        startActivity(Intent.createChooser(sharingIntent,  getResources().getString(R.string.share)));
    }

    private void initUiElements(){
        mHiText = (TextView) findViewById(R.id.hi);
        mHugTextView = (TextView) findViewById(R.id.hugTextView);
        mNewMessages = getResources().getStringArray(R.array.newHug);
        mHugButton = (Button) findViewById(R.id.showHugButton);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.activity_hug);
        mScrollView = (ScrollView) findViewById(R.id.scroll);
        mToolbar = (Toolbar) findViewById(R.id.my_toolbar);
    }

    private void initPersonName(){
        Intent intent = getIntent();
        String name = intent.getStringExtra(getString(R.string.key_name));
        if (name.isEmpty()) {
            name = "Anna";
        }
        String hiText = "Hi, " + name + "!";
        mHiText.setText(hiText);
    }

    private void initToolbar(){
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
    }

    private void initBoomMenu(){
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
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {

                        Intent intent = new Intent(HugActivity.this, EditActivity.class);
                        startActivity(intent);
                    }
                }));
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

    }

    private void addListeners(){
        final ColorWheel mColorWheel = new ColorWheel(HugActivity.this);
        final AllHugs mAllHugs = new AllHugs(HugActivity.this);

        mHugButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hugs = mAllHugs.getHug();
                int color = mColorWheel.getColor();
                mHugTextView.setText(hugs);
                mScrollView.setBackgroundColor(color);
                mRelativeLayout.setBackgroundColor(color);
                mHugButton.setTextColor(color);
            }
        });
    }

    public void insertHug() {
        String hug = "";
        ContentValues values = new ContentValues();
        values.put(HugsContract.HugsEntry.COLUMN_HUG, hug);

        Uri newUri = getContentResolver().insert(HugsEntry.CONTENT_URI, values);
        if (newUri == null) {
            Toast.makeText(this, getString(R.string.save_error), Toast.LENGTH_SHORT).show();
        } else {
            Random generator = new Random();
            int num = generator.nextInt(mNewMessages.length);
            Toast.makeText(this, mNewMessages[num], Toast.LENGTH_SHORT).show();
        }
    }
    private void deleteHug() {
        Cursor c = getContentResolver().query(HugsEntry.CONTENT_URI, null, null, null, null);
        c.moveToLast();
        String dayToDelete = c.getString(c.getColumnIndex(HugsEntry._ID));
        getContentResolver().delete(HugsEntry.CONTENT_URI, HugsEntry._ID + "=?", new String[]{dayToDelete});
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] projection = {
                HugsEntry._ID,
                HugsEntry.COLUMN_HUG,
                HugsEntry.COLUMN_FAV,
        };

        return new CursorLoader(this, HugsEntry.CONTENT_URI, projection, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mCursorAdapter.swapCursor(null);
    }

}