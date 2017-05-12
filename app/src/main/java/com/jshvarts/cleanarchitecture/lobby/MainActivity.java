package com.jshvarts.cleanarchitecture.lobby;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.jshvarts.cleanarchitecture.App;
import com.jshvarts.cleanarchitecture.R;
import com.jshvarts.cleanarchitecture.repository.Repository;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @Inject
    protected Repository repository;

    @BindView(R.id.display_report)
    protected TextView displayReportTextView;

    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        App.getAppComponent(this).inject(this);

        ButterKnife.bind(this);
    }

    @Override
    protected void onStop() {
        super.onStop();

        disposable.dispose();
    }

    @OnClick(R.id.generate_report)
    protected void generateReport() {
        disposable = repository.getAllItems()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(items -> items.toString())
                .doOnSuccess(this::displayReport)
                .doOnError(Timber::e)
                .subscribe();
    }

    private void displayReport(String report) {
        Timber.d("items: " + report);
        displayReportTextView.setText(report);
    }
}
