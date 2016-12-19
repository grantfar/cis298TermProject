package david.grant.product_data_gathering;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import david.grant.product_data_gathering.DB.settingsDBHelper;
import david.grant.product_data_gathering.DB.settingsDbSchema;
import david.grant.product_data_gathering.DB.settingsDbSchema.settingsTable;

/**
 * Created by Grant on 12/6/2016.
 */

public class SettingsActivity extends Activity {
    //private Button mSaveButton;
    private Button mCreateUserButton;
    private Button mUserLoginButton;
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
        mCreateUserButton = (Button)findViewById(R.id.createUserButton);
        mUserLoginButton = (Button)findViewById(R.id.userLoginButton);
        getSettings();
        mCreateUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues currentValues = new ContentValues();
                //Future Development
                //Cursor cursor = mSettingsDatabase.query(settingsTable.cols.USER,null,null,null,null,null,null);
                //while(cursor.moveToNext()){
                    //if (cursor.getString(cursor.getPosition()).equalsIgnoreCase(mUserNameEdit.getText().toString())){
                        //Context context = getApplicationContext();
                        //Toast.makeText(context, "User Already Exists", Toast.LENGTH_SHORT).show();
                    //} else {
                        //currentValues.put(settingsTable.cols.USER,mUserNameEdit.getText().toString());
                        //currentValues.put(settingsTable.cols.PASSWORD,mPasswordEdit.getText().toString());
                        //updateSettings(currentValues);
                    //}
                //}
                //cursor.close();
                currentValues.put(settingsTable.cols.USER,mUserNameEdit.getText().toString());
                currentValues.put(settingsTable.cols.PASSWORD,mPasswordEdit.getText().toString());
                updateSettings(currentValues);
                Context context = getApplicationContext();
                Toast.makeText(context, "User Created Successfully!", Toast.LENGTH_SHORT).show();
            }
        });
        mUserLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //For Future Development
                //Cursor cursor = mSettingsDatabase.query(settingsTable.cols.USER,null,null,null,null,null,null);
                //while(cursor.moveToNext()){
                    //Cursor cursor1 = mSettingsDatabase.query(settingsTable.cols.PASSWORD,null,null,null,null,null,null,null);
                    //cursor1.moveToPosition(cursor.getPosition());
                    //if (cursor.equals(mUserNameEdit.toString())) {
                        //if (cursor1.equals(mPasswordEdit.toString())) {
                            Context context = getApplicationContext();
                            Toast.makeText(context, "Login Success!", Toast.LENGTH_SHORT).show();
                        //} else {
                            //Context context = getApplicationContext();
                            //Toast.makeText(context, "Password is Incorrect", Toast.LENGTH_SHORT).show();
                        //}
                    //} else {
                        //Context context = getApplicationContext();
                        //Toast.makeText(context, "User does not exist", Toast.LENGTH_SHORT).show();
                    //}
                    //cursor1.close();
                //}
                //cursor.close();
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
