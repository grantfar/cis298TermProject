package david.grant.product_data_gathering.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.UUID;

import david.grant.product_data_gathering.DB.ProductsDbSchema;
import david.grant.product_data_gathering.DB.priceCursorWrapper;
import david.grant.product_data_gathering.DB.productDbHelper;

/**
 * Created by grant on 11/28/16.
 * singleton class to hold all of the data
 */

public class DataHolder {
    private static DataHolder mDataHolder;

    private SQLiteDatabase mDatabase;

    private DataHolder(Context context){
        mDataHolder = this;
        mDatabase = new  productDbHelper(context).getWritableDatabase();
    }

    public static DataHolder getDataHolder(Context context){
        if (mDataHolder != null)
            return mDataHolder;
        return new DataHolder(context);
    }


    public ArrayList<Price> getPrices(){
        return new priceCursorWrapper(mDatabase.query(ProductsDbSchema.PriceTable.NAME,null,null,null,null,null,null,null)).getAllPrices();
    }

    public Price getPrice(UUID ID){
        Cursor tmpCursor = mDatabase.query(ProductsDbSchema.PriceTable.NAME,null, ProductsDbSchema.PriceTable.Cols.ENTRYID + "=?",
        new String[]{ID.toString()},null,null,null);
        return new priceCursorWrapper(tmpCursor).getPrice();
    }

    public void addPrice(Price price){
        mDatabase.insert(ProductsDbSchema.PriceTable.NAME,null,getContentValues(price));
    }

    public void updatePrice(Price price){
        mDatabase.update(ProductsDbSchema.PriceTable.NAME,getContentValues(price), ProductsDbSchema.PriceTable.Cols.ENTRYID + "=?",
                new String[]{price.getID().toString()});
    }

    public void deletePrice(UUID id){
        mDatabase.delete(ProductsDbSchema.PriceTable.NAME, ProductsDbSchema.PriceTable.Cols.ENTRYID+"=?",
                new String[]{id.toString()});
    }

    private ContentValues getContentValues(Price price){
        ContentValues returnContentValue = new ContentValues();
        returnContentValue.put(ProductsDbSchema.PriceTable.Cols.ENTRYID,price.getID().toString());
        returnContentValue.put(ProductsDbSchema.PriceTable.Cols.UPC,price.getUpc());
        returnContentValue.put(ProductsDbSchema.PriceTable.Cols.PRICE,price.getPrice());
        returnContentValue.put(ProductsDbSchema.PriceTable.Cols.PRODUCTNAME,price.getProductName());
        returnContentValue.put(ProductsDbSchema.PriceTable.Cols.PRODUCERNAME,price.getProducerName());
        return returnContentValue;
    }
}
