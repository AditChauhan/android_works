package com.example.achauhan.marketting_aec;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Order_Full extends AppCompatActivity {
    Order_item place;

    private TextView mdealer_name;
    private TextView mdealer_address;
    private TextView mdate;
    private TextView mShipping_time;
    private TextView mstatus;
    private TextView morder_id;
    private TextView mid;
    private TextView did;
    private TextView mcash;
    private TextView mrate;
    private TextView mcredit;
    private TextView mtotal;
    private TextView m25watt;
    private TextView m50watt;
    private TextView m75watt;
    private TextView m100watt;
    private TextView m150watt;
    private TextView m200watt;
    private TextView m250watt;
    AlertDialog.Builder builderSingle;
    AlertDialog.Builder alert ;

    DatabaseReference dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order__full);

        place= (Order_item) getIntent().getSerializableExtra("order_object");
        Log.d("order_item",place.getOrder_id());
        builderSingle = new AlertDialog.Builder(Order_Full.this);
        builderSingle.setTitle("Select Status");


        dbref = FirebaseDatabase.getInstance().getReference("Orders");
        alert = new AlertDialog.Builder(getApplicationContext());
        mdealer_name = (TextView)findViewById(R.id.mdealer_name);
        mdealer_address = (TextView)findViewById(R.id.mdealer_address);
        mShipping_time = (TextView)findViewById(R.id.mshipment);
        mstatus = (TextView)findViewById(R.id.estatus);
        mcash = (TextView)findViewById(R.id.mcash);
        mrate = (TextView)findViewById(R.id.mrate);
        mcredit = (TextView)findViewById(R.id.mcredit_amount);
        mtotal = (TextView)findViewById(R.id.mtotal);
        m25watt = (TextView)findViewById(R.id.m25watt);
        m50watt = (TextView)findViewById(R.id.m50watt);
        m75watt = (TextView)findViewById(R.id.m75watt);
        m100watt = (TextView)findViewById(R.id.m100watt);
        m150watt = (TextView)findViewById(R.id.m150watt);
        m200watt = (TextView)findViewById(R.id.m200watt);
        m250watt = (TextView)findViewById(R.id.m250watt);



        mdealer_name.setText(place.getDealer_name());
        mdealer_address.setText(place.getDealer_address());

        mShipping_time.setText(place.getShipping_time());
        mstatus.setText(""+place.getStatus());
        mcash.setText(""+place.getCash());
        mrate.setText(""+place.getRate());
        mcredit.setText(""+place.getCredit());
        mtotal.setText(""+place.getTotal());
        m25watt.setText(""+place.getM25watt());
        m50watt.setText(""+place.getM50watt());
        m75watt.setText(""+place.getM75watt());
        m100watt.setText(""+place.getM100watt());
        m150watt.setText(""+place.getM150watt());
        m200watt.setText(""+place.getM200watt());
        m250watt.setText(""+place.getM250watt());

    }



    public void change_status(View v)
    {
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_singlechoice);
        arrayAdapter.add("New order");
        arrayAdapter.add("Lay_up is completed");
        arrayAdapter.add("lamination is completed");
        arrayAdapter.add("Framing is completed");
        arrayAdapter.add("Packaging is completed");
        arrayAdapter.add("En-route is completed");
        arrayAdapter.add("Order is delivered");



        builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String strName = arrayAdapter.getItem(which);

                change_status(which);

            }
        });
        builderSingle.show();


    }


    public void change_status(int values)
    {
            Log.d("status",""+values);

        dbref.child(""+place.getOrder_id()).child("status").setValue(values);

    }


    public void ship_time()
    {
        final EditText edittext = new EditText(this);
        alert.setMessage("Enter Your Message");
        alert.setTitle("Enter Your Title");

        alert.setView(edittext);

        alert.setPositiveButton("Yes Option", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                String YouEditTextValue = edittext.getText().toString();


            }
        });

        alert.setNegativeButton("No Option", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // what ever you want to do with No option.
            }
        });

        alert.show();


    }

}
