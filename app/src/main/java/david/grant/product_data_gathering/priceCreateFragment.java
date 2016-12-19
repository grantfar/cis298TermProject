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
import android.util.Log;
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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

import david.grant.product_data_gathering.Model.DataHolder;
import david.grant.product_data_gathering.Model.Price;

/**
 * Created by grant on 11/29/16.
 */

public class priceCreateFragment extends Fragment{

    private static final String TAG = "priceCreateFragment";

    private EditText mUPC;
    private EditText mProductName;
    private EditText mProducerName;
    private EditText mPriceEdit;
    private Button mSaveButton;
    private Button mScanUPCButton;
    private Button mDeleteButton;
    private Button mNewButton;
    private DataHolder mDataHolder;

    private Price mPrice;
    private boolean inDatabase;

    private static final String ID_KEY = "price_uuid";
    private static final String DATABASE_KEY = "in_data_base";
    private static final String UPC_KEY = "upc";
    private static final String PRICE_KEY = "price";
    private static final String PRODUCER_KEY = "producer";
    private static final String PRODUCT_KEY = "product name";

    private File mImage;
    private static final int GET_PICTURE = 1;


    public static priceCreateFragment getFragment(UUID id){
        priceCreateFragment theFrag = new priceCreateFragment();
        Bundle arg = new Bundle();
        arg.putSerializable(ID_KEY, id);
        theFrag.setArguments(arg);
        return theFrag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.price_edit_fragment,container,false);
        mDataHolder = DataHolder.getDataHolder(getActivity());
        mUPC = (EditText) view.findViewById(R.id.upcEdit);
        mProducerName = (EditText) view.findViewById(R.id.producerEdit);
        mProductName = (EditText) view.findViewById(R.id.nameEdit);
        mPriceEdit = (EditText) view.findViewById(R.id.priceEdit);
        inDatabase = false;
        if(getArguments() != null && !getArguments().isEmpty()){
            mPrice = mDataHolder.getPrice((UUID) getArguments().getSerializable(ID_KEY));
            inDatabase = true;
            getArguments().clear();
        }

        mNewButton = (Button) view.findViewById(R.id.newButton);
        mNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPrice = null;
                inDatabase = false;
                mUPC.setText("");
                mProductName.setText("");
                mProducerName.setText("");
                mPriceEdit.setText("");
                setEnabledUI();
            }
        });

        mSaveButton = (Button)view.findViewById(R.id.saveButton);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(verifyInput()){
                    if(inDatabase){
                        mPrice.setPrice(Double.parseDouble(mPriceEdit.getText().toString()));
                        mPrice.setProducerName(mProducerName.getText().toString());
                        mPrice.setProductName(mProductName.getText().toString());
                        mDataHolder.updatePrice(mPrice);
                    }
                    else{
                        mDataHolder.addPrice(new Price(mUPC.getText().toString(),Double.parseDouble(mPriceEdit.getText().toString()),
                                mProductName.getText().toString(),mProducerName.getText().toString()));
                        inDatabase = true;
                        setEnabledUI();
                    }
                    Toast.makeText(getContext(), "Saved Successfully", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getActivity(), "Invalid Input", Toast.LENGTH_SHORT).show();
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
        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDataHolder.deletePrice(mPrice.getID());
                getActivity().finish();
            }
        });
        setData(savedInstanceState);
        setEnabledUI();
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
                //For future development
                //fetchData(mUPC.getText().toString());
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(DATABASE_KEY,inDatabase);
        if(inDatabase){
            outState.putSerializable(ID_KEY,mPrice.getID());
        }
        outState.putString(PRODUCT_KEY,mProductName.getText().toString());
        outState.putString(PRODUCER_KEY,mProducerName.getText().toString());
        outState.putString(UPC_KEY,mUPC.getText().toString());
        outState.putString(PRICE_KEY,mPriceEdit.getText().toString());
        super.onSaveInstanceState(outState);
    }

    private void setData(Bundle savedState){
        if(savedState != null && !savedState.isEmpty()){
            inDatabase = savedState.getBoolean(DATABASE_KEY);
            if(inDatabase){
                mPrice = mDataHolder.getPrice((UUID) savedState.getSerializable(ID_KEY));
            }
            mUPC.setText(savedState.getString(UPC_KEY));
            mPriceEdit.setText(savedState.getString(PRICE_KEY));
            mProducerName.setText(savedState.getString(PRODUCER_KEY));
            mProductName.setText(savedState.getString(PRODUCT_KEY));
        }
        else if (mPrice!=null){
            mUPC.setText(mPrice.getUpc());
            mPriceEdit.setText(Double.toString(( mPrice.getPrice())));
            mProducerName.setText(mPrice.getProducerName());
            mProductName.setText(mPrice.getProductName());
        }
    }

    //Detects if the product is in the database and disables/enables UI elements accordingly
    private void setEnabledUI(){
        if(inDatabase){
            mUPC.setEnabled(false);
            mScanUPCButton.setEnabled(false);
            mDeleteButton.setEnabled(true);
        }
        else{
            mUPC.setEnabled(true);
            mScanUPCButton.setEnabled(true);
            mDeleteButton.setEnabled(false);
        }
    }

    //Verifies that the collected data is valid
    private boolean verifyInput(){
        return (mUPC.getText().length() == 12 || mUPC.getText().length() == 8) && mProducerName.getText().length() > 0 &&
                mProductName.getText().length() > 0 && mPriceEdit.getText().length() > 0;
    }

    private byte[] getUrlBytes(String urlSpec) throws IOException {
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException(connection.getResponseMessage() + ": with " + urlSpec);
            }
            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            in.close();
            return out.toByteArray();
        } finally {
            connection.disconnect();
        }
    }

    private String getURLString(String urlSpec) throws IOException {
        return new String(getUrlBytes(urlSpec));
    }

    private void fetchData(String upc) {
        try{
            String url = Uri.parse(getString(R.string.api_url,upc)).buildUpon().build().toString();
            String jsonString = this.getURLString(url);
            Log.i(TAG, "Fetched Contents of URL: " + jsonString);
            //} catch(JSONException jse) {
            //    Log.e(TAG, "Failed to parse JSON string: ", jse);
        } catch(IOException ioe) {
            Toast.makeText(getContext(), "Failed to fetch URL: " + ioe, Toast.LENGTH_SHORT).show();
        }
    }

}
