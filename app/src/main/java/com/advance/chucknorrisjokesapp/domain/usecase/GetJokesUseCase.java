package com.advance.chucknorrisjokesapp.domain.usecase;

import com.advance.chucknorrisjokesapp.domain.repository.JokeRepository;

import javax.inject.Inject;

public class GetJokesUseCase {
    private final JokeRepository repository;

    @Inject
    public GetJokesUseCase(JokeRepository repository) {
        this.repository = repository;
    }

    public void execute(String query, JokeRepository.JokeCallback callback) {
        repository.searchJokes(query, callback);
    }
}