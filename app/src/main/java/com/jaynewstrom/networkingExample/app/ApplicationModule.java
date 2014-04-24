package com.jaynewstrom.networkingExample.app;

import com.jaynewstrom.networkingExample.proper.ProperActivity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;

@Module(
        injects = {
                NetworkingExampleApplication.class,
                ProperActivity.class
        }
)
public class ApplicationModule {

    @Provides @Singleton @SuppressWarnings("unused")
    RestAdapter provideRestAdapter() {
        return new RestAdapter.Builder()
                .setEndpoint("https://api.github.com")
                .setClient(new OkHttpClientWrapper())
                .build();
    }
}
