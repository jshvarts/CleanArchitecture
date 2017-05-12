package com.jshvarts.cleanarchitecture;

import android.app.Application;

import com.jshvarts.cleanarchitecture.di.AppComponent;
import com.jshvarts.cleanarchitecture.di.AppModule;
import com.jshvarts.cleanarchitecture.di.DaggerAppComponent;

/**
 * Custom Application.
 */

public class CleanArchitectureApplication extends Application {
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
