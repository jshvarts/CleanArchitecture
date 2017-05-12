package com.jshvarts.cleanarchitecture.di;

import android.app.Application;

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
    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    Repository provideRepository() {
        return new RepositoryImpl();
    }
}

