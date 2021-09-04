package com.example.blooddonation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class Dashboard extends AppCompatActivity {
    ImageButton button;
    CardView hospitals;

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        mAuth=FirebaseAuth.getInstance();
        hospitals=findViewById(R.id.card_hospital);
        button=findViewById(R.id.items);
        hospitals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Dashboard.this,Hospital_List.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu,menu);
        return  true;
    }



    private void SendUserToLogin()
    {
        Intent intent=new Intent(Dashboard.this,LoginActivity.class);
        startActivity(intent);
    }

    public void logout(View view) {
        PopupMenu popupMenu = new PopupMenu(Dashboard.this, button);

        // Inflating popup menu from popup_menu.xml file
        popupMenu.getMenuInflater().inflate(R.menu.menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                // Toast message on menu item clicked

                if (menuItem.getItemId()==R.id.settings){

                }
                if (menuItem.getItemId()==R.id.mainlogout){
                    mAuth.signOut();
                    SendUserToLogin();
                    finish();
                }
                return true;
            }
        });
        // Showing the popup menu
        popupMenu.show();
    }
}