package com.jaynewstrom.networkingExample.app;

import android.os.SystemClock;

import com.jaynewstrom.networkingExample.BuildConfig;
import com.squareup.okhttp.OkHttpClient;

import java.io.IOException;
import java.util.Random;

import retrofit.client.Client;
import retrofit.client.OkClient;
import retrofit.client.Request;
import retrofit.client.Response;

public class OkHttpClientWrapper implements Client {

    private final Client wrapped;
    private static final Random RANDOM = new Random();

    public OkHttpClientWrapper() {
        OkHttpClient client = new OkHttpClient();
        wrapped = new OkClient(client);
    }

    @Override public Response execute(Request request) throws IOException {
        if (BuildConfig.DEBUG) {
            // sleep between .5 - 1.5 seconds in debug builds. GitHub is too fast.
            SystemClock.sleep(randomInt(500, 1500));
        }

        return wrapped.execute(request);
    }

    private static int randomInt(int min, int max) {
        // nextInt is normally exclusive of the top value, so add 1 to make it inclusive
        return RANDOM.nextInt((max - min) + 1) + min;
    }
}

