package com.example.achauhan.marketting_aec;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Full_dealer extends AppCompatActivity {

    String  mdealer_id,
            mdealer_date,
            mdealer_address,
            mdealer_alphone,
            mdealer_gstin,
            mdealer_name,
            mdealer_rate,
            mdealer_primary_phone,
            mdealer_shop_name,
            mdealer_rating;


    TextView
            dealer_name,
            dealer_shop_name,
            dealer_address,
            dealer_primary_phone,
            dealer_alternate_phone,
            dealer_gstin,
            dealer_rating,
            dealer_rate;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_dealer);


        mdealer_id   = getIntent().getExtras().getString("mdealer_id");
        mdealer_date   = getIntent().getExtras().getString("mdealer_date");
        mdealer_address   = getIntent().getExtras().getString("mdealer_address");
        mdealer_alphone = getIntent().getExtras().getString("mdealer_alphone");
        mdealer_gstin = getIntent().getExtras().getString("mdealer_gstin");
        mdealer_name = getIntent().getExtras().getString("mdealer_name");
        mdealer_rate = getIntent().getExtras().getString("mdealer_rate");
        mdealer_primary_phone = getIntent().getExtras().getString("mdealer_primary_phone");
        mdealer_shop_name = getIntent().getExtras().getString("mdealer_shop_name");
        mdealer_rating = getIntent().getExtras().getString("mdealer_rating");


        dealer_name = (TextView)findViewById(R.id.mdealer_name);
        dealer_shop_name = (TextView)findViewById(R.id.mdealer_shop_name);
        dealer_address = (TextView)findViewById(R.id.mdealer_address);
        dealer_primary_phone = (TextView)findViewById(R.id.mdealer_primary_phone);
        dealer_alternate_phone = (TextView)findViewById(R.id.mdealer_alternate_phone);
        dealer_gstin = (TextView)findViewById(R.id.mdealer_gstin);
        dealer_rating = (TextView)findViewById(R.id.mdealer_rating);
        dealer_rate = (TextView)findViewById(R.id.mdealer_rate);
        Log.d("mdealer_id",mdealer_name);
        Log.d("mdealer_address",mdealer_address);
        Log.d("mdealer_shop_name",mdealer_shop_name);
        Log.d("mdealer_rate",mdealer_rate);

        dealer_name.setText(mdealer_name);
        dealer_address.setText(mdealer_address);
        dealer_gstin.setText(mdealer_gstin);
        dealer_shop_name.setText(mdealer_shop_name);
        dealer_rate.setText(mdealer_rate);
        dealer_rating.setText(mdealer_rating);
        dealer_alternate_phone.setText(mdealer_alphone);
        dealer_primary_phone.setText(mdealer_primary_phone);

    }

    public void edit_order(View v)
    {
        Intent i = new Intent(this,Dealer_edit.class);

        i.putExtra("mdealer_id",mdealer_id);

        i.putExtra("mdealer_name",mdealer_name);
        i.putExtra("mdealer_address",mdealer_address);
        i.putExtra("mdealer_primary_phone",mdealer_primary_phone);
        i.putExtra("mdealer_shop_name",mdealer_shop_name);
        i.putExtra("mdealer_alphone",mdealer_alphone);
        i.putExtra("mdealer_rate",mdealer_rate);
        i.putExtra("mdealer_rating",mdealer_rating);
        i.putExtra("mdealer_gstin",mdealer_gstin);

        startActivity(i);


    }


    public void  gotoorder(View v)
    {
        Intent i = new Intent(this,Order_add.class);
        i.putExtra("dealer_primary_phone",mdealer_primary_phone);
        i.putExtra("dealer_name",mdealer_name);
        i.putExtra("dealer_address",mdealer_address);
        i.putExtra("dealer_shop_name",mdealer_shop_name);
        startActivity(i);
        finish();
    }
}
