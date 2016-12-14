package david.grant.product_data_gathering.Model;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * Created by grant on 11/21/16.
 */

public class Price {
    UUID ID;
    private double mPrice;
    private Product mProduct;
    private Date mDate;
    public Price(double price, Product product){
        ID = UUID.randomUUID();
        mProduct = product;
        mPrice = price;
        mDate = Calendar.getInstance().getTime();
    }

    public  UUID getID =

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
