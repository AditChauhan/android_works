package com.example.achauhan.marketting_aec;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Marketting_Homepage extends AppCompatActivity {

    @BindView(R.id.check)
    TextView check1;
    RatingBar potential;
    private FirebaseAuth fbAuth;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marketting_homepage);
        ButterKnife.bind(this);
        fbAuth = FirebaseAuth.getInstance();
        String uphone = fbAuth.getCurrentUser().getPhoneNumber();
        Log.d("user phone_number",uphone);
    }


    public void addDealers(View v )
    {
        Intent  i  = new Intent(this,Add_Dealer.class );
        startActivity(i);




    }

    public void viewDealers(View v )
    {
        Intent  i  = new Intent(this,View_dealers.class );
        startActivity(i);




    }

    public void addOrders(View v )
    {
        Intent  i  = new Intent(this,Add_Dealer.class );
        startActivity(i);




    }

    public void viewOrders(View v )
    {
        Intent  i  = new Intent(this,View_Orders.class );
        startActivity(i);




    }


    public void signout(View v) {
        fbAuth.signOut();
        Intent i = new Intent(this,Options.class);
        startActivity(i);
    }





    }
