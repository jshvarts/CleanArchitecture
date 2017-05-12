package com.jshvarts.cleanarchitecture.di;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.jshvarts.cleanarchitecture.App;
import com.jshvarts.cleanarchitecture.repository.Repository;
import com.jshvarts.cleanarchitecture.repository.RepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger Module
 */

@Module
public class AppModule {
    private final App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return app;
    }

    @Provides
    @Singleton
    Repository provideRepository() {
        return new RepositoryImpl();
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(app);
    }
}

