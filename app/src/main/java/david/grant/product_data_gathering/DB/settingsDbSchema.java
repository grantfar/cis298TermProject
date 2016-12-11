package david.grant.product_data_gathering.DB;

/**
 * Created by Grant on 12/6/2016.
 */

public final class settingsDbSchema {
    public static final class settingsTable{
        public static final String NAME = "settings";
        public static final class cols{
            public static final String  USER  =  "user_name";
            public static final String PASSWORD = "password";
        }
    }
}
