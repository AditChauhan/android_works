package com.example.achauhan.marketting_aec;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Order_add extends AppCompatActivity {


    DatabaseReference fbdb;
    DatabaseReference mdrefer;
    TextView  ttotal_rate;
    EditText rate;
    EditText e25watt;
    EditText e50watt;
    EditText e75watt;
    EditText e100watt;
    EditText e200watt;
    EditText e150watt;
    EditText e250watt;

    String m25;
    String m50;
    String m75 ;
    String m100;
    String m150;
    String m200;
    String m250;


    String date;
    String mmid;
    String mdid;
    String dealer_name;
    String dealer_address;
    String dealer_shop_name;
    int cash = 0;
    int credit = 0 ;
    int mstatus=0;
    int mtotal_rate=0;
    int mrate=0;
    int m25watt=0;
    int m50watt=0;
    int m75watt=0;
    int m100watt=0;
    int m150watt=0;
    int m200watt=0;
    int m250watt=0;
    boolean rate_empty = true;



    private static final String TAG = "PhoneAuth";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__order);


        rate = (EditText)findViewById(R.id.erate);
        e25watt = (EditText)findViewById(R.id.e25watt);
        e50watt = (EditText)findViewById(R.id.e50watt);
        e75watt = (EditText)findViewById(R.id.e75watt);
        e100watt = (EditText)findViewById(R.id.e100watt);
        e200watt = (EditText)findViewById(R.id.e200watt);
        e250watt = (EditText)findViewById(R.id.e250watt);
        e150watt = (EditText)findViewById(R.id.e150watt);
        ttotal_rate = (TextView)findViewById(R.id.total);

        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
        date = df.format(Calendar.getInstance().getTime());

        mmid = FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber();
        mdid = getIntent().getExtras().getString("dealer_primary_phone");
        dealer_name = getIntent().getExtras().getString("dealer_name");
        dealer_address  = getIntent().getExtras().getString("dealer_address");
        fbdb  = FirebaseDatabase.getInstance().getReference().child("Orders");
        mdrefer = FirebaseDatabase.getInstance().getReference().child("Dealers");
        Log.d(TAG," "+dealer_name);
        Log.d(TAG," "+dealer_address);
        Log.d(TAG," "+dealer_shop_name);

        rate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s != null && s.length() > 0 )
                {
                    rate_empty = false;
                    Log.d(TAG,"not rate_empty");

                }
                else
                {
                    Log.d(TAG,"rate_empty");
                    rate.requestFocus();
                    rate.setError("Cannot be empty");


                }
            }
        });


        e25watt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s != null && s.length() > 0 )
                {
                    m25watt = Integer.parseInt(e25watt.getText().toString());

                    do_total();
                }
                else
                {
                    Log.d(TAG,"rate_empty");
                    rate.requestFocus();

                }
            }
        });


        e50watt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s != null && s.length() > 0 )
                {
                    m50watt = Integer.parseInt(e50watt.getText().toString());

                    do_total();

                }
                else
                {
                    Log.d(TAG,"rate_empty");
                    rate.requestFocus();

                }
            }
        });

        e75watt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s != null && s.length() > 0 )
                {

                    m75watt = Integer.parseInt(e75watt.getText().toString());

                    do_total();

                }
                else
                {
                    Log.d(TAG,"rate_empty");
                    rate.requestFocus();

                }
            }
        });

        e100watt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s != null && s.length() > 0 )
                {
                    m100watt = Integer.parseInt(e100watt.getText().toString());
                    do_total();

                }
                else
                {
                    Log.d(TAG,"rate_empty");
                    rate.requestFocus();

                }
            }
        });

        e150watt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s != null && s.length() > 0 )
                {
                    m150watt = Integer.parseInt(e150watt.getText().toString());

                    do_total();

                }
                else
                {
                    Log.d(TAG,"rate_empty");
                    rate.requestFocus();

                }
            }
        });

        e200watt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s != null && s.length() > 0 )
                {
                    m200watt = Integer.parseInt(e200watt.getText().toString());

                    do_total();

                }
                else
                {
                    Log.d(TAG,"rate_empty");
                    rate.requestFocus();

                }
            }
        });


        e250watt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s != null && s.length() > 0 )
                {
                    m250watt = Integer.parseInt(e250watt.getText().toString());

                    do_total();

                }
                else
                {
                    Log.d(TAG,"rate_empty");
                    rate.requestFocus();

                }
            }
        });



    }


    public void get_Order(View v)
    {
        if(rate_empty)
        {
            Toast.makeText(this,"Rate is empty,please fill it",Toast.LENGTH_SHORT).show();
            rate.requestFocus();
        }
        else {
            m25 = e25watt.getText().toString();
            m50 = e50watt.getText().toString();
            m75 = e75watt.getText().toString();
            m100 = e100watt.getText().toString();
            m150 = e150watt.getText().toString();
            m200 = e200watt.getText().toString();
            m250 = e250watt.getText().toString();

            if (e25watt.getText().toString().matches("")) {
                Log.d(TAG, "25watt is null");
                m25watt = Integer.parseInt(e25watt.getText().toString() + 0);
            } else {
                Log.d(TAG, "watt is not null");
                m25watt = Integer.parseInt(e25watt.getText().toString());
            }
            if (e50watt.getText().toString().matches("")) {
                Log.d(TAG, "50watt is null");
                m50watt = Integer.parseInt(e50watt.getText().toString() + 0);
            } else {
                Log.d(TAG, "watt is not null");

                m50watt = Integer.parseInt(e50watt.getText().toString());
            }
            if (e75watt.getText().toString().matches("")) {
                Log.d(TAG, "75watt is null");
                m75watt = Integer.parseInt(e75watt.getText().toString() + 0);
            } else {
                Log.d(TAG, "watt is not null");

                m75watt = Integer.parseInt(e75watt.getText().toString());

            }
            if (e100watt.getText().toString().matches("")) {
                Log.d(TAG, "50watt is null");
                m100watt = Integer.parseInt(e100watt.getText().toString() + 0);
            } else {
                Log.d(TAG, "watt is not null");

                m100watt = Integer.parseInt(e100watt.getText().toString());
            }

            if (e150watt.getText().toString().matches("")) {
                Log.d(TAG, "50watt is null");
                m150watt = Integer.parseInt(e150watt.getText().toString() + 0);


            } else {
                Log.d(TAG, "watt is not null");

                m150watt = Integer.parseInt(e150watt.getText().toString());

            }
            if (e200watt.getText().toString().matches("")) {
                Log.d(TAG, "50watt is null");
                m200watt = Integer.parseInt(e200watt.getText().toString() + 0);


            } else {
                Log.d(TAG, "watt is not null");

                m200watt = Integer.parseInt(e200watt.getText().toString());

            }
            if (e250watt.getText().toString().matches("")) {
                Log.d(TAG, "50watt is null");
                m250watt = Integer.parseInt(e250watt.getText().toString() + 0);

            }
            else
                {
                Log.d(TAG, "watt is not null");
                m250watt = Integer.parseInt(e250watt.getText().toString());
            }
        }
        do_total();
        save_data();

    }


    public void do_total()
    {

        if (!rate.getText().toString().isEmpty() || !rate.getText().toString().matches(""))
        {
            mrate = Integer.parseInt(rate.getText().toString());
            Log.d("watt", "" + m25watt + " " + m50watt + " " + m75watt + " " + m100watt + " " + m150watt + " " + m200watt + " " + m250watt);
            mtotal_rate = mrate * (m25watt * 25 + m50watt * 50 + m75watt * 75 + m100watt * 100 + m150watt * 150 + m200watt * 200 + m250watt * 250);
            ttotal_rate.setText(mtotal_rate + "");
        }
        else
        {
            rate.setError("cannot be empty");
            Toast.makeText(this, "rate is empty", Toast.LENGTH_LONG).show();
        }
    }


    public void save_data()
    {
        Order_item oi = new Order_item();
        String mm = fbdb.push().getKey();
        oi.setDealer_name(dealer_name);
        oi.setDealer_address(dealer_address);
        oi.setDate(date);
        oi.setShipping_time("");
        oi.setStatus(mstatus);
        oi.setOrder_id(mm);
        oi.setMid(mmid);
        oi.setDid(mdid);
        oi.setRate(mrate);
        oi.setCash(cash);
        oi.setCredit(credit);
        oi.setTotal(mtotal_rate);
        oi.setM25watt(m25watt);
        oi.setM50watt(m50watt);
        oi.setM75watt(m75watt);
        oi.setM100watt(m100watt);
        oi.setM150watt(m150watt);
        oi.setM200watt(m200watt);
        oi.setM250watt(m250watt);

        fbdb.child(mm).setValue(oi);
        Intent i  =  new Intent(this,Marketting_Homepage.class);
        startActivity(i);
        Toast.makeText(this, "order is saved", Toast.LENGTH_LONG).show();
        Log.d("phoneAuth","saved");
        finish();
    }

}

