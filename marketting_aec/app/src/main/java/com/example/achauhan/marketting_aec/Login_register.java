package com.example.achauhan.marketting_aec;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login_register extends AppCompatActivity {


    private String phoneVerificationId;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            verificationCallbacks;
    private PhoneAuthProvider.ForceResendingToken resendToken;
    private FirebaseAuth fbAuth;
    private DatabaseReference mDatabase;
    LinearLayout phone_layout, code_layout;
    EditText phone_number;
    DatabaseReference users ;
    String mphone_number;

    private static final String TAG = "PhoneAuth";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginregister);


         fbAuth = FirebaseAuth.getInstance();
         phone_number = (EditText) findViewById(R.id.ephone_number);
         DatabaseReference root = FirebaseDatabase.getInstance().getReference();
         users = root.child("marketting_profile");
    }

    public void get_otp(View v)
    {

        mphone_number = "+91"+phone_number.getText().toString();
        if(!(mphone_number.isEmpty())) {
            Log.d(TAG,mphone_number);
            check();
        }
        else
        {
            Toast.makeText(this,"please enter phone number",Toast.LENGTH_LONG).show();
        }


    }

    private void check() {


        Log.d(TAG,"inside check");
        Log.d(TAG,users.toString());



        users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()) {
                    if (data.child("mphone_number").getValue().equals(mphone_number))
                    {
                        Log.d(TAG,"loop registered or not");
                        Intent i = new Intent(Login_register.this,Otp_Verification.class);
                        i.putExtra("input",1);
                        i.putExtra("phone_number", mphone_number);
                        startActivity(i);
                        finish();
                        break;
                    }
                }
                invalid();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    public void invalid()
    {
        Toast.makeText(this,"Phone number is not registered",Toast.LENGTH_LONG).show();



    }


}
