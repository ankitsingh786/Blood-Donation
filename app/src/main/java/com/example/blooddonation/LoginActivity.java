package com.example.blooddonation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    EditText email, password;
    Button login, signup;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=findViewById(R.id.loginemail);
        mAuth=FirebaseAuth.getInstance();

        password=findViewById(R.id.loginpassword);
        login=findViewById(R.id.login);
        signup=findViewById(R.id.signup);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               AllowUserToLogin();
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(i);
            }
        });
    }

    private void AllowUserToLogin() {
        String loginemail= email.getText().toString();
        String lpassword=password.getText().toString();

        if (TextUtils.isEmpty(loginemail)){
            Toast.makeText(LoginActivity.this,"Please enter email..",Toast.LENGTH_SHORT).show();

        }if (TextUtils.isEmpty(lpassword)){
            Toast.makeText(LoginActivity.this,"Please enter password..",Toast.LENGTH_SHORT).show();

        }
        else {
            mAuth.signInWithEmailAndPassword(loginemail,lpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        if (mAuth.getCurrentUser().isEmailVerified()){
                            SendToForm();
                            Toast.makeText(LoginActivity.this,"Login Successfully ",Toast.LENGTH_SHORT).show();

                        }else {
                            Toast.makeText(LoginActivity.this,"Please verify first ",Toast.LENGTH_SHORT).show();

                        }


                    }else {
                        String message=task.getException().toString();

                        Toast.makeText(LoginActivity.this,"Error"+message,Toast.LENGTH_SHORT).show();


                    }

                }
            });
    }
   }


    private void SendToForm() {
        Intent i =new Intent(LoginActivity.this,FormActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
        finish();
    }

}