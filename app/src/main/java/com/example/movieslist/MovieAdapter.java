package com.example.movieslist;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private ArrayList<MovieList> movieListArrayList;
    private Activity activity;

    public MovieAdapter(ArrayList<MovieList> movieListArrayList, Activity activity) {
        this.movieListArrayList = movieListArrayList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MovieList list= movieListArrayList.get(position);
        Glide.with(activity).load(list.getPosterPath()).diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.poster);
        holder.title.setText(list.getTitle());

    }

    @Override
    public int getItemCount() {
        return movieListArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView poster;
        TextView title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            poster=itemView.findViewById(R.id.poster);
            title=itemView.findViewById(R.id.title);
        }
    }
}
