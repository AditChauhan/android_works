package com.example.achauhan.marketting_aec;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class View_dealers extends AppCompatActivity {
    private static final String TAG = View_dealers.class.getSimpleName();
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private EditText addTaskBox;
    private DatabaseReference databaseReference,musers;
    String uphone;
    ArrayList<Dealer_object> dealer_list;
    private FirebaseAuth fbAuth;
    Dealer_object ddo;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_dealer);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        musers = databaseReference.child("Dealers");
        dealer_list = new ArrayList<>();

        fbAuth = FirebaseAuth.getInstance();

        dialog = new ProgressDialog(this);

        uphone = fbAuth.getCurrentUser().getPhoneNumber();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        Log.d("TAG IS", TAG);
        Log.d(TAG, "inside the ");
        Log.d(TAG, uphone + "");
        Log.d(TAG, musers + "");
        dialog.setTitle("Loading Dealers, Please wait");

        dialog.show();


        musers.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.getChildrenCount() > 0)
                {
                    Log.d(TAG, "adit: " + " came in data change");


                }
                else
                {
                    display_nodata();

                }

            }




            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        musers.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Log.d(TAG,"inside the onChildAdded");

                dialog.dismiss();
                getAllTask(dataSnapshot);
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                Log.d(TAG,"inside the onChildChanged");
                dialog.dismiss();


                getAllTask(dataSnapshot);
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


    }

    public void display_nodata()
    {
        dialog.dismiss();
        Toast.makeText(this,"no data is found",Toast.LENGTH_LONG).show();
        Intent i = new Intent(this,Marketting_Homepage.class);
        startActivity(i);


    }


    private void getAllTask(DataSnapshot dataSnapshot){
        dialog.dismiss();

        Log.d(TAG,"getalltask");
        Log.d(TAG,dataSnapshot.toString());
        Log.d(TAG,"inside the ");
        ddo = new Dealer_object();
        ddo =  dataSnapshot.getValue(Dealer_object.class);


        dealer_list.add(ddo);
        Log.d(TAG,""+dealer_list.size());




        recyclerView.setAdapter(new RecyclerViewAdapter(this, dealer_list, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Log.d(TAG, "clicked position:" + position);
                String  postId = dealer_list.get(position).getDealer_address();
                move(position,dealer_list);

            }
        })
        );

    }



        public void move(int position,ArrayList<Dealer_object> dealers)
        {
            Dealer_object dof = dealers.get(position);

            String mdealer_address =dof.getDealer_address();
            String mdealer_id =dof.getDealer_id();
            String mdealer_date =dof.getDealer_date();
            String mdealer_alphone= dof.getDealer_alternate_phone();
            String mdealer_gstin =dof.getDealer_gstin();
            String mdealer_name =dof.getDealer_name();
            String mdealer_rate =dof.getDealer_rate();
            String mdealer_primary_phone= dof.getDealer_primary_phone();
            String mdealer_shop_name =dof.getDealer_shop_name();
            String mdealer_rating= dof.getDealer_rating();

            Intent i = new Intent(this,Full_dealer.class);
            i.putExtra("mdealer_id",mdealer_id);
            i.putExtra("mdealer_date",mdealer_date);
            i.putExtra("mdealer_address",mdealer_address);
            i.putExtra("mdealer_alphone",mdealer_alphone);
            i.putExtra("mdealer_gstin",mdealer_gstin);
            i.putExtra("mdealer_name",mdealer_name);
            i.putExtra("mdealer_primary_phone",mdealer_primary_phone);
            i.putExtra("mdealer_rate",mdealer_rate);
            i.putExtra("mdealer_rating",mdealer_rating);
            i.putExtra("mdealer_shop_name",mdealer_shop_name);
            startActivity(i);
            finish();
        }

}
