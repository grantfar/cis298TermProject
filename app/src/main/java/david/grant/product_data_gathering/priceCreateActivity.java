package david.grant.product_data_gathering;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

/**
 * Created by grant on 11/29/16.
 */

public class priceCreateActivity extends singleFragmentActivity {
    private static final String ID_KEY = "david.grant.product_data_gathering.priceid";
    @Override
    protected Fragment createFragment() {
        Intent intent = getIntent();
        if(intent.hasExtra(ID_KEY)){
            return priceCreateFragment.getFragment((UUID)getIntent().getSerializableExtra(ID_KEY));
        }
        return new priceCreateFragment();
    }
    public static Intent newIntent(Context context){
        return new Intent(context,priceCreateActivity.class);
    }
    public static Intent newIntent(Context context, UUID id){
        Intent intent = new Intent(context,priceCreateActivity.class);
        intent.putExtra(ID_KEY,id);
        return intent;
    }
}
