package david.grant.product_data_gathering;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by grant on 11/10/16.
 */

public class SplashFragment extends Fragment {

    private Button mCreateNewButton;
    private Button mEditButton;
    private Button mSaveButton;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.splash_fragment,container,false);

        mEditButton = (Button) v.findViewById(R.id.editDataButton);
        mCreateNewButton = (Button) v.findViewById(R.id.createNewButton);

        mCreateNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(priceCreateActivity.newIntent(getContext()));
            }
        });

        mEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(productListActivity.newIntent(getContext()));
            }
        });

        return v;
    }
}
