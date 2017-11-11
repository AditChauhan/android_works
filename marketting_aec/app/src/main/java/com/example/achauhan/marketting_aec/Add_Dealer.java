package com.example.achauhan.marketting_aec;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Add_Dealer extends AppCompatActivity {

    private static final String TAG = "PhoneAuth";

    DatabaseReference musers;
    Boolean duplicate_user = false;
    String uphone_number;
    EditText dealer_name,
            dealer_shop_name,
            dealer_address,
            dealer_primary_phone,
            dealer_alternate_phone,
            dealer_rate,
            dealer_gstin;



    RatingBar dealer_rating;

    String mdate, mdealer_name, mdealer_shop_name,
            mdealer_address, mdealer_primary_phone,
            mdealer_alternate_phone, mdealer_rate,
            mdealer_gstin, mdealer_rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dealer);


        dealer_name = (EditText)findViewById(R.id.edealer_name);
        dealer_shop_name = (EditText)findViewById(R.id.edealer_shopname);
        dealer_address = (EditText)findViewById(R.id.edealer_address);
        dealer_primary_phone = (EditText)findViewById(R.id.eprimary_phone_number);
        dealer_alternate_phone = (EditText)findViewById(R.id.ealternate_phone_number);
        dealer_rate = (EditText)findViewById(R.id.erate);
        dealer_gstin = (EditText)findViewById(R.id.edealer_gstin);
        dealer_rating = (RatingBar) findViewById(R.id.ratingBar);

        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
        mdate = df.format(Calendar.getInstance().getTime());



        uphone_number =  FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber();
        Log.d(TAG,"check from phone number"+uphone_number);
        DatabaseReference root = FirebaseDatabase.getInstance().getReference();
         musers = root.child("Dealers");




    }
    public  void get_fields( View v)
    {
        mdealer_name = dealer_name.getText().toString();
        mdealer_shop_name = dealer_shop_name.getText().toString();
        mdealer_address = dealer_address.getText().toString();
        mdealer_primary_phone = "+91"+dealer_primary_phone.getText().toString();
        mdealer_alternate_phone = dealer_alternate_phone.getText().toString();
        mdealer_gstin = dealer_gstin.getText().toString();
        mdealer_rating = dealer_rating.getRating()+"";
        mdealer_rate = dealer_rate.getText().toString();

        if(mdealer_name.isEmpty() ||
                mdealer_shop_name.isEmpty() ||
                mdealer_address.isEmpty() ||
                mdealer_primary_phone.isEmpty() ||
                mdealer_alternate_phone.isEmpty() || mdealer_rate.isEmpty() || mdealer_gstin.isEmpty() || mdealer_rating.isEmpty())
        {
            Toast.makeText(this,"Please fill all the details",Toast.LENGTH_LONG).show();
        }
        else
            {
                check();

            }

        }



        public void check()
        {
            Log.d(TAG, "adit : "+musers);
            musers.addListenerForSingleValueEvent(new ValueEventListener() {
             @Override
                public void onDataChange(DataSnapshot snapshot) {
                 Log.d(TAG, "adit before :" + " came in data change");
                 if (snapshot.getChildrenCount() > 0) {
                     Log.d(TAG, "adit: " + " came in data change");
                     for (DataSnapshot data : snapshot.getChildren()) {
                         if (data.child("dealer_primary_phone").getValue().equals(mdealer_primary_phone)) {
                             Log.d(TAG, "Duplicate dealers");
                             duplicate_user = true;
                             break;
                         } else

                         {
                             Log.d(TAG, "adit else : " + " came in data change");

                         }




                     }
                 }

                    doIntent();
             }



            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





    }
    public  void doIntent()
    {
        Log.d(TAG, "do Intent");

        if(duplicate_user == true)
        {
            Log.d(TAG, "duplicate_user dealers");
        }
        else {

            Intent i = new Intent(this,Dealer_otp.class);
            i.putExtra("mdate",mdate);
            i.putExtra("mdealer_name",mdealer_name);
            i.putExtra("mdealer_shop_name",mdealer_shop_name);
            i.putExtra("mdealer_address",mdealer_address);
            i.putExtra("mdealer_primary_phone",mdealer_primary_phone);
            i.putExtra("mdealer_alternate_phone",mdealer_alternate_phone);
            i.putExtra("mdealer_rate",mdealer_rate);
            i.putExtra("mdealer_gstin",mdealer_gstin);
            i.putExtra("mdealer_rating",mdealer_rating);
            startActivity(i);




        }

    }




}
