package com.example.blooddonation;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseUser currentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth=FirebaseAuth.getInstance();
        currentUser=mAuth.getCurrentUser();

        TextView text=(TextView) findViewById(R.id.textblood) ;

        ImageView image = (ImageView) findViewById(R.id.img);
        Animation animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        image.startAnimation(animFadeIn);
        Animation slideAnimation= AnimationUtils.loadAnimation(this, R.anim.slide_slide);
        text.startAnimation(slideAnimation);

        int SPLASH_SCREEN_TIME_OUT = 9000;
        new Handler().postDelayed(() -> {
            if (currentUser!=null){
                if (mAuth.getCurrentUser().isEmailVerified()){
                    SendToDash();
                }else{
                    sendtologin();
                }

            }else{
                SendtoFirst();
            }
            //the current activity will get finished.
        }, SPLASH_SCREEN_TIME_OUT);
    }

    private void sendtologin() {
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom);
        finish();
    }

    private void SendtoFirst() {
        startActivity(new Intent(MainActivity.this, SecondActivity.class));
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom);
        finish();
    }

    private void SendToDash() {
        startActivity(new Intent(MainActivity.this, Dashboard.class));
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom);
        finish();
    }
}