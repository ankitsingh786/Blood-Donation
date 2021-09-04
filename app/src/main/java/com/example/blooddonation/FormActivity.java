package com.example.blooddonation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.pm.Signature;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.williamww.silkysignature.views.SignaturePad;

public class FormActivity extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener {

    private SignaturePad mSignaturePad;
    private Button mClearButton;
    AppCompatButton submit;
    // create array of Strings
    // and store name of courses
    String[] courses = { "Select your blood group", "O Positive","O Negative",
            "A Positive","A Negative","B Positive","B Negative",
            "AB Positive", "AB Negative" };

FirebaseAuth mAuth;
FirebaseUser currentuser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        mSignaturePad = (SignaturePad) findViewById(R.id.signature_pad);
        mClearButton=findViewById(R.id.clearsignature) ;
        mAuth=FirebaseAuth.getInstance();
        currentuser=mAuth.getCurrentUser();
        submit=findViewById(R.id.submits);
        Spinner spino = findViewById(R.id.coursesspinner);
        spino.setOnItemSelectedListener(this);



        // Create the instance of ArrayAdapter
        // having the list of courses
        ArrayAdapter ad
                = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                courses);

        ad.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);

        // Set the ArrayAdapter (ad) data on the
        // Spinner which binds data to spinner.
        spino.setPrompt("Select your blood group");
        spino.setAdapter(ad);

        mSignaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {

            }

            @Override
            public void onSigned() {

                mClearButton.setEnabled(true);

            }

            @Override
            public void onClear() {
                mClearButton.setEnabled(false);

            }
        });

        mClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSignaturePad.clear();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FormActivity.this,Dashboard.class);
                startActivity(intent);
            }
        });






    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ((TextView) parent.getChildAt(0)).setTextColor(Color.RED);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }





}