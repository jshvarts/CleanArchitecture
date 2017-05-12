package com.jshvarts.cleanarchitecture;

import android.app.Application;

import com.jshvarts.cleanarchitecture.di.AppComponent;

import timber.log.Timber;

/**
 * Custom Application that initializes dependency graph, sets up libraries, etc.
 */

public class CleanArchitectureApplication extends Application {
    private static AppComponent componentGraph;

    @Override
    public void onCreate() {
        super.onCreate();

        componentGraph = AppComponent.Initializer.init(this);

        setupTimber();
    }

    public static AppComponent component() {
        return componentGraph;
    }

    private void setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
