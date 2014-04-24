package com.jaynewstrom.networkingExample.proper;

import com.jaynewstrom.networkingExample.common.Contributor;
import com.jaynewstrom.networkingExample.common.GitHubService;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import timber.log.Timber;

@Singleton
public class Controller implements Callback<List<Contributor>> {

    private ControllerActivity controllerActivity;
    private List<Contributor> contributors;

    @Inject Controller(RestAdapter restAdapter) {
        GitHubService gitHubService = restAdapter.create(GitHubService.class);
        gitHubService.contributors("square", "dagger", this);
    }

    void takeActivity(ControllerActivity activity) {
        controllerActivity = activity;
        updateActivityState();
    }

    void dropActivity(ControllerActivity activity) {
        if (controllerActivity == activity) {
            controllerActivity = null;
        }
    }

    @Override public void success(List<Contributor> contributors, Response response) {
        this.contributors = contributors;
        updateActivityState();
    }

    @Override public void failure(RetrofitError error) {
        Timber.e("Failed to load contributors", error.getCause());
    }

    private void updateActivityState() {
        if (contributors != null) {
            controllerActivity.setContributors(contributors);
        }
    }
}
