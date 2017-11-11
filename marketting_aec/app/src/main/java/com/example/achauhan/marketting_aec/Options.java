package com.example.achauhan.marketting_aec;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Options extends AppCompatActivity {
    FirebaseUser fAuth;

    private static final String TAG = "PhoneAuth";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        fAuth = FirebaseAuth.getInstance().getCurrentUser();

        if(fAuth != null)
        {

            Log.d(TAG,"fauth not empty");
            Intent i = new Intent(this,Marketting_Homepage.class);
            startActivity(i);
            finish();
        }

    }

    public  void doLogin(View v)
    {
        Intent i = new Intent(this,Login_register.class);
        startActivity(i);
        finish();

    }

    public  void doSignup(View v)
    {
        Intent i = new Intent(this,profile.class);
        startActivity(i);
        finish();

    }


}
