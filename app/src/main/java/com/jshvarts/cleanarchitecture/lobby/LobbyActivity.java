package com.jshvarts.cleanarchitecture.lobby;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.jshvarts.cleanarchitecture.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Activity is the View component in our MVP.
 */
public class LobbyActivity extends AppCompatActivity implements HasSupportFragmentInjector, LobbyView {

    @Inject
    protected LobbyPresenter presenter;

    @BindView(R.id.display_report)
    protected TextView displayReportTextView;

    @BindView(R.id.loading_indicator)
    protected ProgressBar loadingIndicator;

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lobby_activity);

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
    public void displayReportData(CharSequence report) {
        displayReportTextView.setVisibility(View.VISIBLE);
        displayReportTextView.setText(report);
    }

    @Override
    public void hideReportData() {
        displayReportTextView.setVisibility(View.GONE);
    }

    @Override
    public void displayReportError() {
        Toast.makeText(this, R.string.lobby_report_error_text, Toast.LENGTH_LONG).show();
    }

    @Override
    public void displayLoadingIndicator() {
        loadingIndicator.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingIndicator() {
        loadingIndicator.setVisibility(View.GONE);
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }
}
