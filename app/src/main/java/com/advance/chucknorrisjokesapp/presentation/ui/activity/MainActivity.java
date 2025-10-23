package com.advance.chucknorrisjokesapp.presentation.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.advance.chucknorrisjokesapp.databinding.ActivityMainBinding;
import com.advance.chucknorrisjokesapp.presentation.ui.adapter.JokeListAdapter;
import com.advance.chucknorrisjokesapp.presentation.viewmodel.MainViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private JokeListAdapter jokeAdapter;
    private MainViewModel viewModel;

    private final Handler searchHandler = new Handler(Looper.getMainLooper());
    private static final long SEARCH_DELAY = 600;
    private String lastQuery = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupViewBinding();
        setupEdgeToEdge();
        setupRecyclerView();
        setupViewModel();
        observeViewModel();
        setupSearchListener();

        String defaultQuery = "funny";
        viewModel.searchJokes(defaultQuery);
    }

    private void setupSearchListener() {
        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchHandler.removeCallbacksAndMessages(null);

                String query = s.toString().trim();

                if (query.isEmpty()) {
                    jokeAdapter.setData(null);
                    return;
                }

                searchHandler.postDelayed(() -> {
                    if (!query.equals(lastQuery)) {
                        lastQuery = query;
                        viewModel.searchJokes(query);
                    }
                }, SEARCH_DELAY);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void observeViewModel() {
        viewModel.jokes.observe(this, jokes -> {
            jokeAdapter.setData(jokes);
        });

        viewModel.isLoading.observe(this, isLoading -> {
            binding.progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
        });

        viewModel.errorMessage.observe(this, error -> {
            if (error != null && !error.isEmpty()) {
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupViewModel() {
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
    }

    private void setupViewBinding() {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void setupEdgeToEdge() {
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void setupRecyclerView() {
        jokeAdapter = new JokeListAdapter(this);
        binding.rvSearchResult.setLayoutManager(new LinearLayoutManager(this));
        binding.rvSearchResult.setAdapter(jokeAdapter);
    }
}
