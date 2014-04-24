package com.jaynewstrom.networkingExample.app;

import android.app.Application;

import com.jaynewstrom.networkingExample.BuildConfig;

import javax.inject.Inject;

import dagger.ObjectGraph;
import retrofit.RestAdapter;
import timber.log.Timber;
import timber.log.Timber.DebugTree;

public class NetworkingExampleApplication extends Application {

    @Inject RestAdapter restAdapter;

    private static NetworkingExampleApplication instance;

    private ObjectGraph objectGraph;

    @Override public void onCreate() {
        super.onCreate();
        instance = this;
        objectGraph = ObjectGraph.create(new ApplicationModule());
        objectGraph.inject(this);

        if (BuildConfig.DEBUG) {
            Timber.plant(new DebugTree());
        }
    }

    public static RestAdapter getRestAdapter() {
        return instance.restAdapter;
    }

    public static void inject(Object object) {
        instance.objectGraph.inject(object);
    }
}
