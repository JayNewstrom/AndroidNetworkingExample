package com.jaynewstrom.networkingExample.proper;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.jaynewstrom.networkingExample.R;
import com.jaynewstrom.networkingExample.app.NetworkingExampleApplication;
import com.jaynewstrom.networkingExample.common.Contributor;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ProperActivity extends Activity implements ControllerActivity {

    @InjectView(R.id.contributors) TextView contributorsTextView;

    @Inject Controller controller;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        NetworkingExampleApplication.inject(this);
        controller.takeActivity(this);
    }

    @Override protected void onDestroy() {
        controller.dropActivity(this);
        super.onDestroy();
    }

    @Override public void setContributors(List<Contributor> contributors) {
        StringBuilder sb = new StringBuilder();
        for (Contributor contributor : contributors) {
            sb.append(contributor.login).append(": ").append(contributor.contributions).append("\n");
        }
        contributorsTextView.setText(sb.toString());
    }
}
