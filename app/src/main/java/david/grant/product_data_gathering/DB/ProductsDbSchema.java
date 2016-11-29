package david.grant.product_data_gathering.DB;

/**
 * Created by grant on 11/21/16.
 */

public final class ProductsDbSchema {



    public final static class ProductTable{
        public static final String NAME = "Products";
        public static final String CREATIONSQL = "Create Table " + NAME + "(" + Cols.ID + "," + Cols.UPC + ","+ Cols.NAME + "," +Cols.PRODUCERID+");";
        public final static class Cols{
            public static final String ID = "productID";
            public static final String UPC = "UPC";
            public static final String NAME = "Name";
            public static final String PRODUCERID = "producerId";
        }
    }

    public final static class ProducerTable{
        public static final String NAME = "Producers";
        public static final String CREATIONSQL = "Create Table " + NAME + "(" + Cols.ID + "," + Cols.UPC + "," + Cols.NAME + ");";
        public final static class Cols{
            public static final String ID = "producerID";
            public static final String UPC = "UPC";
            public static final String NAME = "Name";
        }
    }

    public final static class PriceTable{
        public static final String NAME = "Prices";
        public static final String CREATIONSQL = "Create Table " + NAME + "(" + Cols.ID + "," + Cols.PRODUCTID + "," + Cols.PRICE + "," + Cols.TIMESTAMP + ");";
        public final static class Cols{
            public static final String ID = "priceID";
            public static final String PRODUCTID = "productId";
            public static final String PRICE = "price";
            public static final String TIMESTAMP = "priceTimeStamp";
        }
    }
}


