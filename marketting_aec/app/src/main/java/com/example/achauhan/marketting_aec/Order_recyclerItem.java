package com.example.achauhan.marketting_aec;

/**
 * Created by achauhan on 24/10/17.
 */

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


public class Order_recyclerItem extends RecyclerView.Adapter<Order_recyclerholder> {
    private static final String TAG = "PhoneAuth";

    CustomItemClickListener listener;
    private List<Order_item> order;
    protected Context context;

    public Order_recyclerItem(Context context, List<Order_item> dealer, CustomItemClickListener listener) {
        this.order = dealer;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public Order_recyclerholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, parent, false);
        final Order_recyclerholder viewHolder = new Order_recyclerholder(layoutView);

        layoutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, viewHolder.getPosition());
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(Order_recyclerholder holder, int position) {

        int p = position + 1;
        holder.mcounter.setText("No." + p);
        holder.mDealer_name.setText(order.get(position).getDealer_name());
        holder.mdealer_address.setText(order.get(position).getDealer_address());
        Log.d(TAG, order.get(position).getDealer_name());

        holder.mOrder_time.setText(order.get(position).getDate());

        if (order.get(position).getStatus() == 0) {
            holder.mStatus.setText("New order recieved");
            holder.mStatus.setBackgroundColor(Color.parseColor("#fbbc05"));

        }
        if (order.get(position).getStatus() == 1) {
            holder.mStatus.setText("Lay_up is completed");
            holder.mStatus.setBackgroundColor(Color.parseColor("#fbbc05"));
        }

        if (order.get(position).getStatus() == 2) {
            holder.mStatus.setText("lamination is completed");
            holder.mStatus.setBackgroundColor(Color.parseColor("#DB4337"));
        }
        if (order.get(position).getStatus() == 3) {
            holder.mStatus.setText("Framing is completed");
            holder.mStatus.setBackgroundColor(Color.parseColor("#DB4337"));

        }
        if (order.get(position).getStatus() == 4) {
            holder.mStatus.setText("Packaging is completed");
            holder.mStatus.setBackgroundColor(Color.parseColor("#fbbc05"));

        }

        if (order.get(position).getStatus() == 5) {
            holder.mStatus.setText("Package is in En-route");
            holder.mStatus.setBackgroundColor(Color.parseColor("#42B5F4"));

        }
        if (order.get(position).getStatus() == 6) {
            holder.mStatus.setText("Order is completed");
            holder.mStatus.setBackgroundColor(Color.parseColor("#26B14C"));


        }
            if (order.get(position).getShipping_time().matches("")) {
                holder.mShipping_date.setText("Not Assigned");
            } else {
                holder.mShipping_date.setText(order.get(position).getShipping_time());

            }
            holder.mTotal.setText("" + order.get(position).getTotal() + " rs/-");


    }


    @Override
    public int getItemCount() {

        return this.order.size();
    }
}