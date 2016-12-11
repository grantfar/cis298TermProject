package david.grant.product_data_gathering;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * Created by grant on 11/29/16.
 */

public class priceCreateActivity extends singleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new priceCreateFragment();
    }
    public static Intent newIntent(Context context){
        return new Intent(context,priceCreateActivity.class);
    }
}
