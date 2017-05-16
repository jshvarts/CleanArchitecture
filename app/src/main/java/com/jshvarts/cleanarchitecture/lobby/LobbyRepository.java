package com.jshvarts.cleanarchitecture.lobby;

import io.reactivex.Single;

/**
 * Lobby-specific Repository Interface
 */
public interface LobbyRepository {
    Single<String> getReportAsync();
    String getReport();
}
