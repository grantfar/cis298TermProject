package david.grant.product_data_gathering.Model;

import david.grant.product_data_gathering.Model.Producer;

/**
 * Created by grant on 11/21/16.
 */

public class Product {
    private Producer mProducer;
    private String mUPC;
    private String mName;

    public Producer getProducer() {
        return mProducer;
    }

    public void setProducer(Producer mProducer) {
        this.mProducer = mProducer;
    }

    public String getUPC() {
        return mUPC;
    }

    public void setUPC(String mUPC) {
        this.mUPC = mUPC;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }
}
