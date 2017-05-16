package com.jshvarts.cleanarchitecture.lobby;

import io.reactivex.Single;

/**
 * Lobby-specific repository implementation.
 */
public class LobbyRepositoryImpl implements LobbyRepository {

    static final String FAKE_REPORT_DATA = "this is a dummy lobby report.";

    @Override
    public Single<String> getReportAsync() {
        return Single.just(FAKE_REPORT_DATA);
    }

    @Override
    public String getReport() {
        return FAKE_REPORT_DATA;
    }
}
