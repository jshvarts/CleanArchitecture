package com.jshvarts.cleanarchitecture.lobby;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.jshvarts.cleanarchitecture.App;
import com.jshvarts.cleanarchitecture.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Activity is the View component in our MVP.
 */
public class LobbyActivity extends AppCompatActivity implements LobbyView {

    @Inject
    protected LobbyPresenter presenter;

    @BindView(R.id.display_report)
    protected TextView displayReportTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lobby_activity);

        App.getAppComponent(this).plus(new LobbyModule())
                .inject(this);

        ButterKnife.bind(this);

        presenter.setView(this);
    }

    @Override
    protected void onDestroy() {
        presenter.onViewDestroyed();
        super.onDestroy();
    }

    @OnClick(R.id.generate_report)
    public void onGenerateReportButtonClicked() {
        presenter.generateReport();
    }

    @Override
    public void onDisplayReportData(CharSequence report) {
        displayReportTextView.setText(report);
    }

    @Override
    public void onDisplayReportError() {
        Toast.makeText(this, R.string.lobby_report_error_text, Toast.LENGTH_LONG).show();
    }
}
