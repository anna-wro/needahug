package pm.anna.needahug;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Random;

import pm.anna.needahug.data.HugsContract;

import static pm.anna.needahug.R.style.dialog;

public class EditActivity extends BaseActivity {

    ImageButton mBackButton;
    Button mCancelHugButton;
    Button mSaveHugButton;
    EditText mNewHugText;
    Toolbar mToolbar;
    String mNewMessages[];
    String yourName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUiElements();
        getDataFromIntent();
        addListeners();
    }

    private void initUiElements(){
        setContentView(R.layout.activity_edit);
        mToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        mBackButton = (ImageButton) findViewById(R.id.backButton);
        mCancelHugButton = (Button) findViewById(R.id.cancelHugButton);
        mSaveHugButton = (Button) findViewById(R.id.saveHugButton);
        mNewHugText = (EditText) findViewById(R.id.newHugText);
        mNewMessages = getResources().getStringArray(R.array.newHug);
        setSupportActionBar(mToolbar);
    }

    private void getDataFromIntent(){
        Intent getName = getIntent();
        yourName = getName.getStringExtra("name");
        String hintMessageToFormat = getResources().getString(R.string.hug_hint);
        String hintMessage = String.format(hintMessageToFormat, yourName);
        mNewHugText.setHint(hintMessage);
    }

    private void addListeners(){
        View.OnClickListener askIfCancel = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hug = mNewHugText.getText().toString();
                if(!hug.trim().isEmpty()){
                    showCancelConfirmationDialog();
                } else {
                    finish();
                }
            }
        };
        mSaveHugButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertHug();
            }
        });
        mBackButton.setOnClickListener(askIfCancel);
        mCancelHugButton.setOnClickListener(askIfCancel);
    }

    private void showCancelConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, dialog);
        builder.setMessage(R.string.cancel_dialog_msg);

        builder.setPositiveButton(R.string.go_back, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });
        builder.setNegativeButton(R.string.keep_editing, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button negativeButton = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        negativeButton.setTextColor(getResources().getColor(R.color.green));

        Button positiveButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        positiveButton.setTextColor(getResources().getColor(R.color.red));
    }

    private void insertHug() {
        String hug = mNewHugText.getText().toString();
        if(hug == null || hug.trim().isEmpty()){
            Toast.makeText(this, getString(R.string.empty_hug), Toast.LENGTH_SHORT).show();
            return;
        }
        ContentValues values = new ContentValues();
        values.put(HugsContract.HugsEntry.COLUMN_HUG, hug);
        Uri newUri = getContentResolver().insert(HugsContract.HugsEntry.CONTENT_URI, values);
        if (newUri == null) {
            Toast.makeText(this, getString(R.string.save_error), Toast.LENGTH_SHORT).show();
        } else {
            // Show random message
            Random generator = new Random();
            int num = generator.nextInt(mNewMessages.length);
            Toast.makeText(this, mNewMessages[num], Toast.LENGTH_SHORT).show();

            // Go back to HugActivity
            Intent intent = new Intent(this, HugActivity.class);
            intent.putExtra(getString(R.string.key_new_hug), hug);
            intent.putExtra(getString(R.string.key_name), yourName);
            startActivity(intent);
        }
    }
}


