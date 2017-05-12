package com.jshvarts.cleanarchitecture.repository;

import java.util.List;

import io.reactivex.Single;

/**
 * Repository Interface
 */

public interface Repository {
    Single<List<String>> getAllItems();
}
