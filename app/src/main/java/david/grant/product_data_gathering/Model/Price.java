package david.grant.product_data_gathering.Model;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * Created by grant on 11/21/16.
 */

public class Price {
    UUID mID;
    private double mPrice;
    private String mUpc;
    private String mProductName;
    private String mProducerName;

    public Price(String UPC, double price, String productName, String producerName){
        this(UUID.randomUUID(),UPC,price,productName,producerName);
    }

    public Price(UUID ID, String UPC, double price, String productName, String producerName){
        mID = ID;
        mUpc = UPC;
        mProductName = productName;
        mPrice = price;
        mProducerName = producerName;
    }

    public  UUID getID(){
        return mID;
    }

    public String getUpc() {
        return mUpc;
    }

    public double getPrice() {
        return mPrice;
    }


    public void setPrice(Double Price){
        mPrice = Price;
    }

    public String getProducerName() {
        return mProducerName;
    }

    public void setProducerName(String ProducerName) {
        this.mProducerName = ProducerName;
    }

    public String getProductName() {
        return mProductName;
    }

    public void setProductName(String ProductName) {
        this.mProductName = ProductName;
    }
}



