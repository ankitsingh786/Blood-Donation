package com.example.blooddonation;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    LayoutInflater inflater;
    List<Hospital> songs;

    public Adapter(Context ctx, List<Hospital> songs){
        this.inflater = LayoutInflater.from(ctx);
        this.songs = songs;
    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.customlayout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // bind the data
        holder.songTitle.setText(songs.get(position).getName());
        holder.songArtists.setText(songs.get(position).getAddress());
        holder.pincode.setText(songs.get(position).getPincode());
        holder.phone.setText(songs.get(position).getPhone());
        holder.fax.setText(songs.get(position).getFax());
        Picasso.get().load(songs.get(position).getImage()).into(holder.songCoverImage);

    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public  class ViewHolder extends  RecyclerView.ViewHolder{
        TextView songTitle,songArtists,pincode,phone,fax;
        ImageView songCoverImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            songTitle = itemView.findViewById(R.id.hospitalName);
            songArtists = itemView.findViewById(R.id.hospitalAddress);
            pincode = itemView.findViewById(R.id.hospitalPincode);
            phone= itemView.findViewById(R.id.hospitalPhone);
            fax= itemView.findViewById(R.id.hospitalFax);
            songCoverImage = itemView.findViewById(R.id.coverImage);

            // handle onClick

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Do Something With this Click", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}