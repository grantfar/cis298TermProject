package david.grant.product_data_gathering.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static david.grant.product_data_gathering.DB.ProductsDbSchema.*;

/**
 * Created by grant on 11/21/16.
 */

public class productDbHelper extends SQLiteOpenHelper {
    private  static final String DB_NAME = "Products_Data";
    private  static final int DB_VERSION = 1;
    public productDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create Table " + PriceTable.NAME + "( _ID integer primary key autoincrement, " +
                PriceTable.Cols.ENTRYID + " Text, " +
                PriceTable.Cols.UPC + " Text, " +
                PriceTable.Cols.PRICE + " Double, " +
                PriceTable.Cols.PRODUCTNAME + " Text, " +
                PriceTable.Cols.PRODUCERNAME + " Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
