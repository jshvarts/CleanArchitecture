package com.jshvarts.cleanarchitecture;

import android.app.Application;

import com.jshvarts.cleanarchitecture.di.AppComponent;
import com.jshvarts.cleanarchitecture.di.DaggerAppComponent;

import timber.log.Timber;

/**
 * Custom Application that initializes dependency graph, sets up libraries, etc.
 */

public class App extends Application {
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        setupTimber();

        appComponent = DaggerAppComponent
                .builder()
                .application(this)
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    protected void setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
