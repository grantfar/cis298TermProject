package david.grant.product_data_gathering;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import david.grant.product_data_gathering.DB.settingsDBHelper;
import david.grant.product_data_gathering.DB.settingsDbSchema;
import david.grant.product_data_gathering.DB.settingsDbSchema.settingsTable;

/**
 * Created by Grant on 12/6/2016.
 */

public class SettingsActivity extends Activity {
    private Button mSaveButton;
    private EditText mUserNameEdit;
    private EditText mPasswordEdit;
    private SQLiteDatabase mSettingsDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_layout);
        mSettingsDatabase = new settingsDBHelper(this).getWritableDatabase();
        mUserNameEdit = (EditText) findViewById(R.id.usernameEdit);
        mPasswordEdit = (EditText) findViewById(R.id.passwordEdit);
        mSaveButton = (Button) findViewById(R.id.settingSaveButton);
        getSettings();
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues currentValues = new ContentValues();
                currentValues.put(settingsTable.cols.USER,mUserNameEdit.getText().toString());
                currentValues.put(settingsTable.cols.PASSWORD,mPasswordEdit.getText().toString());
                updateSettings(currentValues);
            }
        });
    }
    private void updateSettings(ContentValues values){
        mSettingsDatabase.update(settingsTable.NAME,values,null,null);
    }

    private void getSettings(){
        Cursor cursor = mSettingsDatabase.query(settingsTable.NAME,null,null,null,null,null,null);
        cursor.moveToFirst();
        mPasswordEdit.setText(cursor.getString(cursor.getColumnIndex(settingsTable.cols.PASSWORD)));
        mUserNameEdit.setText(cursor.getString(cursor.getColumnIndex(settingsTable.cols.USER)));
        cursor.close();
    }

    @Override
    protected void onDestroy() {
        mSettingsDatabase.close();
        super.onDestroy();
    }
}
