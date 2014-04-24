package com.jaynewstrom.networkingExample.naive;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.jaynewstrom.networkingExample.R;
import com.jaynewstrom.networkingExample.app.NetworkingExampleApplication;
import com.jaynewstrom.networkingExample.common.Contributor;
import com.jaynewstrom.networkingExample.common.GitHubService;
import com.jaynewstrom.networkingExample.proper.ProperActivity;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class NaiveActivity extends Activity implements Callback<List<Contributor>> {

    @InjectView(R.id.contributors) TextView contributorsTextView;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        GitHubService gitHubService = NetworkingExampleApplication.getRestAdapter().create(GitHubService.class);
        gitHubService.contributors("square", "dagger", this);
    }

    @Override public void success(List<Contributor> contributors, Response response) {
        StringBuilder sb = new StringBuilder();
        for (Contributor contributor : contributors) {
            sb.append(contributor.login).append(": ").append(contributor.contributions).append("\n");
        }
        contributorsTextView.setText(sb.toString());
    }

    @Override public void failure(RetrofitError error) {
        contributorsTextView.setText(R.string.error_loading_contributors);
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.naive_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.go_to_proper:
                startActivity(new Intent(this, ProperActivity.class));
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
