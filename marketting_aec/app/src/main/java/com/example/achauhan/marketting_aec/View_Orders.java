package com.example.achauhan.marketting_aec;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;

public class View_Orders extends AppCompatActivity {
    private static final String TAG = "PhoneAuth";
    DatabaseReference dbref;
    String muphone ;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    ArrayList<Order_item> O_items;

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_orders);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        muphone = FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber();
        dbref  = FirebaseDatabase.getInstance().getReference().child("Orders");
        recyclerView =  (RecyclerView)findViewById(R.id.recycler_view);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading Orders, Please wait");
        O_items = new ArrayList<>();
        dialog.show();
        check();


    }


    public void check()
    {
        Log.d(TAG, "check");
        dbref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Log.d(TAG, "adit before :" + " came in data change");
                if (snapshot.getChildrenCount() > 0) {
                    for (DataSnapshot data : snapshot.getChildren()) {
                        Log.d(TAG, data.toString());
                        Log.d(TAG,muphone);
                        if (data.child("mid").getValue().equals(muphone))
                        {
                            Log.d(TAG,"data inside "+ data.getValue(Order_item.class).toString());
                            doIntent(data);

                        }

                    }

                    inflate_orders();
                }
                else
                {
                   no_data();
                }

            }



            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    private  void no_data()
    {
        dialog.setTitle("No Orders found");
        dialog.dismiss();
        dialog.cancel();
        Intent i = new Intent(this,Marketting_Homepage.class);
        startActivity(i);
        finish();

    }

    private void inflate_orders() {
        Log.d(TAG,"this is working");
        dialog.cancel();
        recyclerView.setAdapter(new Order_recyclerItem(this, O_items, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {


                show_singleton(position);

            }
        }));

    }

    public void show_singleton(int oi)
    {

        Order_item clicked_item = O_items.get(oi);
        Intent intent = new Intent(this, Order_Full.class);
        intent.putExtra("order_object", clicked_item);
        startActivity(intent);

    }

    public void doIntent(DataSnapshot x)
    {
        dialog.cancel();
        Log.d(TAG,"doIntent");
        Log.d(TAG,x.getValue(Order_item.class).getMid());



        O_items.add(x.getValue(Order_item.class));

        Log.d(TAG," size of arraylist is :" +
                ""+O_items.size());
    }
}
