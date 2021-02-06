package com.example.movieslist;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Service {
    //url=http://api.themoviedb.org/3/movie/popular?api_key=3354c6563712f6717437182b5fa0e039
    String BASE_URL="http://api.themoviedb.org/3/";
    String api_key="movie/popular?api_key=3354c6563712f6717437182b5fa0e039";
    String api="3354c6563712f6717437182b5fa0e039";

    Retrofit retrofit= new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build();

     @GET(api_key)
     Call<List<MovieList>> getItems();
//    @GET(api_key)
//    Call<List<MovieList>> getItems(@Query(api) String str);



}
