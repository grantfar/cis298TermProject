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

public class productListActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.listFragmentContainer);
        if(fragment == null){
            fragment = new ProductListFragment();
            fm.beginTransaction().add(R.id.listFragmentContainer,fragment).commit();
        }
    }
    public static Intent newIntent(Context context){
        return new Intent(context,productListActivity.class);
    }
}
