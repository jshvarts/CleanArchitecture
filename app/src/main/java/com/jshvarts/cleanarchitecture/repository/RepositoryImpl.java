package com.jshvarts.cleanarchitecture.repository;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Single;

/**
 * Repository implementation.
 */

public class RepositoryImpl implements Repository {
    @Override
    public Single<List<String>> getAllItems() {
        List<String> data = Arrays.asList("a", "b");
        return Single.just(data);
    }
}
