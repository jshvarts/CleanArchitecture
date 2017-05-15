package com.jshvarts.cleanarchitecture.lobby;

import io.reactivex.Single;

/**
 * Lobby-specific repository implementation.
 */
public class LobbyRepositoryImpl implements LobbyRepository {

    private static final String FAKE_REPORT_DATA = "this is a dummy lobby report.";

    @Override
    public Single<String> getReport() {
        return Single.just(FAKE_REPORT_DATA);
    }

    @Override
    public String getReportSync() {
        return FAKE_REPORT_DATA;
    }
}
