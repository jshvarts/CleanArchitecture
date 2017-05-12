package com.jshvarts.cleanarchitecture;

import android.app.Application;
import android.content.Context;
import android.support.annotation.VisibleForTesting;

import com.jshvarts.cleanarchitecture.di.AppComponent;
import com.jshvarts.cleanarchitecture.di.AppModule;
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
    }

    public static AppComponent getAppComponent(Context context) {
        App app = (App) context.getApplicationContext();
        if (app.appComponent == null) {
            app.appComponent = app.createComponent();
        }
        return app.appComponent;
    }

    @VisibleForTesting
    protected AppComponent createComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    protected void setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
