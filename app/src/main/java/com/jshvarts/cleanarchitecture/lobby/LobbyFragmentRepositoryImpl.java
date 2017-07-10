package com.jshvarts.cleanarchitecture.lobby;

import java.util.Arrays;
import java.util.List;

public class LobbyFragmentRepositoryImpl implements LobbyFragmentRepository {
    @Override
    public List<String> getAllItems() {
        return Arrays.asList("d", "e", "f");
    }
}
