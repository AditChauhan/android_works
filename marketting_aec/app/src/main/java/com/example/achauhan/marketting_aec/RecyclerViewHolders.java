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

public class RecyclerViewHolders extends RecyclerView.ViewHolder{
    private static final String TAG = RecyclerViewHolders.class.getSimpleName();
    TextView mdealer_name,
            mdealer_shop_name,
            mdealer_address;



    public RecyclerViewHolders(final View itemView) {
        super(itemView);
        mdealer_name = (TextView)itemView.findViewById(R.id.mdealer_name);
        mdealer_shop_name = (TextView)itemView.findViewById(R.id.mdealer_shop_name);
        mdealer_address = (TextView)itemView.findViewById(R.id.mdealer_address);


    }
}