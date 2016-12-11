package david.grant.product_data_gathering.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import david.grant.product_data_gathering.DB.settingsDbSchema.settingsTable;

/**
 * Created by Grant on 12/6/2016.
 */

public class settingsDBHelper extends SQLiteOpenHelper {
    private static final String NAME = "settings.db";
    private static final int VERSION = 1;

    public settingsDBHelper(Context context){
        super(context,NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + settingsTable.NAME + "( " + settingsTable.cols.USER + "," + settingsTable.cols.PASSWORD + ")");
        sqLiteDatabase.execSQL("Insert Into " + settingsTable.NAME + "(" + settingsTable.cols.USER + "," + settingsTable.cols.PASSWORD + ") VALUES ('','');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
