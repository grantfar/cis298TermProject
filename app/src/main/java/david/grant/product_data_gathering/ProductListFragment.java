package david.grant.product_data_gathering;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Date;

import david.grant.product_data_gathering.Model.DataHolder;
import david.grant.product_data_gathering.Model.Price;
import david.grant.product_data_gathering.Model.Product;

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
        mDataHolder = DataHolder.getDataHolder();
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
            holder.Price = mDataHolder.getPrices().get(position);
            holder.updateView();
        }

        @Override
        public int getItemCount() {
            return mDataHolder.getPrices().size();
        }
    }

    private class productHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public Price  Price;
        private TextView mName;
        private TextView mPrice;
        private TextView mDate;
        public productHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mName = (TextView) itemView.findViewById(R.id.listItemName);
            mPrice = (TextView) itemView.findViewById(R.id.listItemPrice);
            mDate = (TextView) itemView.findViewById(R.id.listItemDate);

        }

        public void updateView(){
            mPrice.setText("$"+Price.getPrice());
            mDate.setText(Price.getDate().toString());
            mName.setText(Price.getProduct().getName());
        }
        @Override
        public void onClick(View view) {

        }
    }
    private void updateUI(){
        if(mAdapter == null)
        {
            mAdapter = new productAdapter();
            mRecyclerView.setAdapter(mAdapter);
        }
        mAdapter.notifyDataSetChanged();
    }
}
