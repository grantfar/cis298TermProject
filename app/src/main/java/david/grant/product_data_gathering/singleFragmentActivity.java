package david.grant.product_data_gathering;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Created by grant on 11/29/16.
 */

public abstract class singleFragmentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_layout);
        FragmentManager fm = getSupportFragmentManager();

        if(fm.findFragmentById(R.id.FragContainer) == null) {
            fm.beginTransaction().add(R.id.FragContainer, createFragment(), null).commit();
        }
        else{
            fm.beginTransaction().remove(fm.findFragmentById(R.id.FragContainer)).add(R.id.FragContainer,createFragment(),null).commit();
        }
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
                startActivity(new Intent(getBaseContext(),SettingsActivity.class));
                break;
        }
        return true;
    }

    protected abstract Fragment createFragment();
}
