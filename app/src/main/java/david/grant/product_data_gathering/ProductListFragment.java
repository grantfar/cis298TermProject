package david.grant.product_data_gathering;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import david.grant.product_data_gathering.Model.DataHolder;
import david.grant.product_data_gathering.Model.Price;

/**
 * Created by grant on 11/28/16.
 */

public class ProductListFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private DataHolder mDataHolder;
    private productAdapter mAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.product_list_fragment,container,false);
        mRecyclerView = (RecyclerView)v.findViewById(R.id.productRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mDataHolder = DataHolder.getDataHolder(getActivity());
        updateUI();
        return v;
    }

    private class productAdapter extends RecyclerView.Adapter<productHolder>
    {

        @Override
        public productHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new productHolder(layoutInflater.inflate(R.layout.list_item,parent,false));
        }

        @Override
        public void onBindViewHolder(productHolder holder, int position) {
            holder.price = mDataHolder.getPrices().get(position);
            holder.updateView();
        }

        @Override
        public int getItemCount() {
            return mDataHolder.getPrices().size();
        }
    }

    private class productHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public Price price;
        private TextView mName;
        private TextView mPrice;
        private TextView mUPC;
        public productHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mName = (TextView) itemView.findViewById(R.id.listItemName);
            mPrice = (TextView) itemView.findViewById(R.id.listItemPrice);
            mUPC= (TextView) itemView.findViewById(R.id.listItemUPC);

        }

        public void updateView(){
            mPrice.setText("$"+price.getPrice());
            mUPC.setText(price.getUpc());
            mName.setText(price.getProductName());
        }
        @Override
        public void onClick(View view) {
            Intent intent = priceCreateActivity.newIntent(getActivity(),price.getID());
            startActivity(intent);
        }
    }

    //updates the UI for the data in the database
    private void updateUI(){
        if(mAdapter == null)
        {
            mAdapter = new productAdapter();
            mRecyclerView.setAdapter(mAdapter);
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }
}
