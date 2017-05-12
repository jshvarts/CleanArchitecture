package com.jshvarts.cleanarchitecture.di;

import com.jshvarts.cleanarchitecture.lobby.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Dagger App Component.
 */
@Singleton
@Component(modules={AppModule.class})
public interface AppComponent {
    void inject(MainActivity activity);
}
