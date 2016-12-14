package david.grant.product_data_gathering;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

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
    private Button mScanUPCButton;
    private Button mDeleteButton;
    private DataHolder mDataHolder;

    private final String UPC_KEY = "upc";
    private final String PRICE_KEY = "price";
    private final String PRODUCER_KEY = "producer";
    private final String PRODUCT_KEY = "product name";

    private File mImage;
    private static final int GET_PICTURE = 1;

    public static priceCreateFragment getFragment(){
        priceCreateFragment theFragment = new priceCreateFragment();
        theFragment.setArguments(new Bundle());
        return theFragment;
    }

    public static priceCreateFragment getFragment(UUID id){
        
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
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
                    putData(getArguments());
                    setData(savedInstanceState);
                }
            }
        });

        mScanUPCButton = (Button) view.findViewById(R.id.upcCaptureButton);
        mScanUPCButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchCamera();
            }
        });

        mDeleteButton = (Button) view.findViewById(R.id.deleteButton);
        setData(savedInstanceState);
        return view;
    }

    private void getFile(){
        File directory = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if(mImage != null){
            mImage.delete();
            mImage = null;
        }
        try {
            mImage = File.createTempFile("tempview",".jpg",directory);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void launchCamera(){
        getFile();
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri photoUri = FileProvider.getUriForFile(getActivity(),"david.grant.product_data_gathering",mImage);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,photoUri);
        startActivityForResult(intent,GET_PICTURE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GET_PICTURE && resultCode == getActivity().RESULT_OK){

            //decode jpg into bitmap
            Bitmap barCodeBitmap = BitmapFactory.decodeFile(mImage.getAbsolutePath());
            //delete jpg
            mImage.delete();
            mImage = null;

            //set up Barcode decoder to upc format
            BarcodeDetector barcodeDetector = new  BarcodeDetector.Builder(getActivity().getApplicationContext())
                    .setBarcodeFormats(Barcode.UPC_A|Barcode.UPC_E).build();
            //read bitmap for barcode data
            Frame frame = new  Frame.Builder().setBitmap(barCodeBitmap).build();
            SparseArray<Barcode> barCodes = barcodeDetector.detect(frame);
            if(barCodes.size() != 0){
                mUPC.setText(barCodes.valueAt(0).rawValue);
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        putData(outState);
        super.onSaveInstanceState(outState);
    }

    private void setData(Bundle savedState){
        Bundle arguments = getArguments();
        if(arguments == null || arguments.isEmpty()){
            mDeleteButton.setEnabled(false);
        }
        else {
            mUPC.setClickable(false);
            mScanUPCButton.setEnabled(false);
            if(savedState == null || savedState.isEmpty()){
                mUPC.setText(arguments.getString(UPC_KEY));
                mPrice.setText(arguments.getString(PRICE_KEY));
                mProductName.setText(arguments.getString(PRODUCT_KEY));
                mProducerName.setText(arguments.getString(PRODUCER_KEY));
            }
        }
        if (savedState != null && !savedState.isEmpty()){
            mUPC.setText(savedState.getString(UPC_KEY));
            mPrice.setText(savedState.getString(PRICE_KEY));
            mProductName.setText(savedState.getString(PRODUCT_KEY));
            mProducerName.setText(savedState.getString(PRODUCER_KEY));
        }
    }

    private void putData(Bundle putBundle){
        putBundle.putString(UPC_KEY,mUPC.getText().toString());
        putBundle.putString(PRODUCT_KEY,mProductName.getText().toString());
        putBundle.putString(PRODUCER_KEY,mProducerName.getText().toString());
        putBundle.putString(PRICE_KEY,mPrice.getText().toString());
    }
}
