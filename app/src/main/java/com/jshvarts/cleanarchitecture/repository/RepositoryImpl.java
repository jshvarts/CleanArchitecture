package com.jshvarts.cleanarchitecture.repository;

import java.util.Arrays;
import java.util.List;

/**
 * Repository implementation.
 */

public class RepositoryImpl implements Repository {
    @Override
    public List<String> getAllItems() {
        return Arrays.asList("a", "b");
    }
}
