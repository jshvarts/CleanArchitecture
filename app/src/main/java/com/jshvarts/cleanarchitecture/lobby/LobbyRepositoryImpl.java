package com.jshvarts.cleanarchitecture.lobby;

import io.reactivex.Single;

/**
 * Lobby-specific repository implementation.
 */
public class LobbyRepositoryImpl implements LobbyRepository {
    @Override
    public Single<String> getReport() {
        return Single.just("this is a dummy lobby report.");
    }
}
