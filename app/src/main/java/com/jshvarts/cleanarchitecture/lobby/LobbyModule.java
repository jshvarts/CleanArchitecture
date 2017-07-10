package com.jshvarts.cleanarchitecture.lobby;

import dagger.Module;
import dagger.Provides;

/**
 * Provides Lobby-specific dependencies.
 */

@Module
public class LobbyModule {

    @Provides
    LobbyRepository provideLobbyRepository() {
        return new LobbyRepositoryImpl();
    }
}
