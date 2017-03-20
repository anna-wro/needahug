package pm.anna.needahug;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import com.nightonke.boommenu.BoomButtons.SimpleCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.Util;

public class EditActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


        BoomMenuButton bmb = (BoomMenuButton) findViewById(R.id.boom_edit);
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
               );
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
        ImageButton backButton = (ImageButton) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}

