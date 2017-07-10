package com.jshvarts.cleanarchitecture.di;

import android.content.Context;

import com.jshvarts.cleanarchitecture.App;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger Module
 */
@Module
public class AppModule {
    @Provides
    Context provideContext(App application) {
        return application.getApplicationContext();
    }
}

