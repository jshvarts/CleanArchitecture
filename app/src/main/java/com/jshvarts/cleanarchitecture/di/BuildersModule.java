package com.jshvarts.cleanarchitecture.di;

import com.jshvarts.cleanarchitecture.lobby.LobbyActivity;
import com.jshvarts.cleanarchitecture.lobby.LobbyModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Binds all sub-components within the app.
 */
@Module
public abstract class BuildersModule {
    @ContributesAndroidInjector(modules = LobbyModule.class)
    abstract LobbyActivity bindLobbyActivity();

    // Add more bindings here for other sub-components
}
