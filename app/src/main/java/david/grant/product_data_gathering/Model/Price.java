package david.grant.product_data_gathering.Model;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by grant on 11/21/16.
 */

public class Price {
    private double mPrice;
    private Product mProduct;
    private Date mDate;
    public Price(double price, Product product){
        mProduct = product;
        mPrice = price;
        mDate = Calendar.getInstance().getTime();
    }

    public double getPrice() {
        return mPrice;
    }

    public Product getProduct() {
        return mProduct;
    }

    public Date getDate() {
        return mDate;
    }
}
