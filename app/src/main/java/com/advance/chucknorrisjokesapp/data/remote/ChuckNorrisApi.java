package com.advance.chucknorrisjokesapp.data.remote;

import com.advance.chucknorrisjokesapp.data.model.JokeResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ChuckNorrisApi {
    @GET("jokes/search")
    Call<JokeResponse> searchJokes(@Query("query") String query);
}
