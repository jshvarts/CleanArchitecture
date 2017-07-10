package com.jshvarts.cleanarchitecture.lobby;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

/**
 * Provides Lobby-specific dependencies.
 */

@Module
public class LobbyFragmentModule {

    @Provides
    LobbyRepository provideLobbyRepository() {
        return new LobbyRepositoryImpl();
    }

    @Provides
    LobbyFragmentRepository provideLobbyFragmentRepository() {
        return new LobbyFragmentRepositoryImpl();
    }
}
