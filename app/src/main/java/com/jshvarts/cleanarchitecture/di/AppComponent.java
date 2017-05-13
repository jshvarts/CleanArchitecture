package com.jshvarts.cleanarchitecture.di;

import com.jshvarts.cleanarchitecture.lobby.LobbyComponent;
import com.jshvarts.cleanarchitecture.lobby.LobbyModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Dagger App Component.
 */
@Singleton
@Component(modules={AppModule.class})
public interface AppComponent {
    LobbyComponent plus(LobbyModule module);
}
