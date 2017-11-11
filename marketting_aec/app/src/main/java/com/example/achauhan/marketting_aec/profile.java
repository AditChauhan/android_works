package com.example.achauhan.marketting_aec;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import butterknife.BindView;

public class profile extends AppCompatActivity {


    @BindView(R.id.ename)
    EditText ename;
    @BindView(R.id.ephone_number)
    EditText ephone_number;
    @BindView(R.id.eAdress)
    EditText eadress;
    @BindView(R.id.ealternate_phone_number)
    EditText ealternate_phone_number;
    @BindView(R.id.ejoining_date)
    EditText ejoining_date;
    @BindView(R.id.evehicle_number)
    EditText evehicle_number;
    @BindView(R.id.bsubmit)
    Button bsubmit;
    private FirebaseAuth fbAuth;

    private static final String TAG = "PhoneAuth";

    String mname,maddress,mjoining_date,mvehicle_number, mphone_number,malternate_phone_number;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.marketting_profile);
        fbAuth = FirebaseAuth.getInstance();
        ename = (EditText)findViewById(R.id.ename);
        ephone_number = (EditText)findViewById(R.id.ephone_number);
        eadress= (EditText)findViewById(R.id.eAdress);
        ealternate_phone_number= (EditText)findViewById(R.id.ealternate_phone_number);
        ejoining_date= (EditText)findViewById(R.id.ejoining_date);
        evehicle_number= (EditText)findViewById(R.id.evehicle_number);


    }





    public void do_submit(View view)
    {
        mname = ename.getText().toString();
        mphone_number = "+91"+ephone_number.getText().toString();
        maddress = eadress.getText().toString();
        malternate_phone_number = ealternate_phone_number.getText().toString();
        mjoining_date = ejoining_date.getText().toString();
        mvehicle_number = evehicle_number.getText().toString();
        validate_entrynumber();

    }

    private void jjj() {

        Toast.makeText(this,"Phone number already registered",Toast.LENGTH_LONG).show();

    }

    private void send_details() {



        Intent i = new Intent(this, Otp_Verification.class);
        i.putExtra("input",0);
        i.putExtra("phone_number", mphone_number);
        i.putExtra("name", mname);
        i.putExtra("address", maddress);
        i.putExtra("alternate_phone_number", malternate_phone_number);
        i.putExtra("joining_date", mjoining_date);
        i.putExtra("vehicle_number", mvehicle_number);
        startActivity(i);
        finish();
    }


    public void validate_entrynumber() {


        DatabaseReference root = FirebaseDatabase.getInstance().getReference();
        DatabaseReference musers = root.child("phone_numbers");

        Log.d(TAG, musers.toString());

        Log.d(TAG, "yes");

        if (mname.isEmpty() || (mphone_number.isEmpty()) || maddress.isEmpty() || (malternate_phone_number.isEmpty())
                || mjoining_date.isEmpty() || mvehicle_number.isEmpty()) {
            display_partial();

            Log.d(TAG, "partial");

        } else {
            Log.d(TAG, "notthing is empty");
            musers.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    for (DataSnapshot data : snapshot.getChildren()) {
                        if (data.child("phone_number").getValue().equals(mphone_number)) {
                                validate_duplicacy();
                        } else {

                            not_authorised();

                        }
                    }
                }


                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
    }


    public void validate_duplicacy()
    {
        DatabaseReference root = FirebaseDatabase.getInstance().getReference();
        DatabaseReference musers = root.child("marketting_profile");

        Log.d(TAG,musers.toString());

        musers.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if(snapshot.getChildrenCount() >0) {
                        for (DataSnapshot data : snapshot.getChildren()) {
                            if (data.child("mphone_number").getValue().equals(mphone_number)) {
                                Log.d(TAG, " " + data.child("mname").getValue().toString());
                                Log.d(TAG, "no. of times" + data.child("mphone_number").getValue());
                                already_exist();
                                break;
                            }
                        }
                        send_details();
                    }
                    else
                    {
                        Log.d(TAG, "snapshot child count is zero");
                        send_details();
                    }

                }




                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.d(TAG,""+"onCancelled");

                }
            });

        }


    public  void  already_exist()
    {
        Toast.makeText(this,"you have already registered",Toast.LENGTH_LONG).show();
        Intent i = new Intent(this,Options.class);
        startActivity(i);


    }

    public  void  not_authorised()
    {
        Toast.makeText(this,"you are not authorised to login",Toast.LENGTH_LONG).show();

    }

    public void display_partial()
    {
        Toast.makeText(getApplicationContext(),"please completely fill the form before submitting",Toast.LENGTH_LONG).show();
    }


































//
//    public void work()
//    {
//        if(fbAuth.getCurrentUser() != null)
//        {
//            Intent i = new Intent(profile.this, Marketting_Homepage.class);
//            startActivity(i);
//        }
//        else
//        {
//            DatabaseReference root = FirebaseDatabase.getInstance().getReference();
//            DatabaseReference users = root.child("marketting_profile");
//            Log.d("adit users : ", users.toString());
//
//
//            users.addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot snapshot) {
//                    for (DataSnapshot data : snapshot.getChildren()) {
//                        if (data.child("mphone_number").getValue().equals("8197460703"))
//                        {
//                            Log.d("child : ", "no. of times" + data.child("mphone_number").getValue());
//                            Intent i = new Intent(profile.this, Otp_Verification.class);
//                            startActivity(i);
//                        }
//                        else
//                        {
//
//                        }
//                    }
//                }
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//
//                }
//            });
//        }
//    }
}
