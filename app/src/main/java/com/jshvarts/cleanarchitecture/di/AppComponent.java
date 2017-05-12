package com.jshvarts.cleanarchitecture.di;

import com.jshvarts.cleanarchitecture.lobby.LobbyActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Dagger App Component.
 */
@Singleton
@Component(modules={AppModule.class})
public interface AppComponent {
    void inject(LobbyActivity activity);
}
