package com.jshvarts.cleanarchitecture.lobby;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jshvarts.cleanarchitecture.CleanArchitectureApplication;
import com.jshvarts.cleanarchitecture.R;
import com.jshvarts.cleanarchitecture.repository.Repository;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @Inject
    protected Repository repository;

    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CleanArchitectureApplication.component().inject(this);

        disposable = repository.getAllItems()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(items -> Timber.d("items: " + items))
                .doOnError(Timber::e)
                .subscribe();
    }

    @Override
    protected void onStop() {
        super.onStop();

        disposable.dispose();
    }
}
