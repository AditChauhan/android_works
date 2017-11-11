package com.example.achauhan.marketting_aec;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class Dealer_otp extends AppCompatActivity {

    String mdate,mdealer_name,mdealer_shop_name,mdealer_address,mdealer_primary_phone, mdealer_alternate_phone,mdealer_rate,mdealer_gstin,mdealer_rating;

    private String phoneVerificationId;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            verificationCallbacks;
    private PhoneAuthProvider.ForceResendingToken resendToken;
    private FirebaseAuth fbAuth;
    private DatabaseReference mDatabase;
    DatabaseReference musers;
    EditText ed ;
    String mcode;
    private static final String TAG = "PhoneAuth";
    String uphone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dealer_otp);

        fbAuth =  FirebaseAuth.getInstance();


        uphone =  fbAuth.getCurrentUser().getPhoneNumber();


        DatabaseReference root = FirebaseDatabase.getInstance().getReference();
        musers = root.child("Dealers");

        ed = (EditText)findViewById(R.id.eotp);
        mdate = getIntent().getExtras().getString("mdate");
        mdealer_name = getIntent().getExtras().getString("mdealer_name");
        mdealer_shop_name = getIntent().getExtras().getString("mdealer_shop_name");
        mdealer_address = getIntent().getExtras().getString("mdealer_address");
        mdealer_primary_phone = getIntent().getExtras().getString("mdealer_primary_phone");
        mdealer_alternate_phone = getIntent().getExtras().getString("mdealer_alternate_phone");
        mdealer_gstin = getIntent().getExtras().getString("mdealer_gstin");
        mdealer_rating = getIntent().getExtras().getString("mdealer_rating");
        mdealer_rate = getIntent().getExtras().getString("mdealer_rate");




    }

    @Override
    protected void onResume() {
        super.onResume();
        sendCode(mdealer_primary_phone);
    }

    public void verify(View v)
    {

         mcode =  ed.getText().toString();
        verifyCode();
    }

    public void sendCode(String vphone_number)
    {
        Log.d(TAG,"sendcode");
        setUpVerificatonCallbacks();
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                vphone_number,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                verificationCallbacks);
    }

    public void verifyCode() {

        Log.d(TAG,"verifyCode");
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(phoneVerificationId, mcode);
        signInWithPhoneAuthCredential(credential);

    }

    public void resendCode(View view) {
        setUpVerificatonCallbacks();
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                mdealer_primary_phone,
                60,
                TimeUnit.SECONDS,
                this,
                verificationCallbacks,
                resendToken);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        fbAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");

                            save_Dealer();




                        }
                        else
                        {
                            // Sign in failed, display a message and update the UI
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }
                        }
                    }
                });
    }

    private void setUpVerificatonCallbacks() {

        verificationCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential)
            {

                Log.d(TAG,"onVerificationCompleted");
                save_Dealer();



            }




            @Override
            public void onVerificationFailed(FirebaseException e) {

                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                    Log.d(TAG, "Invalid credential: "
                            + e.getLocalizedMessage());
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    // SMS quota exceeded
                    Log.d(TAG, "SMS Quota exceeded.");
                }
            }




            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {

                phoneVerificationId = verificationId;
                resendToken = token;
            }
        };
    }


    public void save_Dealer()
    {
        String userId = musers.push().getKey();
        Dealer_object od =  new Dealer_object();

        od.setDealer_date(mdate);
        od.setDealer_id(userId);
        od.setMid(uphone);
        od.setDealer_name(mdealer_name);
        od.setDealer_shop_name(mdealer_shop_name);
        od.setDealer_address(mdealer_address);
        od.setDealer_primary_phone(mdealer_primary_phone);
        od.setDealer_alternate_phone(mdealer_alternate_phone);
        od.setDealer_gstin(mdealer_gstin);
        od.setDealer_rating(mdealer_rating);
        od.setDealer_rate(mdealer_rate);


        musers.child(userId).setValue(od);

        Toast.makeText(this,"your detailes are saved",Toast.LENGTH_LONG).show();

        Intent i = new Intent(this,Marketting_Homepage.class);
        startActivity(i);
        finish();


    }


}
