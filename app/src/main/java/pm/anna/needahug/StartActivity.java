package pm.anna.needahug;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StartActivity extends BaseActivity {

    private EditText mNameField;
    private Button mStartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        mNameField = (EditText) findViewById(R.id.nameEditText);
        mStartButton = (Button) findViewById(R.id.startButton);

        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mNameField.getText().toString();
                // Testing if it works
                //  Toast.makeText(StartActivity.this, name, Toast.LENGTH_LONG).show();
                showHugs(name);
            }
        });
    }
    private void showHugs(String name){
        Intent intent = new Intent(this, HugActivity.class);
        intent.putExtra(getString(R.string.key_name), name);

        startActivity(intent);
    }
}
