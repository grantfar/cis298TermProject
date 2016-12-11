package david.grant.product_data_gathering;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import david.grant.product_data_gathering.Model.DataHolder;
import david.grant.product_data_gathering.Model.Price;
import david.grant.product_data_gathering.Model.Producer;
import david.grant.product_data_gathering.Model.Product;

/**
 * Created by grant on 11/29/16.
 */

public class priceCreateFragment extends Fragment{
    private EditText mUPC;
    private EditText mProductName;
    private EditText mProducerName;
    private EditText mPrice;
    private Button mSaveButton;
    private DataHolder mDataHolder;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.price_edit_fragment,container,false);
        mDataHolder = DataHolder.getDataHolder();
        mUPC = (EditText) view.findViewById(R.id.upcEdit);
        mProducerName = (EditText) view.findViewById(R.id.producerEdit);
        mProductName = (EditText) view.findViewById(R.id.nameEdit);
        mPrice = (EditText) view.findViewById(R.id.priceEdit);
        mSaveButton = (Button)view.findViewById(R.id.saveButton);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mUPC.getText().toString().length() == 12){
                    Producer tProducer = new Producer(mProducerName.getText().toString(),mUPC.getText().toString().substring(1,6));
                    Product tProduct = new Product(mUPC.getText().toString(),mProductName.getText().toString(),tProducer);
                    Price tPrice = new Price(Double.parseDouble(mPrice.getText().toString()),tProduct);
                    mDataHolder.getPrices().add(tPrice);
                    Context context = getContext();
                    Toast.makeText(context, "Saved Successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}
