package com.example.movieslist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    NestedScrollView nestedScrollView;
    RecyclerView recyclerView;
    ProgressBar pb;
    ArrayList<MovieList> movieListArrayList= new ArrayList<>();
    MovieAdapter movieAdapter;
    int page=1, limit=10;
    Service service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nestedScrollView=findViewById(R.id.scrollView);
        recyclerView=findViewById(R.id.recyclerview);
        pb=findViewById(R.id.progressbar);

        movieAdapter= new MovieAdapter(movieListArrayList,MainActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(movieAdapter);

        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY== v.getChildAt(0).getMeasuredHeight()- v.getMeasuredHeight()){
                    page++;
                    pb.setVisibility(View.VISIBLE);
                }
            }
        });

//        Call<MovieVideos> movieVideosCall = BuildConfig.THE_MOVIE_DB_API_KEY;
//        retrofitService.getMovieDetailsById(id, movieVideosCall).enqueue(new Callback<MovieDetails>() {
//            public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {
//                MovieDetails movieDetailsResponse = (MovieDetails) response.body();
//                if (movieDetailsResponse != null) {
//                    MovieDetailActivity.this.prepareMovieDetails(movieDetailsResponse);
//                }
//            }

        //String movieListCall= BuildConfig.API_KEY;
        service = Service.retrofit.create(Service.class);
        Call<List<MovieList>> call =service.getItems();
        call.enqueue(new Callback<List<MovieList>>() {
            @Override
            public void onResponse(Call<List<MovieList>> call, Response<List<MovieList>> response) {
                if(response.isSuccessful() && response.body()!= null){
                    pb.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<MovieList>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}