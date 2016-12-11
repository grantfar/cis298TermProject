package david.grant.product_data_gathering;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.orm.query.Select;

/**
 * Created by grant on 11/10/16.
 */

public class SplashActivity extends singleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new SplashFragment();
    }
}
