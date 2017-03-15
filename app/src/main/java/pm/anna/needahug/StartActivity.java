package pm.anna.needahug;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class StartActivity extends BaseActivity {
    private static final String PREFS_FILE = "pm.anna.needahug.preferences";
    private static final String KEY_EDITTEXT = "key_edittext";

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private EditText mEditText;
    private EditText mNameField;
    private Button mStartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        mEditText = (EditText)findViewById(R.id.nameEditText);
        mSharedPreferences = getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        Boolean skipWelcome = mSharedPreferences.getBoolean("SKIP_WELCOME", false);

        if (skipWelcome) {
            String name = mSharedPreferences.getString(KEY_EDITTEXT, "");
            Intent intent = new Intent(this, HugActivity.class);
            intent.putExtra(getString(R.string.key_name), name);
            startActivity(intent);
        }

        String editTextString = mSharedPreferences.getString(KEY_EDITTEXT, "");
        mEditText.setText(editTextString);


        mNameField = (EditText) findViewById(R.id.nameEditText);
        mNameField.setFilters(new InputFilter[] {
                new InputFilter() {
                    @Override
                    public CharSequence filter(CharSequence cs, int start,
                                               int end, Spanned spanned, int dStart, int dEnd) {
                        if(cs.equals("")){ // for backspace
                            return cs;
                        }
                        if(cs.toString().matches("[a-zA-Z ]+")){
                            return cs;
                        }
                        return "";
                    }
                }
        });

        mStartButton = (Button) findViewById(R.id.startButton);

        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mNameField.getText().toString();
                name = name.replaceAll("\\s+$", "");
                showHugs(name);
            }
        });
    }
    private void showHugs(String name){
        Intent intent = new Intent(this, HugActivity.class);
        intent.putExtra(getString(R.string.key_name), name);

        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mEditor.putString(KEY_EDITTEXT, mEditText.getText().toString().replaceAll("\\s+$", ""));
        mEditor.putBoolean("SKIP_WELCOME", true);
        mEditor.apply();
    }

}
