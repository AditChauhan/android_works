package com.example.achauhan.marketting_aec;

/**
 * Created by achauhan on 24/10/17.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.List;


    public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolders> {

        CustomItemClickListener listener;
        private List<Dealer_object> dealer;
        protected Context context;
        public RecyclerViewAdapter(Context context, List<Dealer_object> dealer,CustomItemClickListener listener) {
            Log.d("phoneAuth","instantiated");

            this.dealer = dealer;
            this.context = context;
            this.listener = listener;

        }



        @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType)
    {
        Log.d("phoneAuth","onCreateViewHolder");

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.dealer_item, parent, false);
        final RecyclerViewHolders viewHolder = new RecyclerViewHolders(layoutView);

        layoutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, viewHolder.getPosition());
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position)
    {
        Log.d("phoneAuth","onBindViewHolder");


        holder.mdealer_name.setText(dealer.get(position).dealer_name);
        holder.mdealer_shop_name.setText(dealer.get(position).dealer_shop_name);
        holder.mdealer_address.setText(dealer.get(position).dealer_address);


    }


    @Override
    public int getItemCount() {

        return this.dealer.size();
    }
}