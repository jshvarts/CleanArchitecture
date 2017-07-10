package com.jshvarts.cleanarchitecture;

import android.app.Activity;
import android.app.Application;

import com.jshvarts.cleanarchitecture.di.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import timber.log.Timber;

/**
 * Custom Application that initializes dependency graph, sets up libraries, etc.
 */

public class App extends Application implements HasActivityInjector {
    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        setupTimber();

        DaggerAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this);
    }

    protected void setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
