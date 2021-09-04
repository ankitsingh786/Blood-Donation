package com.example.blooddonation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Hospital_List extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Hospital> hospital;
    private static String JSON_URL = "https://blood-donation-healthcare.000webhostapp.com/image";
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_list);
        recyclerView = findViewById(R.id.recycler_hospital_list);
        hospital = new ArrayList<>();
        extractSongs();
    }

    private void extractSongs() {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject songObject = response.getJSONObject(i);

                        Hospital song = new Hospital();
                        song.setName(songObject.getString("name").toString());
                        song.setAddress(songObject.getString("address".toString()));
                        song.setPincode(songObject.getString("pincode".toString()));
                        song.setPhone(songObject.getString("phone".toString()));
                        song.setFax(songObject.getString("fax".toString()));
                        song.setImage(songObject.getString("image"));

                        hospital.add(song);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adapter = new Adapter(getApplicationContext(),hospital);
                recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag", "onErrorResponse: " + error.getMessage());
            }
        });

        queue.add(jsonArrayRequest);

    }

    public void gethome(View view) {
        Intent i=new Intent(Hospital_List.this,Dashboard.class);
        startActivity(i);
    }
}
