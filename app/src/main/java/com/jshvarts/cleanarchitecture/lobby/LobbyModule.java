package com.jshvarts.cleanarchitecture.lobby;

import com.jshvarts.cleanarchitecture.di.ActivityScope;
import com.jshvarts.cleanarchitecture.di.AppModule;

import dagger.Module;
import dagger.Provides;

/**
 * Lobby-specific Dagger Module
 */

@Module(includes = {AppModule.class})
public class LobbyModule {
    @Provides
    @ActivityScope
    LobbyRepository provideLobbyRepository() {
        return new LobbyRepositoryImpl();
    }
}

