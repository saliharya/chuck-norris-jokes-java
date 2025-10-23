package com.advance.chucknorrisjokesapp.data.repository;

import com.advance.chucknorrisjokesapp.data.model.JokeResponse;
import com.advance.chucknorrisjokesapp.data.remote.ChuckNorrisApi;
import com.advance.chucknorrisjokesapp.domain.model.Joke;
import com.advance.chucknorrisjokesapp.domain.repository.JokeRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JokeRepositoryImpl implements JokeRepository {
    private final ChuckNorrisApi api;

    @Inject
    public JokeRepositoryImpl(ChuckNorrisApi api) {
        this.api = api;
    }

    @Override
    public void searchJokes(String query, JokeCallback callback) {
        api.searchJokes(query).enqueue(new Callback<JokeResponse>() {
            @Override
            public void onResponse(Call<JokeResponse> call, Response<JokeResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Joke> jokes = new ArrayList<>();
                    for (JokeResponse.JokeDto dto : response.body().getResult()) {
                        jokes.add(new Joke(dto.getId(), dto.getValue(), dto.getIconUrl()));
                    }
                    callback.onSuccess(jokes);
                } else {
                    callback.onError("API Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<JokeResponse> call, Throwable t) {
                t.printStackTrace();
                callback.onError(t.getMessage());
            }
        });
    }
}
