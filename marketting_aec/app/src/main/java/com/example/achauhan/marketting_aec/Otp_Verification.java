package com.example.achauhan.marketting_aec;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * Created by achauhan on 17/10/17.
 */


public class Otp_Verification extends AppCompatActivity {

    private static final String TAG = "PhoneAuth";
    @BindView(R.id.ecode)
    EditText otp;
    @BindView(R.id.bsubmit)
    Button verify;

    String mphone_number,mname,maddress,malternate_phone_number,mjoining_date,mvehicle_number;

    private String phoneVerificationId;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            verificationCallbacks;
    private PhoneAuthProvider.ForceResendingToken resendToken;
    private FirebaseAuth fbAuth;
    private DatabaseReference mDatabase;
    String mcode;
    TextInputLayout Tphone_number;
    int minput;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp_verification);

        Tphone_number = (TextInputLayout)(findViewById(R.id.phone_number));

        fbAuth = FirebaseAuth.getInstance();
        otp = (EditText)findViewById(R.id.ecode);
        verify =(Button)findViewById(R.id.bverify);

        mDatabase = FirebaseDatabase.getInstance().getReference("marketting_profile");
        minput = getIntent().getExtras().getInt("input");

        if(minput == 1)
        {
            mphone_number = getIntent().getExtras().getString("phone_number");
        }

        else
        {
            mphone_number = getIntent().getExtras().getString("phone_number");
            mname = getIntent().getExtras().getString("name");
            maddress = getIntent().getExtras().getString("address");
            malternate_phone_number = getIntent().getExtras().getString("alternate_phone");
            mjoining_date = getIntent().getExtras().getString("joining_date");
            mvehicle_number = getIntent().getExtras().getString("vehicle_number");
            Log.d(TAG, mphone_number);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        sendCode(mphone_number);
        Log.d(TAG,"adit : "+mphone_number);
    }

    public void verify(View v){
        mcode  = otp.getText().toString();
        if(mcode.isEmpty())
        {
                Toast.makeText(this,"please enter valid code",Toast.LENGTH_LONG).show();
        }
        else
        {
            if(!mphone_number.isEmpty()) {
                verifyCode();
            }
        }

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
        PhoneAuthCredential credential =
                PhoneAuthProvider.getCredential(phoneVerificationId, mcode);
        signInWithPhoneAuthCredential(credential);
    }


    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        fbAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");

                            if(minput == 1)
                            {
                                Toast.makeText(getApplicationContext(),"succesfully logged in ",Toast.LENGTH_LONG).show();
                                Intent i = new Intent(Otp_Verification.this,Marketting_Homepage.class);
                                startActivity(i);
                                finish();

                            }
                            else {

                                Toast.makeText(getApplicationContext(), "succesfully logged in ", Toast.LENGTH_LONG).show();
                                save_profile();

                                Intent i = new Intent(Otp_Verification.this,Marketting_Homepage.class);
                                startActivity(i);
                                finish();
                            }

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


    public void resendCode(View view) {
        setUpVerificatonCallbacks();
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                mphone_number,
                60,
                TimeUnit.SECONDS,
                this,
                verificationCallbacks,
                resendToken);
    }


private void setUpVerificatonCallbacks() {

        verificationCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential)
            {

                Log.w(TAG,"onVerificationCompleted");

                signInWithPhoneAuthCredential(credential);
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


    public void save_profile()
    {

        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy hh-mm-ss");
        String formattedDate = df.format(c.getTime());
        System.out.print("date to be printed : "+formattedDate);


        Profile_pojo pp = new Profile_pojo(formattedDate,mname,maddress,mjoining_date,mvehicle_number,mphone_number,malternate_phone_number);
        String userId = mDatabase.push().getKey();
        mDatabase.child(userId).setValue(pp);




    }
}
