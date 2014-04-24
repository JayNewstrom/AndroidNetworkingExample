package com.jaynewstrom.networkingExample.common;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

public interface GitHubService {
    @GET("/repos/{owner}/{repo}/contributors") void contributors(
            @Path("owner") String owner,
            @Path("repo") String repo,
            Callback<List<Contributor>> callback
    );
}
