package com.example.achauhan.marketting_aec;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Dealer_edit extends AppCompatActivity {


    String uphone_number;
    EditText dealer_name,
            dealer_shop_name,
            dealer_address,
            dealer_primary_phone,
            dealer_alternate_phone,
            dealer_rate,
            dealer_gstin;
    RatingBar dealer_rating;

    DatabaseReference dbref;


    String  mdealer_id,mdealer_name, mdealer_shop_name, mdealer_address, mdealer_primary_phone, mdealer_alphone, mdealer_rate, mdealer_gstin, mdealer_rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dealer_edit);

        dbref = FirebaseDatabase.getInstance().getReference("Dealers");
        dealer_name = (EditText)findViewById(R.id.edealer_name);
        dealer_shop_name = (EditText)findViewById(R.id.edealer_shopname);
        dealer_address = (EditText)findViewById(R.id.edealer_address);
        dealer_primary_phone = (EditText)findViewById(R.id.eprimary_phone_number);
        dealer_alternate_phone = (EditText)findViewById(R.id.ealternate_phone_number);
        dealer_rate = (EditText)findViewById(R.id.erate);
        dealer_gstin = (EditText)findViewById(R.id.edealer_gstin);



        mdealer_id   = getIntent().getExtras().getString("mdealer_id");
        mdealer_address   = getIntent().getExtras().getString("mdealer_address");
        mdealer_alphone = getIntent().getExtras().getString("mdealer_alphone");
        mdealer_gstin = getIntent().getExtras().getString("mdealer_gstin");
        mdealer_name = getIntent().getExtras().getString("mdealer_name");
        mdealer_rate = getIntent().getExtras().getString("mdealer_rate");
        mdealer_primary_phone = getIntent().getExtras().getString("mdealer_primary_phone");
        mdealer_shop_name = getIntent().getExtras().getString("mdealer_shop_name");
        mdealer_rating = getIntent().getExtras().getString("mdealer_rating");

        dealer_name.setText(mdealer_name);
        dealer_address.setText(mdealer_address);
        dealer_gstin.setText(mdealer_gstin);
        dealer_shop_name.setText(mdealer_shop_name);
        dealer_rate.setText(mdealer_rate);
        dealer_alternate_phone.setText(mdealer_alphone);
        dealer_primary_phone.setText(mdealer_primary_phone);

    }

    public void edit(View v)
    {

        mdealer_name = dealer_name.getText().toString();
        mdealer_shop_name = dealer_shop_name.getText().toString();
        mdealer_address = dealer_address.getText().toString();
        mdealer_primary_phone = dealer_primary_phone.getText().toString();
        mdealer_alphone = dealer_alternate_phone.getText().toString();
        mdealer_gstin = dealer_gstin.getText().toString();
        mdealer_rate = dealer_rate.getText().toString();

        if(mdealer_name.isEmpty() ||
                mdealer_shop_name.isEmpty() ||
                mdealer_address.isEmpty() ||
                mdealer_primary_phone.isEmpty() ||
                mdealer_alphone.isEmpty() ||
                mdealer_rate.isEmpty() ||
                mdealer_gstin.isEmpty() )
        {
            Toast.makeText(this,"Please fill all the details",Toast.LENGTH_LONG).show();
        }

            else
            {
                do_Save();
            }


    }

    public void do_Save()
    {

        dbref.child(mdealer_id).child("dealer_name").setValue(mdealer_name);
        dbref.child(mdealer_id).child("dealer_shop_name").setValue(mdealer_shop_name);
        dbref.child(mdealer_id).child("dealer_address").setValue(mdealer_address);
        dbref.child(mdealer_id).child("dealer_primary_phone").setValue(mdealer_primary_phone);
        dbref.child(mdealer_id).child("dealer_alternate_phone").setValue(mdealer_alphone);
        dbref.child(mdealer_id).child("dealer_gstin").setValue(mdealer_gstin);
        dbref.child(mdealer_id).child("dealer_rate").setValue(mdealer_rate);

        Toast.makeText(this,"Details has been updated",Toast.LENGTH_LONG).show();


        Intent i = new Intent(this,Marketting_Homepage.class);
        startActivity(i);
        finish();
    }




}
