package com.jshvarts.cleanarchitecture.lobby;

import com.jshvarts.cleanarchitecture.di.ActivityScope;

import dagger.Subcomponent;

/**
 * Lobby-specific Dagger sub-component.
 */
@ActivityScope
@Subcomponent(modules={LobbyModule.class})
public interface LobbyComponent {
    void inject(LobbyActivity activity);
}
