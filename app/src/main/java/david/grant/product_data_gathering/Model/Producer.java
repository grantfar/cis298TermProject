package david.grant.product_data_gathering.Model;


/**
 * Created by grant on 11/10/16.
 */

public class Producer{
    private String mName;
    private String mUPC;
    public Producer(String Name, String UPC){
        mName = Name;
        mUPC = UPC;
    }

    public String getName(){
        return mName;
    }
    public String getUPC(){
        return mUPC;
    }
}
