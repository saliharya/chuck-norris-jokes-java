package com.advance.chucknorrisjokesapp.di;

import com.advance.chucknorrisjokesapp.BuildConfig;
import com.advance.chucknorrisjokesapp.data.remote.ChuckNorrisApi;
import com.advance.chucknorrisjokesapp.data.repository.JokeRepositoryImpl;
import com.advance.chucknorrisjokesapp.domain.repository.JokeRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {
    @Provides
    @Singleton
    public ChuckNorrisApi provideChuckNorrisApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ChuckNorrisApi.class);
    }

    @Provides
    @Singleton
    public JokeRepository provideJokeRepository(ChuckNorrisApi api) {
        return new JokeRepositoryImpl(api);
    }
}
