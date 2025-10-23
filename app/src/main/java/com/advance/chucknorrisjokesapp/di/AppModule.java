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
import okhttp3.CertificatePinner;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient() {
        CertificatePinner certificatePinner = new CertificatePinner.Builder()
                .add("api.chucknorris.io", BuildConfig.SSL_PIN)
                .build();

        return new OkHttpClient.Builder()
                .certificatePinner(certificatePinner)
                .build();
    }

    @Provides
    @Singleton
    public ChuckNorrisApi provideChuckNorrisApi(OkHttpClient client) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(client)
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
