package com.advance.chucknorrisjokesapp.domain.repository;

import com.advance.chucknorrisjokesapp.domain.model.Joke;

import java.util.List;

public interface JokeRepository {
    interface JokeCallback {
        void onSuccess(List<Joke> jokes);

        void onError(String message);
    }

    void searchJokes(String query, JokeCallback callback);
}
