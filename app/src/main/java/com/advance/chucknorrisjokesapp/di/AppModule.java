package com.advance.chucknorrisjokesapp.di;

import com.advance.chucknorrisjokesapp.data.remote.ChuckNorrisApi;
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
                .baseUrl("https://api.chucknorris.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ChuckNorrisApi.class);
    }

}
