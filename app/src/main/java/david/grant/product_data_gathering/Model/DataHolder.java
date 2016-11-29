package david.grant.product_data_gathering.Model;

import java.util.ArrayList;

/**
 * Created by grant on 11/28/16.
 * singleton class to hold all of the data
 */

public class DataHolder {
    private static DataHolder mDataHolder;
    private ArrayList<Price> mPrices;
    private ArrayList<Product> mProducts;
    private ArrayList<Producer> mProducers;

    private DataHolder(){
        mPrices = new ArrayList<>();
        mProducers = new ArrayList<>();
        mProducts = new ArrayList<>();
        mDataHolder = this;
    }

    public static DataHolder getDataHolder(){
        if (mDataHolder != null)
            return mDataHolder;
        return new DataHolder();
    }
    //getters
    public ArrayList<Price> getPrices(){
        return mPrices;
    }
}
