package com.example.blooddonation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupActivity extends AppCompatActivity {
     TextView already;
     Button register;
    EditText singupemail,signuppassword,sinupconfirmpassword;
     String emailPattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
     ProgressDialog progressDialog;
     FirebaseAuth mAuth;
     FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        singupemail=findViewById(R.id.signup_email);
        signuppassword=findViewById(R.id.signup_password);
        sinupconfirmpassword=findViewById(R.id.signup_cpassword);
        already=findViewById(R.id.alreadyaccount);
        register=findViewById(R.id.btn_register);

        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();

        already.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateNewAccount();
            }
        });
    }

    private void CreateNewAccount() {
        String email=singupemail.getText().toString();
        String password=signuppassword.getText().toString();
        String cpass=sinupconfirmpassword.getText().toString();
         if (TextUtils.isEmpty(email)){
            Toast.makeText(SignupActivity.this,"Please enter email..",Toast.LENGTH_SHORT).show();

        }if (TextUtils.isEmpty(password)){
            Toast.makeText(SignupActivity.this,"Please enter password..",Toast.LENGTH_SHORT).show();

        }if (!cpass.matches(password)){
            Toast.makeText(SignupActivity.this,"Password did not match ",Toast.LENGTH_SHORT).show();
        }
         else {

             mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                 @Override
                 public void onComplete(@NonNull Task<AuthResult> task) {

                     if (task.isSuccessful()){
                         mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                             @Override
                             public void onComplete(@NonNull Task<Void> task) {
                                 if (task.isSuccessful()){
                                     Toast.makeText(SignupActivity.this,"Registration Successfully  check your email to verify",Toast.LENGTH_SHORT).show();
                                 }

                             }
                         });

                           sendusertoform();
                           Toast.makeText(SignupActivity.this,"Account Created Successfully ",Toast.LENGTH_SHORT).show();


                               }else {
                                     String message=task.getException().toString();

                                 Toast.makeText(SignupActivity.this,"Error"+message,Toast.LENGTH_SHORT).show();


                               }
                                       }
                               });
                }
    }

    private void sendusertoform() {
        Intent i =new Intent(SignupActivity.this,LoginActivity.class);
        startActivity(i);
    }

    private void sendusertologin() {
        Intent i =new Intent(SignupActivity.this,FormActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
        finish();
    }


}