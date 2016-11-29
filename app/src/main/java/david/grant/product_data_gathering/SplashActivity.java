package david.grant.product_data_gathering;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.orm.query.Select;

/**
 * Created by grant on 11/10/16.
 */

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spash_layout);
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.splashFragContainer,new SplashFragment(),null).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.default_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.settingsMenuButton:
                Toast.makeText(this,"test",Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}
