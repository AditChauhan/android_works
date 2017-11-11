package com.example.achauhan.marketting_aec;

/**
 * Created by achauhan on 24/10/17.
 */
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.util.List;

public class Order_recyclerholder extends RecyclerView.ViewHolder{



    TextView mcounter,mStatus, mTotal, mdealer_address, mOrder_time, mDealer_name,mShipping_date;

    private static final String TAG = "PhoneAuth";


    public Order_recyclerholder(final View itemView) {
        super(itemView);
        mcounter = (TextView)itemView.findViewById(R.id.counter);
        mStatus = (TextView)itemView.findViewById(R.id.order_status);
        mTotal = (TextView)itemView.findViewById(R.id.ototal);
        mShipping_date = (TextView)itemView.findViewById(R.id.shiping_time);
        mDealer_name = (TextView)itemView.findViewById(R.id.mdealer_name);
        mOrder_time = (TextView)itemView.findViewById(R.id.order_time);
        mdealer_address = (TextView)itemView.findViewById(R.id.mdealer_address);

    }
}