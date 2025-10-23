package com.advance.chucknorrisjokesapp.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.advance.chucknorrisjokesapp.domain.model.Joke;
import com.advance.chucknorrisjokesapp.domain.repository.JokeRepository;
import com.advance.chucknorrisjokesapp.domain.usecase.GetJokesUseCase;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MainViewModel extends ViewModel {

    private final GetJokesUseCase getJokesUseCase;

    private final MutableLiveData<List<Joke>> _jokes = new MutableLiveData<>();
    public LiveData<List<Joke>> jokes = _jokes;

    private final MutableLiveData<Boolean> _isLoading = new MutableLiveData<>(false);
    public LiveData<Boolean> isLoading = _isLoading;

    private final MutableLiveData<String> _errorMessage = new MutableLiveData<>();
    public LiveData<String> errorMessage = _errorMessage;

    @Inject
    public MainViewModel(GetJokesUseCase getJokesUseCase) {
        this.getJokesUseCase = getJokesUseCase;
    }

    public void searchJokes(String query) {
        _isLoading.postValue(true);

        getJokesUseCase.execute(query, new JokeRepository.JokeCallback() {

            @Override
            public void onSuccess(List<Joke> jokes) {
                _isLoading.postValue(false);
                _jokes.postValue(jokes);
            }

            @Override
            public void onError(String error) {
                _isLoading.postValue(false);
                _errorMessage.postValue(error);
            }
        });
    }
}
