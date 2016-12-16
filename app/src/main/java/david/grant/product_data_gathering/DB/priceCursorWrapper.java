package david.grant.product_data_gathering.DB;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import david.grant.product_data_gathering.DB.ProductsDbSchema.PriceTable;
import david.grant.product_data_gathering.Model.Price;

/**
 * Created by grant on 12/14/16.
 */

public class priceCursorWrapper {
    private Cursor mCursor;
    public priceCursorWrapper(Cursor cursor){
        mCursor = cursor;
    }

    private Price getCurrentPrice(){
        Double price = mCursor.getDouble(mCursor.getColumnIndex(PriceTable.Cols.PRICE));
        UUID id = UUID.fromString(mCursor.getString(mCursor.getColumnIndex(PriceTable.Cols.ENTRYID)));
        String UPC = mCursor.getString(mCursor.getColumnIndex(PriceTable.Cols.UPC));
        String productName = mCursor.getString(mCursor.getColumnIndex(PriceTable.Cols.PRODUCTNAME));
        String producerName = mCursor.getString(mCursor.getColumnIndex(PriceTable.Cols.PRODUCERNAME));
        return new Price(id,UPC,price,productName,producerName);
    }

    public Price getPrice(){
        mCursor.moveToFirst();
        Price returnPrice = null;
        if(!mCursor.isAfterLast()){
            returnPrice = getCurrentPrice();
        }
        mCursor.close();
        return returnPrice;
    }
    public ArrayList<Price> getAllPrices(){
        ArrayList<Price> returnPrices = new ArrayList<>();
        mCursor.moveToFirst();
        while (!mCursor.isAfterLast()){
            returnPrices.add(getCurrentPrice());
            mCursor.moveToNext();
        }
        mCursor.close();
        return  returnPrices;
    }
}
