package com.jshvarts.cleanarchitecture.lobby;

import com.jshvarts.cleanarchitecture.di.ActivityScope;

import javax.inject.Inject;

import io.reactivex.Single;

@ActivityScope
class LobbyReportUseCase {
    private final LobbyRepository repository;

    @Inject
    LobbyReportUseCase(LobbyRepository repository) {
        this.repository = repository;
    }

    Single<String> generateReport() {
        return repository.getReport();
    }

    String getLocalReport() {
        return repository.getReportSync();
    }
}
