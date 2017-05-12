package com.jshvarts.cleanarchitecture.lobby;

import com.jshvarts.cleanarchitecture.repository.Repository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
class LobbyReportUseCase {
    private final Repository repository;

    @Inject
    LobbyReportUseCase(Repository repository) {
        this.repository = repository;
    }

    Single<List<String>> generateReport() {
        return repository.getAllItems();
    }
}
