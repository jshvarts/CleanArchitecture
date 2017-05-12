package com.jshvarts.cleanarchitecture;

import android.app.Application;

import com.jshvarts.cleanarchitecture.di.AppComponent;
import com.jshvarts.cleanarchitecture.di.AppModule;
import com.jshvarts.cleanarchitecture.di.DaggerAppComponent;

import timber.log.Timber;

/**
 * Custom Application.
 */

public class CleanArchitectureApplication extends Application {
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        initDagger();

        initTimber();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    private void initDagger() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    private void initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
