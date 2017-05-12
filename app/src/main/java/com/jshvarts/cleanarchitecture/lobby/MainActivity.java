package com.jshvarts.cleanarchitecture.lobby;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.jshvarts.cleanarchitecture.CleanArchitectureApplication;
import com.jshvarts.cleanarchitecture.R;
import com.jshvarts.cleanarchitecture.repository.Repository;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Inject
    protected Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((CleanArchitectureApplication) getApplication()).getAppComponent().inject(this);

        Log.d(LOG_TAG, "item 1 in repo: " + repository.getAllItems().get(0));
    }
}
