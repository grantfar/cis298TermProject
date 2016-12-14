package david.grant.product_data_gathering.DB;

/**
 * Created by grant on 11/21/16.
 */

public final class ProductsDbSchema {


    public final static class PriceTable{
        public static final String NAME = "Prices";
        public final static class Cols{
            public static final String ENTRYID = "entry_id";
            public static final String PRICE = "price";
            public static final String UPC = "UPC";
            public static final String PRODUCTNAME = "product_name";
            public static final String PRODUCERNAME = "producer_name";
        }
    }
}


