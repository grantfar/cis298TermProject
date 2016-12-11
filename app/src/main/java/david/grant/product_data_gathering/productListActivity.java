package david.grant.product_data_gathering;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by grant on 11/28/16.
 */

public class productListActivity extends singleFragmentActivity{

    public static Intent newIntent(Context context){
        return new Intent(context,productListActivity.class);
    }

    @Override
    protected Fragment createFragment() {
        return new ProductListFragment();
    }
}
